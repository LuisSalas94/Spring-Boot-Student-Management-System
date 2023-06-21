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
import org.springframework.web.bind.annotation.PathVariable;
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

    //handle method to handle edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId, Model model) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        return "edit_student";
    }

    //handle method to handle edit student form submit request
    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId")
                                Long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model) {
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    //handle method to handle delete student request
    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    //handle method to handle view student request
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        System.out.println("View is working");
        return "view_student";
    }

}
