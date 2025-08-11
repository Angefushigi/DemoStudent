package com.Student.demoStudent.Speciality;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<SpecialityModel, Integer> {

    Optional<SpecialityModel> findById(Integer id_speciality);
    List<SpecialityModel>  findByDepartement_IdDepartement(Integer id_departement);
    List<SpecialityModel> findByNameContainingIgnoreCase(String name);
}
