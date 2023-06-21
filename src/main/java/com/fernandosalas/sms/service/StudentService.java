package com.fernandosalas.sms.service;
import com.fernandosalas.sms.dto.StudentDto;
import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    void createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long studentId);
}
