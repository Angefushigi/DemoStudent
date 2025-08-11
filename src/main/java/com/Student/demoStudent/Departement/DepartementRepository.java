package com.Student.demoStudent.Departement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<DepartementModel, Integer> {

   Optional<DepartementModel> findByNameIgnoreCase(String name);
   List<DepartementModel> findByNameContainingIgnoreCase(String name);
   Optional<DepartementModel> findById(Integer id_departement);
}
