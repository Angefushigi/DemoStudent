package com.Student.demoStudent.Enrollment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Student.demoStudent.Student.StudentModel;

import jakarta.persistence.EntityNotFoundException;
import java.lang.System;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // ← autorise ton front-end
@RequestMapping("/Enrollment")
public class EnrollmentController {
     
    @Autowired
    private EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    //All students
    @GetMapping("/students/all")
    public ResponseEntity<List<StudentModel>> getAllEnrolledStudents() {
        List<StudentModel> students = enrollmentService.getAllEnrolledStudents();
        return ResponseEntity.ok(students);
    }

    //Students by speciality
    @GetMapping("/students/by-speciality")
    public ResponseEntity<List<StudentModel>> getStudentsBySpeciality(@RequestParam Integer specialityId) {
        return ResponseEntity.ok(enrollmentService.getStudentsBySpeciality(specialityId));
    }

    //Students by level
    @GetMapping("/students/by-level")
    public ResponseEntity<List<StudentModel>> getStudentByLevel(@RequestParam Integer levelId) {
       return ResponseEntity.ok(enrollmentService.getStudentsBySpeciality(levelId));
    }

    //all enrollment
    @GetMapping("/all")
    public ResponseEntity<List<EnrollmentModel>> getAllEnrollments() {
        List<EnrollmentModel> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    //enrollment by speciality
    @GetMapping("/by-speciality")
    public ResponseEntity<List<EnrollmentModel>> getBySpeciality(@RequestParam Integer specialityId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsBySpeciality(specialityId));
    }

    //enrollment by level
    @GetMapping("/by-level")
    public ResponseEntity<List<EnrollmentModel>> getByLevel (@RequestParam Integer levelId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsBySpeciality(levelId));
    }

    //save
    @PostMapping("/save")
    public  ResponseEntity<String> saveEnrollment(@RequestBody EnrollmentIdRequest request) {
        System.out.println("requete reçue:" + request); 
        try {
            enrollmentService.saveOrUpdateEnrollment(
                request.getStudentId(),
                request.getSpecialityId(),
                request.getLevelId(),
                request.getAcademic_year()
                
            );

            return ResponseEntity.ok("Enrôlement enregistré avec succès.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne lors de l’enregistrement.");
        }
        
    }



    //update
    @PutMapping("/update")
    public ResponseEntity<String> updateEnrollment(@RequestBody EnrollmentUpdateRequest updateRequest){
        System.out.println("requete reçue:" + updateRequest);
        try {
            enrollmentService.updateEnrollment(updateRequest);
            return ResponseEntity.ok("Enrôlement mis à jour avec succès.");
        } catch(EntityNotFoundException | IllegalStateException e)  {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne lors de la mise à jour.");
        }
    }



    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEnrollment(
        @RequestParam Integer studentId,
        @RequestParam Integer specialityId,
        @RequestParam Integer levelId) {

       enrollmentService.deleteEnrollment(studentId, specialityId, levelId);
        return ResponseEntity.ok("Enrôlement supprimé avec succès.");
    }
}
