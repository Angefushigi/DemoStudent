package com.Student.demoStudent.Enrollment;

import com.Student.demoStudent.Student.StudentModel;


import com.Student.demoStudent.Level.LevelModel;
import com.Student.demoStudent.Speciality.SpecialityModel;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class EnrollmentModel {

    @EmbeddedId
    private EnrollmentId id_enrol ;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "id")
    private StudentModel student;

    @ManyToOne
    @MapsId("specialityId")
    @JoinColumn(name = "id_speciality")
    private SpecialityModel speciality;


    @ManyToOne
    @MapsId("levelId")
    @JoinColumn(name = "id_level")
    private LevelModel level;

    @Column(name = "Academic_year")
    private String academic_year;

    //Constructeurs
    public EnrollmentModel() { 
        // Default constructor
    }
    public EnrollmentModel(StudentModel student, SpecialityModel speciality, LevelModel level, String academic_year) {
        this.student = student;
        this.speciality = speciality;
        this.level = level;
        this.id_enrol = new EnrollmentId(student.getId_student(), speciality.getId(), level.getId());
        this.academic_year = academic_year;
    }

    //getters et setters
    //...
    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    //...
    public EnrollmentId getId_enrol() {
        return id_enrol;
    }

    public void setId_enrol(EnrollmentId id_enrol)  {
        this.id_enrol = id_enrol;
    }

    //...
    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    } 

    //...
    public SpecialityModel getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityModel speciality) {
        this.speciality = speciality;
    }

    //...
    public LevelModel getLevel() {
        return level;
    }

    public void setLevel(LevelModel level) {
        this.level = level;
    }
}
