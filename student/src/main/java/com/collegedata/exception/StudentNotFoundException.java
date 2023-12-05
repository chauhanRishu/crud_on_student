package com.collegedata.exception;

public class StudentNotFoundException extends RuntimeException{

    int studentId;

    public StudentNotFoundException(int studentId){
        super("Student not found with studentId : "+ studentId+ " into database");
        this.studentId = studentId;
    }

}
