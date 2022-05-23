package com.example.server.repository;

import com.example.server.entity.Device;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {


    @NotNull
    @Override
    List<Device> findAll();

    List<Device> findAllByDeviceId(Integer did);

    default Device addDevice(String customName, String ip) {
        Device device = new Device();
        device.setCustomName(customName);
        device.setIp(ip);
        return device;
    }
}
