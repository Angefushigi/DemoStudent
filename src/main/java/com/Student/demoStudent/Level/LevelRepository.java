package com.Student.demoStudent.Level;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LevelRepository extends JpaRepository<LevelModel, Integer> {
   Optional<LevelModel> findById(Integer id);
   Optional<LevelModel> findByDescription(String description);
}
