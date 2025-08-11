package com.Student.demoStudent.Enrollment;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class EnrollmentId implements Serializable{

    private Integer studentId;
    private Integer specialityId;
    private Integer levelId;

    //Constructors
    public EnrollmentId() {}
    public EnrollmentId(Integer studentId, Integer specialityId, Integer levelId) {
        this.studentId = studentId;
        this.specialityId = specialityId;
        this.levelId = levelId;
    }
    
    //getters and setters
     
    //...
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    //...
    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialyId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    //...
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentId)) return false;
        EnrollmentId that = (EnrollmentId) o;
        return studentId.equals(that.studentId) && specialityId.equals(that.specialityId) && levelId.equals(that.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, specialityId, levelId);
    }
}
