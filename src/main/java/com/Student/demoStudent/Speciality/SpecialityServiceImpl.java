package com.Student.demoStudent.Speciality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Student.demoStudent.Departement.DepartementModel;
import com.Student.demoStudent.Departement.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private DepartementRepository departementRepository;

    public List<SpecialityModel> getAllSpeciality() {
        return specialityRepository.findAll();
    }

    
    public SpecialityModel getSpecialityById(Integer id) {
        return specialityRepository.findById(id).orElse(null);
    }

    //add a speciality
    public String addSpecialityModel(Integer idDepartement, SpecialityModel data) {
        Optional<DepartementModel> departementOpt = departementRepository.findById(idDepartement);
        if (departementOpt.isEmpty()) {
            return "Departement not found";
        }
        data.setDepartement(departementOpt.get());
        specialityRepository.save(data);
        return "Speciality added successfully";
    }

    //get all speciality
    @Override
    public List<SpecialityModel> getSpecialityByDepartementId(Integer idDepartement) {
        return specialityRepository.findByDepartement_IdDepartement(idDepartement);
    }

    //get the departement
    public Optional<DepartementModel> getDepartementBySpeciality(Integer id) {
        return specialityRepository.findById(id).map(SpecialityModel::getDepartement);
    }

    //Update
    public ResponseEntity<String> updateSpeciality(Integer id, SpecialityUpdateDto dto) {
        Optional<SpecialityModel> specialityOpt = specialityRepository.findById(id);
        if (specialityOpt.isPresent()) {
            SpecialityModel specialityModel = specialityOpt.get();
            specialityModel.setName(dto.getName());
            specialityModel.setDescription(dto.getDescription());

            if (dto.getIdDepartement()!= null) {
                Optional<DepartementModel> departementOpt = departementRepository.findById(dto.getIdDepartement());
                departementOpt.ifPresent(specialityModel::setDepartement);
    }

            specialityRepository.save(specialityModel);
            return ResponseEntity.ok("Speciality updated successfully");
    } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Speciality not found");
    }
    }
    //delete
    public String deleteSpeciality(Integer id) {
        Optional<SpecialityModel> specialityOpt = specialityRepository.findById(id);
        if (specialityOpt.isPresent()) {
            specialityRepository.delete(specialityOpt.get());
            return "Speciality deleted";
        } else {
            return "Speciality not found";
        }
    }
}

