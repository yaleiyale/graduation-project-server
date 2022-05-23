package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "record")
public class PassRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    private Integer recordId;

    @Column(name = "device_id", nullable = false)
    private Integer deviceId;

    @Column(name = "person_id", nullable = false)
    private Integer personId;

    @Column(name = "result", nullable = false)
    private Boolean result = false;

    @Column(name = "time", nullable = false, length = 45)
    private String time;

    @Column(name = "image_url", nullable = false, length = 45)
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer id) {
        this.recordId = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}