package com.Student.demoStudent.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
   private StudentRepository studentRepository;

   //add
   public StudentModel addStudent(StudentModel studentModel) {
    return studentRepository.save(studentModel);
   }

   //all
   public List<StudentModel> getAllStudent() {
    return studentRepository.findAll();
   }

   //Lire par id
   public ResponseEntity<?> getStudentById(Integer id_student) {
    Optional<StudentModel> studentOpt = studentRepository.findById(id_student);
        if (studentOpt.isPresent()) {
            return ResponseEntity.ok(studentOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id :" + id_student);
        }
   }


   //update
   public StudentModel updateStudent(Integer id_student, StudentModel updateStudent) {
    return studentRepository.findById(id_student).map(student -> {
        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());
        student.setBirth(updateStudent.getBirth());
        return studentRepository.save(student);
    }).orElse(null);
   }
   

   //delete
   public void deleteStudent(Integer id_student) {
    if (!studentRepository.existsById(id_student)) {
        throw new StudentNotFoundException("Student not found with id: " + id_student);
    }
        studentRepository.deleteById(id_student);
}   
}