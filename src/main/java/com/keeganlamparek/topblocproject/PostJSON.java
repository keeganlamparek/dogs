package com.keeganlamparek.topblocproject;

public class PostJSON {

    // Class used for GSON stringify
    private String id;
    private String name;
    private int average;
    private String[] studentIds;

    public PostJSON(String id, String name, int average, String[] studentIds){
        this.id = id;
        this.name = name;
        this.average = average;
        this.studentIds = studentIds;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAverage() { return average; }
    public void setAverage(int average) { this.average = average; }

    public String[] getStudentIds() { return studentIds; }
    public void setStudentIds(String[] studentIds) { this.studentIds = studentIds; }
}
