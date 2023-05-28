package com.task.student.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {

    private List<StudentDto> content;
    private int pageNo;
    private int pageSize;
    private long totalPages;
    private long totalElements;
    private boolean isLast;
}
