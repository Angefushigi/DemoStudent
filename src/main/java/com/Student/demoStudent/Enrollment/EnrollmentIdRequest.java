package com.Student.demoStudent.Enrollment;

public class EnrollmentIdRequest {

    private Integer studentId;
    private Integer specialityId;
    private Integer levelId;
    private String academic_year;

    //Constructors
    public EnrollmentIdRequest() {}
    public EnrollmentIdRequest(Integer studentId, Integer specialityId, Integer levelId, String academic_year) {
        this.studentId = studentId;
        this.specialityId = specialityId;
        this.levelId = levelId;
        this.academic_year = academic_year;
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

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    //...
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    //...
    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }


    @Override
    public String toString() {
        return "EnrollmentIdRequest{" +
            "studentId=" + studentId +
            ", specialityId=" + specialityId +
            ", levelId=" + levelId +
            ", academic_year='" + academic_year + '\'' +
        '}';
    }
}

