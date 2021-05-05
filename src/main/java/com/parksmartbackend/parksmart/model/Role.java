package com.parksmartbackend.parksmart.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ERole name;

    @Column(name = "name_tr")
    private String nameTr;

    public Role() {

    }

    public Role(ERole name) {

        this.name = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public ERole getName() {

        return name;
    }

    public void setName(ERole name) {

        this.name = name;
    }
}