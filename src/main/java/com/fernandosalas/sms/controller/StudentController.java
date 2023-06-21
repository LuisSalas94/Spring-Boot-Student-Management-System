package com.fernandosalas.sms.controller;

import com.fernandosalas.sms.dto.StudentDto;
import com.fernandosalas.sms.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    //handle method to handle new student request
    @GetMapping("/students/new")
    public String newStudent(Model model) {
        // students model object to store student form data
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "create_student";
    }

    //handle method to handle save student form submit request
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                              BindingResult result,
                              Model model) {
        if(result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "create_student";
        }
        studentService.createStudent(studentDto);
        return "redirect:/students";
    }

}
