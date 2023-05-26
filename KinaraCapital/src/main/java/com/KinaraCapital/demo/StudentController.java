package com.KinaraCapital.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
	
    private List<Student> studentData;
    
    public StudentController() {
        loadstudentData();
    }

    private void loadstudentData() {
		// TODO Auto-generated method stub
		
	}
    private void loadStudentData() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(getClass().getResource("/studentData.csv").toURI()));
            ObjectMapper objectMapper = new ObjectMapper();
            studentData = Arrays.asList(objectMapper.readValue(jsonData, Student[].class));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

	@GetMapping("/students")
    public List<Student> getStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String filterColumn,
            @RequestParam(required = false) String filterValue
    ) {
        List<Student> paginatedData = paginateStudentData(page, pageSize);

        if (filterColumn != null && filterValue != null) {
            paginatedData = filterStudents(paginatedData, filterColumn, filterValue);
        }

        return paginatedData;
    }

   
    private List<Student> paginateStudentData(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, studentData.size());

        return studentData.subList(startIndex, endIndex);
    }

    
    private List<Student> filterStudents(List<Student> students, String filterColumn, String filterValue) {
        switch (filterColumn) {
            case "name":
                return students.stream()
                        .filter(student -> student.getName().equals(filterValue))
                        .collect(Collectors.toList());
            default:
                return students; // 
        }
    }

}
