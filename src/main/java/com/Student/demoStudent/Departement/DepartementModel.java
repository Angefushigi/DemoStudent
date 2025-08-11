package com.Student.demoStudent.Departement;

import java.util.List;

import com.Student.demoStudent.Speciality.SpecialityModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DepartementModel {

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<SpecialityModel> speciality;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departement")
    private Integer idDepartement;
    private String name;
    private String description;

    //Constructeurs
    public DepartementModel() {}
    public DepartementModel(Integer idDepartement, String name, String description) {
        this.idDepartement = idDepartement;
        this.name = name;
        this.description = description;
    }

    //id_departement
    public Integer getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Integer idDepartement) {
        this.idDepartement = idDepartement;
    }

    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
