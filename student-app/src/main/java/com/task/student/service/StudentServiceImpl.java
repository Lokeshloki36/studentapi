package com.task.student.service;

import com.task.student.Exception.ResourceNotFoundException;
import com.task.student.dto.StudentDto;
import com.task.student.dto.StudentResponse;
import com.task.student.entity.Student;
import com.task.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentDto add(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
        studentRepository.save(student);
        StudentDto studentDto1 = mapToDto(student);
        return studentDto1;
    }

    @Override
    public StudentResponse getAllStudents(int pageNo, int pageSize,String sortBy ,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("ASC")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Student> all = studentRepository.findAll(pageable);
        List<Student> content = all.getContent();
        List<StudentDto> dtos = content.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(dtos);
        studentResponse.setPageNo(all.getNumber());
        studentResponse.setPageSize(all.getSize());
        studentResponse.setTotalPages(all.getTotalPages());
        studentResponse.setTotalElements(all.getTotalElements());
        studentResponse.setLast(all.isLast());
        return studentResponse;
    }

    @Override
    public Student getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("student", "id", id)
        );
        return student;
    }

    StudentDto mapToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setGender(student.getGender());
        studentDto.setTotalMarks(student.getTotalMarks());
        return studentDto;
    }

    Student mapToEntity(StudentDto studentDto){
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setGender(studentDto.getGender());
        student.setTotalMarks(studentDto.getTotalMarks());
        return student;
    }
}
