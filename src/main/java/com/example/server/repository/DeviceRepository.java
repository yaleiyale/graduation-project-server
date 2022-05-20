package com.example.server.repository;

import com.example.server.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {


    List<Device> findAll();


    default Device addDevice(String customName) {
        Device device = new Device();
        device.setCustomName(customName);
        return device;
    }
}
