package com.keeganlamparek.topblocproject;

import com.google.gson.Gson;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Client {

    public static void main(String args[]) throws Exception {
        StudentDAO dao = new XLSXStudentDAO();
        Gson gson = new Gson();

        Student[] students = dao.getAllStudents();

        List<Student> femaleStudentsInCompSci = new ArrayList<>();

        // Find all female computer science majors
        for(Student student: students){
            if(student.getGender().equals("F") && student.getMajor().equals("computer science")){
                femaleStudentsInCompSci.add(student);
            }
        }

        // sort female students by ID
        femaleStudentsInCompSci.sort(new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                String idOne = student1.getStudentId();
                String idTwo = student2.getStudentId();
                return idOne.compareTo(idTwo);
            }
        });

        String[] studentIds = new String[femaleStudentsInCompSci.size()];

        for(int i = 0; i < studentIds.length; i++){
            studentIds[i] = femaleStudentsInCompSci.get(i).getStudentId();
        }

        int classAverage = 0;

        for(Student student: students){
            classAverage += (int) student.getScore();
        }

        classAverage = classAverage / students.length;

        System.out.println(classAverage);

        PostJSON postJSON = new PostJSON("keeganlamparek@gmail.com", "Keegan Lamparek", classAverage, studentIds);

        String json = gson.toJson(postJSON);

        System.out.println(json);

        Client.post(json);
    }

    public static void post(String json){
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("http://3.86.140.38:5000/challenge");
            StringEntity params =new StringEntity(json);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.toString());

        }catch (Exception ex) {

            System.out.println(ex.toString());
        }
    }
}
