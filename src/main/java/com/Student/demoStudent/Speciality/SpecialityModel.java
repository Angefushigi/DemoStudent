package com.Student.demoStudent.Speciality;

import com.Student.demoStudent.Departement.DepartementModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SpecialityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_speciality")
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_departement")
    private DepartementModel departement; 

    //Constructeurs
    public SpecialityModel() {}
    public SpecialityModel(Integer id, String name, String description, DepartementModel departement) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departement = departement;
    }
 

    //...
    public Integer getIdDepartement() {
        return this.departement != null? this.departement.getIdDepartement() : null;
    }

    // id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    //departement
    public DepartementModel getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementModel departement) {
        this.departement = departement;
    }
    //...
}
