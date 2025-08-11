package com.Student.demoStudent.Enrollment;

import com.Student.demoStudent.Speciality.SpecialityModel;
import com.Student.demoStudent.Student.StudentModel;

import com.Student.demoStudent.Level.LevelModel;

public class EnrollmentRequest {

    private StudentModel student;
    private SpecialityModel speciality;
    private LevelModel level;
    private String academic_year; 


    //Constructeurs
    public EnrollmentRequest() {}
    public EnrollmentRequest(StudentModel student, SpecialityModel speciality, LevelModel level) {
        this.student = student;
        this.speciality = speciality;
        this.level = level;
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

    public void setSpecialy(SpecialityModel speciality) {
        this.speciality = speciality;
    }

    //...
    public LevelModel getLevel() {
        return level;
    }

    public void setLevel(LevelModel level) {
        this.level = level;
    }
    //...
    public String getAcademic_year() {
        return academic_year;
    }
    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }
}
