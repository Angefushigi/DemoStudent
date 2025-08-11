package com.Student.demoStudent.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    public List<LevelModel> getAllLevel() {
        return levelRepository.findAll();
    }

    //Get a level by id
    public ResponseEntity<?> getLevelById(Integer id) {
        Optional<LevelModel> levelOpt = levelRepository.findById(id);
        if (levelOpt.isPresent()) {
            return ResponseEntity.ok(levelOpt.get());
        }  else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Level not found with :" + id);
        }
    }

    //Create
    public String addLevel(String description) {
        if (levelRepository.findByDescription(description).isPresent()) {
            return "Level already exists";     
        } else {
            LevelModel levelModel = new LevelModel();
            levelModel.setDescription(description);
            //save the new level
            levelRepository.save(levelModel);
            return "Level added successfully";
        }
    }

    //update
    public ResponseEntity<?> updateLevel(Integer id, LevelModel newData) {
        Optional<LevelModel> levelOpt = levelRepository.findById(id);
        LevelModel levelModel;
        
        if (levelOpt.isPresent()) {   
            levelModel = levelOpt.get();
            levelModel.setDescription(newData.getDescription());
            levelRepository.save(levelModel);
            return ResponseEntity.ok(levelOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departement not found with :" + id);
        }
    }

    //delete
    public ResponseEntity<?> deleteLevel(Integer id) {
        Optional<LevelModel> levelOpt = levelRepository.findById(id);
        if (levelOpt.isPresent()) {
            levelRepository.delete(levelOpt.get());
            return ResponseEntity.ok(levelOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Level not found with :" + id);
        }
    }
}
