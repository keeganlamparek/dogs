package com.keeganlamparek.topblocproject;

public class Student {

    private String studentId;
    private String major;
    private String gender;
    private double highestScore;

    public Student(String studentId, String major, String gender, double score){
        this.studentId = studentId;
        this.major = major;
        this.gender = gender;
        this.highestScore = score;
    }

    public String getStudentId() { return this.studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public double getScore() { return this.highestScore; }
    public void setScore(int score) { this.highestScore = score; }

    public String toString(){
        return "<Student>: ID: " + this.studentId + ", Major: " + this.major + ", Gender: " + this.gender + ", Score: " + this.highestScore + ";" ;
    }



}
