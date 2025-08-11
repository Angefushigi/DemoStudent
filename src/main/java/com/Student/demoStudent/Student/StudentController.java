package com.Student.demoStudent.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/Student")
public class StudentController {

    @Autowired
    private StudentService studentService = new StudentService();

    @Autowired
    private StudentRepository studentRepository;

    //Create(ajouter un étudiant)
    @PostMapping("/add")
    public StudentModel addStudent(@RequestBody StudentModel studentModel) {
        return studentService.addStudent(studentModel);
    }

    //read (obtenir tous les étudiants)
    @GetMapping("/all")
    public List<StudentModel> getAllStudent() {
        return studentService.getAllStudent();
    }

    //read (obtenir un étudiant par son id)
    @GetMapping("/{id_student}")
    @CrossOrigin
    public ResponseEntity<?> StudentModel(@PathVariable Integer id_student) {
        return studentService.getStudentById(id_student);
    }

    //Search
    @GetMapping("/search")
    @CrossOrigin
    public ResponseEntity<List<StudentModel>> searchStudentByNames(@RequestParam String query) {
        List<StudentModel> results = studentRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
        return ResponseEntity.ok(results);
    }

    //update
    @PutMapping("/{id_student}") 
    public StudentModel upadateStudent(@PathVariable Integer id_student, @RequestBody StudentModel updateStudent) {
        return studentService.updateStudent(id_student, updateStudent);
    }

    //delete
    @DeleteMapping("/{id_student}")
    @CrossOrigin
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id_student) {
        try {
            studentService.deleteStudent(id_student);
            return ResponseEntity.ok("Student deleted successfully");
        }
        catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
