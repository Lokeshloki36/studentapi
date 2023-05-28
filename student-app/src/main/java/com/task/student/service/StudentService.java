package com.task.student.service;

import com.task.student.dto.StudentDto;
import com.task.student.dto.StudentResponse;
import com.task.student.entity.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    public StudentDto add(StudentDto studentDto);

    StudentResponse getAllStudents(int pageNo, int pageSize,String sortBy,String sortDir);

    Student getStudentById(long id);
}
