package com.Student.demoStudent.Speciality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Student.demoStudent.Departement.DepartementModel;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173") // ← autorise ton front-end
@RestController
@RequestMapping("/Speciality")
public class SpecialityController {


    @Autowired
    private SpecialityServiceImpl specialityServiceImpl;

    @Autowired
    private SpecialityRepository specialityRepository;

    @GetMapping("/all")
    public List<SpecialityModel> gSpecialityModels() {
        return specialityServiceImpl.getAllSpeciality();
    } 

    @GetMapping("/{id}")
    @CrossOrigin
    public SpecialityModel getById(@PathVariable Integer id) {
        return specialityServiceImpl.getSpecialityById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SpecialityModel>> searchSpecialitiesByName(@RequestParam String name) {
        List<SpecialityModel> results = specialityRepository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(results);
    }

    //speciality of departement
    @GetMapping("/spec/{idDepartement}")
    @CrossOrigin
    public List<SpecialityModel> getSpecialityByDepartement(@PathVariable Integer idDepartement) {
        return specialityServiceImpl.getSpecialityByDepartementId(idDepartement);
    }

    //departement d'une spécialité
    @GetMapping("/depart/{id}")
    @CrossOrigin
        public ResponseEntity<?> getDepartementOfSpeciality(@PathVariable Integer id) {
            Optional<DepartementModel> departementOpt = specialityServiceImpl.getDepartementBySpeciality(id);
        return departementOpt.isPresent()? ResponseEntity.ok(departementOpt.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Speciality not found");
        }

    // add a speciality    
    @PostMapping("/add/{idDepartement}")
    @CrossOrigin
    public String addSpeciality(@PathVariable Integer idDepartement, @RequestBody SpecialityModel specialityModel) {
        return specialityServiceImpl.addSpecialityModel(idDepartement, specialityModel);

    }

    //update
    @PutMapping("/update/{id}")
    @CrossOrigin
    public ResponseEntity<String> updateSpeciality(@PathVariable Integer id, @RequestBody SpecialityUpdateDto dto) {
        return specialityServiceImpl.updateSpeciality(id, dto);
    }

    //delete
    @PostMapping("/delete/{id}")
    @CrossOrigin
    public String deleteSpeciality(@PathVariable Integer id) {
        return specialityServiceImpl.deleteSpeciality(id);
    }
}
