package com.task.student.controller;

import com.task.student.dto.StudentDto;
import com.task.student.dto.StudentResponse;
import com.task.student.entity.Student;
import com.task.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/studentApi/add")
    public ResponseEntity<Object> registerStudent(@RequestBody StudentDto studentDto){
        StudentDto dto = studentService.add(studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StudentResponse> getAllStudents(
        @RequestParam(name = "pageNo",defaultValue = "0",required = false)int pageNo,
        @RequestParam(name = "pageSize",defaultValue = "5",required = false) int pageSize,
        @RequestParam(name = "sortBy",defaultValue = "totalMarks",required = false) String sortBy,
        @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        StudentResponse allStudents = studentService.getAllStudents(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allStudents,HttpStatus.OK);
    }
    @GetMapping("/studentApi/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") long id){
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
}
