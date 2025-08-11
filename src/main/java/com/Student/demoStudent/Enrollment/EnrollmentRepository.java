package com.Student.demoStudent.Enrollment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentModel, EnrollmentId> {

    List<EnrollmentModel> findByLevel_Id(Integer id);  
    List<EnrollmentModel> findBySpeciality_Id(Integer id); 

}
