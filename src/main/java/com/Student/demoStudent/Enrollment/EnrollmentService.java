package com.Student.demoStudent.Enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Student.demoStudent.Speciality.SpecialityModel;
import com.Student.demoStudent.Speciality.SpecialityRepository;
import com.Student.demoStudent.Student.StudentModel;
import com.Student.demoStudent.Student.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

import com.Student.demoStudent.Level.LevelModel;
import com.Student.demoStudent.Level.LevelRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired private LevelRepository levelRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    //Constructeurs
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }


    //Enregistrement
    public void saveOrUpdateEnrollment(Integer studentId, Integer specialityId, Integer levelId, String academic_year) {
        if (studentId == null || specialityId == null || levelId == null) {
            throw new IllegalArgumentException("Les identifiants ne doivent pas être null.");
        }
        try {
        // Clé composite
        EnrollmentId id_enrol= new EnrollmentId(studentId, specialityId, levelId);

        // Vérifie si un enrôlement existe déjà
        Optional<EnrollmentModel> existingEnrollment = enrollmentRepository.findById(id_enrol);

        // Récupération des entités liées
        System.out.println("ID reçu: studentId=" + studentId + ", specialityId=" + specialityId + ", levelId=" + levelId);
        StudentModel student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Étudiant introuvable"));
        SpecialityModel speciality = specialityRepository.findById(specialityId).orElseThrow(() -> new EntityNotFoundException("Spécialité introuvable"));
        LevelModel level = levelRepository.findById(levelId).orElseThrow(() -> new EntityNotFoundException("Niveau introuvable"));

        // Création ou mise à jour de l'enrôlement
        EnrollmentModel enrollment = existingEnrollment.orElse(new EnrollmentModel());
        enrollment.setId_enrol(id_enrol);
        enrollment.setStudent(student);
        enrollment.setSpeciality(speciality);
        enrollment.setLevel(level);
        enrollment.setAcademic_year(academic_year);

        // Sauvegarde en base
        enrollmentRepository.save(enrollment);
        } catch (Exception e) {
            e.printStackTrace(); // ← très important pour voir l’erreur exacte
            throw e;
        }
    }


    //Upadte
    public void updateEnrollment(EnrollmentUpdateRequest request) {
       // Création de l'ancienne clé
        EnrollmentId oldId = new EnrollmentId(request.getOldStudentId(),request.getOldSpecialityId(), request.getOldLevelId());

        // Récupération de l’enrôlement existant
        EnrollmentModel oldEnrollment = enrollmentRepository.findById(oldId)
        .orElseThrow(() -> new EntityNotFoundException("Ancien enrôlement introuvable"));

        // Récupération du nouvel enrollment
        EnrollmentId newId = new EnrollmentId(
            request.getNewStudentId(),
            request.getNewSpecialityId(),
            request.getNewLevelId()
        );

        // Vérifie si le nouvel enrôlement existe déjà
       if (enrollmentRepository.existsById(newId)) {
            throw new IllegalStateException("Le nouvel enrôlement existe déjà.");
       }

        // Récupération des nouvelles entités
        StudentModel student = studentRepository.findById(request.getNewStudentId()).orElseThrow(() -> new EntityNotFoundException("Etudiant introuvable"));
        SpecialityModel speciality = specialityRepository.findById(request.getNewSpecialityId()).orElseThrow(() -> new EntityNotFoundException("Spécialité introuvable"));
        LevelModel level = levelRepository.findById(request.getNewLevelId()).orElseThrow(() -> new EntityNotFoundException("Niveau introuvable"));

        // Création du nouvel enrôlement
        EnrollmentModel newEnrollment = new EnrollmentModel();
        newEnrollment.setId_enrol(newId);
        newEnrollment.setStudent(student);
        newEnrollment.setSpeciality(speciality);
        newEnrollment.setLevel(level);
        newEnrollment.setAcademic_year(request.getAcademic_year());

       //delete the old and save the new
        enrollmentRepository.save(newEnrollment);
        enrollmentRepository.delete(oldEnrollment);

    }

    //Reucperer tous les enrolements d'un etudiant
    public List<StudentModel> getAllEnrolledStudents() {
        return enrollmentRepository.findAll().stream().map(EnrollmentModel::getStudent).distinct().toList();
    }

    //Student by speciality
    public List<StudentModel> getStudentsBySpeciality(Integer id) {
        return enrollmentRepository.findBySpeciality_Id(id).stream().map(EnrollmentModel::getStudent).distinct().toList();
    }

    //Student by level
    public List<StudentModel> getStudentByLevel(Integer id_level) {
        return enrollmentRepository.findByLevel_Id(id_level).stream().map(EnrollmentModel::getStudent).distinct().toList();
    }

    //Reucperer tous les enrolements
    public List<EnrollmentModel> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    //Reucperer un enrolement par son id
    public EnrollmentModel getEnrollmentById(EnrollmentId id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    //enrollment by speciality
    public List<EnrollmentModel> getEnrollmentsBySpeciality(Integer id) {
        return enrollmentRepository.findBySpeciality_Id(id);
    }

    //enrollment by level
    public List<EnrollmentModel> getEnrollmentsByLevel(Integer id) {
        return enrollmentRepository.findByLevel_Id(id);
    }

    //Supprimer un enrollment
    public void deleteEnrollment(Integer studentId, Integer specialityId, Integer levelId) {
        EnrollmentId id_Enrol = new EnrollmentId(studentId, specialityId, levelId);

        if(!enrollmentRepository.existsById(id_Enrol)) {
            throw new EntityNotFoundException("L'enrôlement n'existe pas.");
        }

        enrollmentRepository.deleteById(id_Enrol);
    }

}
