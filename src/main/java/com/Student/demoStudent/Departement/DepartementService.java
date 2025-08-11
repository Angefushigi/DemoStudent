package com.Student.demoStudent.Departement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<DepartementModel> getAllDepartement() {
        return departementRepository.findAll();
    }

    //Byname
    public List<DepartementModel> searchDepartementsByKeyword(String keyword) {
    return departementRepository.findByNameContainingIgnoreCase(keyword.trim());
    } 

    public ResponseEntity<?> getDepartementById(Integer idDepartement) {
        Optional<DepartementModel> departementOpt = departementRepository.findById(idDepartement);
        if (departementOpt.isPresent()) {
            return ResponseEntity.ok(departementOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departement not found with id :" + idDepartement);
        }
    }

    //Create
    public String addDepartement(String name, String description) {
        if (departementRepository.findByNameIgnoreCase(name).isPresent()) {
            return "Departement already exists";
        } else {
            DepartementModel departementModel = new DepartementModel();
            departementModel.setName(name);
            departementModel.setDescription(description);
    // Save the new departement
            departementRepository.save(departementModel);
            return "Departement added successfully";
        }
    }

    //...update
    public ResponseEntity<?> updateDepartement(Integer idDepartement, DepartementModel newData) {
        Optional<DepartementModel> departementOpt = departementRepository.findById(idDepartement);
        DepartementModel departementModel;
        
        if (departementOpt.isPresent()) {   
            departementModel = departementOpt.get();
            departementModel.setName(newData.getName());
            departementModel.setDescription(newData.getDescription());
            departementRepository.save(departementModel);
            return ResponseEntity.ok(departementOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departement not found with :" + idDepartement);
        }
        
    }

    //delete...
    public ResponseEntity<?> deleteDepartement(Integer idDepartement) {
        Optional<DepartementModel> departementModel = departementRepository.findById(idDepartement);
        if (departementModel.isPresent()) {
            departementRepository.delete(departementModel.get());
            return ResponseEntity.ok(departementModel.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departement not found with :" + idDepartement);
            
        }
    }
}

