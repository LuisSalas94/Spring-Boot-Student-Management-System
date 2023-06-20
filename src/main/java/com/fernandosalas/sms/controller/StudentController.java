package com.fernandosalas.sms.controller;

import com.fernandosalas.sms.dto.StudentDto;
import com.fernandosalas.sms.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;

    //handle method to handle list student request
    @GetMapping("/students")
    public String listStudents(Model model) {
       List<StudentDto> studentDtoList = studentService.getAllStudents();
       model.addAttribute("students", studentDtoList);
       return "students";
    }

}