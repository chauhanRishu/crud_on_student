package com.collegedata.service;

import com.collegedata.entities.StudentRecord;
import com.collegedata.repository.StudentRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentRecordService {

    @Autowired
    private StudentRecordRepo studentRecordRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //create
    public StudentRecord createStudent(StudentRecord saveData)
    {
        StudentRecord studenData=new StudentRecord();
        studenData.setStudentName(saveData.getStudentName());
        studenData.setMobileNumber(saveData.getMobileNumber());
        studenData.setAddress(saveData.getAddress());
        studenData.setEmail(saveData.getEmail());
        studenData.setPassword(this.passwordEncoder.encode(saveData.getPassword()));
        return this.studentRecordRepo.save(studenData);

    }

    //read
    public StudentRecord studentById(int studentId)
    {
        return this.studentRecordRepo.findById(studentId).get();
    }

    //update
    public StudentRecord updateStudent(int studentId,StudentRecord updateData)
    {
        StudentRecord data=this.studentRecordRepo.findById(studentId).get();
        StudentRecord newData=new StudentRecord();
        if(data.getStudentName()!=null)
        {
            newData.setStudentId(studentId);
            newData.setStudentName(updateData.getStudentName());
            newData.setMobileNumber(updateData.getMobileNumber());
            newData.setAddress(updateData.getAddress());
            newData.setEmail(updateData.getEmail());
            newData.setPassword(this.passwordEncoder.encode(updateData.getPassword()));
            this.studentRecordRepo.save(newData);
        }
        else
        {
            System.out.println("Student not found with student Id : "+studentId);
        }
        return newData;
    }
    //delete
    public String deleteById(int studentId)
    {
        this.studentRecordRepo.deleteById(studentId);
        return "student deleted with id : "+studentId+" successfully!!";
    }
}

