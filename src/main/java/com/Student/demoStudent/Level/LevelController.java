package com.Student.demoStudent.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // ← autorise ton front-end
@RestController
@RequestMapping("/Level")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @GetMapping("/all")
    public List<LevelModel> getLevelModels() {
        return levelService.getAllLevel();
    }


    //récupérer un niveau par son id
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return levelService.getLevelById(id);
    }

    //add a level
    @PostMapping("/add")
    public String crLevelModel(@RequestBody LevelModel l) {
        return levelService.addLevel(l.getDescription());
    }

    //update
    @PutMapping("/update/{id}")
    @CrossOrigin
    public ResponseEntity<?> updateLevelById(@PathVariable Integer id, @RequestBody LevelModel levelModel) {
        return levelService.updateLevel(id, levelModel);
    }

    //delete
    @PostMapping("/delete/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        return levelService.deleteLevel(id);
    }
}
