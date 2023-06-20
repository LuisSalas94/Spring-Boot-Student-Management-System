package com.fernandosalas.sms.repository;

import com.fernandosalas.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
