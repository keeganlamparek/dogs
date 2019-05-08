package com.keeganlamparek.topblocproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXStudentDAO implements StudentDAO{


    public Student[] getAllStudents() throws IOException{

        HashMap<String, Double> scores = loadScores();
        DataFormatter formatter = new DataFormatter();
        List<Student> studentsArrayList = new ArrayList<Student>();

        File excelFile = new File("src/main/resources/Student Info.xlsx");//getFileFromResources("Student Info.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);


        int skipHeaderRow = 1;

        for (int i = skipHeaderRow; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell id = row.getCell(0);
            String idFormatted = formatter.formatCellValue(id);
            Cell major = row.getCell(1);
            Cell gender = row.getCell(2);
            double score = scores.get(id.toString());
            studentsArrayList.add(new Student(idFormatted, major.toString(), gender.toString(), score));
        }

        workbook.close();
        fis.close();

        return studentsArrayList.toArray(new Student[studentsArrayList.size()]);
    }

    private HashMap<String, Double> loadScores() throws IOException{
        HashMap<String, Double> scores = new HashMap<>();

        File scoreFile1 = new File("src/main/resources/Test Scores.xlsx");
        File scoreFile2 = new File("src/main/resources/Test Retake Scores.xlsx");

        FileInputStream fis1 = new FileInputStream(scoreFile1);
        FileInputStream fis2 = new FileInputStream(scoreFile2);

        XSSFWorkbook scoreBook1 = new XSSFWorkbook(fis1);
        XSSFWorkbook scoreBook2 = new XSSFWorkbook(fis2);

        // we create an XSSF Workbook object for our XLSX Excel File
        // we get first sheet
        XSSFSheet scoreSheet1 = scoreBook1.getSheetAt(0);
        XSSFSheet scoreSheet2 = scoreBook2.getSheetAt(0);

        int skipHeaderRow = 1;

        for (int i = skipHeaderRow; i <= scoreSheet1.getLastRowNum(); i++) {
            Row row = scoreSheet1.getRow(i);

            Cell id = row.getCell(0);
            Cell score = row.getCell(1);
            scores.put(id.toString(), Double.parseDouble(score.toString()));
        }

        // Go through Retake Scores
        for (int i = skipHeaderRow; i < scoreSheet2.getLastRowNum(); i++) {
            Row row = scoreSheet2.getRow(i);
            String id = row.getCell(0).toString();
            double score = Double.parseDouble(row.getCell(1).toString());

            if(scores.containsKey(id) && score > scores.get(id)){
                scores.replace(id, score);
            }
        }

        fis1.close();
        fis2.close();

        scoreBook1.close();
        scoreBook2.close();
        return scores;
    }
}
