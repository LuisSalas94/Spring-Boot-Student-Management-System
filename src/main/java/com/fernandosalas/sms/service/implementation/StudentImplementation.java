package com.fernandosalas.sms.service.implementation;
import com.fernandosalas.sms.dto.StudentDto;
import com.fernandosalas.sms.entity.Student;
import com.fernandosalas.sms.mapper.StudentMapper;
import com.fernandosalas.sms.repository.StudentRepository;
import com.fernandosalas.sms.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentImplementation implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
       List<Student> studentList = studentRepository.findAll();
       List<StudentDto> studentDtoList = new ArrayList<>();
       for(Student student:studentList) {
         StudentDto studentDto = StudentMapper.mapToStudentDto(student);
         studentDtoList.add(studentDto);
       }
       return studentDtoList;
    }

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return StudentMapper.mapToStudentDto(student);
    }
}
