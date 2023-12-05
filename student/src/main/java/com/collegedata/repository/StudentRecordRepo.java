package com.collegedata.repository;

import com.collegedata.entities.StudentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRecordRepo extends JpaRepository<StudentRecord,Integer> {

    StudentRecord findByEmail(String email);
}
