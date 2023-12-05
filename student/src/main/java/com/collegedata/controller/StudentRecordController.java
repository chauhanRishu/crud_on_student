package com.collegedata.controller;

import com.collegedata.entities.StudentRecord;
import com.collegedata.service.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentRecordController {

    @Autowired
    private StudentRecordService studentRecordService;

    //create
    @PostMapping("/create")
    public StudentRecord createStudent(@RequestBody StudentRecord studentRecord)
    {
        return this.studentRecordService.createStudent(studentRecord);
    }

    //read
    @GetMapping("/getStudent/{studentId}")
    public StudentRecord getStudentById(@PathVariable Integer studentId)
    {
        return this.studentRecordService.studentById(studentId);
    }

    //update
    @PutMapping("/update/{studentId}")
    public StudentRecord updateStudentRecord(@PathVariable Integer studentId,@RequestBody StudentRecord studentRecord)
    {
        return this.studentRecordService.updateStudent(studentId,studentRecord);
    }

    @DeleteMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Integer studentId)
    {
        return this.studentRecordService.deleteById(studentId);
    }

}
