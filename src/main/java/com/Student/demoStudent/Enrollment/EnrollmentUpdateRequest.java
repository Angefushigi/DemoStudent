package com.Student.demoStudent.Enrollment;

public class EnrollmentUpdateRequest {
    private Integer OldStudentId;
    private Integer OldSpecialityId;
    private Integer OldLevelId;

    private Integer NewStudentId;
    private Integer NewSpecialityId;
    private Integer NewLevelId;

   private String academic_year;

    // Constructors
    public EnrollmentUpdateRequest() {}
    public EnrollmentUpdateRequest(Integer oldStudentId, Integer oldSpecialityId, Integer oldLevelId,
                                   Integer newStudentId, Integer newSpecialityId, Integer newLevelId,
                                   String academic_year) {
        this.OldStudentId = oldStudentId;
        this.OldSpecialityId = oldSpecialityId;
        this.OldLevelId = oldLevelId;
        this.NewStudentId = newStudentId;
        this.NewSpecialityId = newSpecialityId;
        this.NewLevelId = newLevelId;
        this.academic_year = academic_year;                      
    }

    // Getters and Setters
    public Integer getOldStudentId() {
        return OldStudentId;
    }
    public void setOldStudentId(Integer oldStudentId) {
        this.OldStudentId = oldStudentId;
    }
    //...
    public Integer getOldSpecialityId() {
        return OldSpecialityId;
    }
    public void setOldSpecialityId(Integer oldSpecialityId) {
        this.OldSpecialityId = oldSpecialityId;
    }

    //...
    public Integer getOldLevelId() {
        return OldLevelId;
    }
    public void setOldLevelId(Integer oldLevelId) {
        this.OldLevelId = oldLevelId;
    }

    //...
    public Integer getNewStudentId() {
        return NewStudentId;
    }
    public void setNewStudentId(Integer newStudentId) {
        this.NewStudentId = newStudentId;
    }

    //...
    public Integer getNewSpecialityId() {
        return NewSpecialityId;
    }
    public void setNewSpecialityId(Integer newSpecialityId) {
        this.NewSpecialityId = newSpecialityId;
    }

    //...
    public Integer getNewLevelId() {
        return NewLevelId;
    }   
    public void setNewLevelId(Integer newLevelId) {
        this.NewLevelId = newLevelId;
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
        return "EnrollmentUpdateRequest{" +
            "oldStudentId=" + OldStudentId +
            ", oldSpecialityId=" + OldSpecialityId +
            ", oldLevelId=" + OldLevelId +
            ", newStudentId=" + NewStudentId +
            ", newSpecialityId=" + NewSpecialityId +
            ", newLevelId=" + NewLevelId +
            ", academic_year='" + academic_year + '\'' +
        '}';
    }
}
