package com.Student.demoStudent.Departement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // ← autorise ton front-end
@RequestMapping("/Departement")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @GetMapping("/all")
    public List<DepartementModel> getAllDepartement() {
        return departementService.getAllDepartement();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPartial(@RequestParam String name) {
        List<DepartementModel> results = departementService.searchDepartementsByKeyword(name);
        if (!results.isEmpty()) {
                return ResponseEntity.ok(results);
        } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("message", "Aucun département trouvé"));
        }
    }


    @GetMapping("/info/{idDepartement}")
    public ResponseEntity<?> DepartementModel(@PathVariable Integer idDepartement) {
        return departementService.getDepartementById(idDepartement);
    }

    @PostMapping("/add")
    public String addDepartement(@RequestBody DepartementModel departementModel) {
        return departementService.addDepartement(departementModel.getName(), departementModel.getDescription());
    }  
    
    @PutMapping("/update/{idDepartement}")
    @CrossOrigin
    public ResponseEntity<?> updateDepartement(@PathVariable Integer idDepartement, @RequestBody DepartementModel departementModel ) {
        return departementService.updateDepartement(idDepartement, departementModel);
    }

    @DeleteMapping("/delete/{idDepartement}")
    @CrossOrigin
    public ResponseEntity<?> deleteDepartement(@PathVariable Integer idDepartement) {
        return departementService.deleteDepartement(idDepartement);
    }
}
