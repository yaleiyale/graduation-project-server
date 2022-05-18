package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Integer personId;

    @Column(name = "person_name", nullable = false, length = 45)
    private String personName;

    @Column(name = "image_url", length = 45)
    private String imageUrl;

    @Column(name = "power")
    private Integer power;

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer id) {
        this.personId = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}