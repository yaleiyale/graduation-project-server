package com.example.server.controller;

import com.example.server.entity.Device;
import com.example.server.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @RequestMapping("/getdeviceid")
    public Integer addDevice(String customName) {
        System.out.println(customName);
        Device device = deviceRepository.addDevice(customName);
        deviceRepository.save(device);
        return device.getDeviceId();
    }

    @RequestMapping("/getalldevices")
    public Object getDevices() {
        class DeviceList {
            public List<Device> devices;
        }
        DeviceList deviceList = new DeviceList();
        deviceList.devices = deviceRepository.findAll();
        return deviceList;
    }
}

