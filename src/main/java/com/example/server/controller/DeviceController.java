package com.example.server.controller;

import com.alibaba.fastjson2.JSON;
import com.example.server.TCP_Client;
import com.example.server.entity.Device;
import com.example.server.repository.DeviceRepository;
import com.example.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
public class DeviceController {
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @RequestMapping("/getdeviceid")
    public Integer addDevice(String customName, String ip) {
        System.out.println(customName);
        Device device = deviceRepository.addDevice(customName, ip);

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
        System.out.println(deviceList.devices);
        return deviceList;
    }

    @RequestMapping("/updatedevice")
    public boolean update(String json_device) {
        Device device = JSON.parseObject(json_device, Device.class);
        deviceRepository.save(device);
        return true;
    }

    @RequestMapping("/deletedevice")
    public boolean delete(String json_device) {
        Device device = JSON.parseObject(json_device, Device.class);
        deviceRepository.delete(device);
        recordRepository.deleteByDeviceId(device.getDeviceId());
        return true;
    }

    @RequestMapping("/finddevice")
    public boolean findDevice(int did, String ip) {
        if (!deviceRepository.findAllByDeviceId(did).isEmpty()) {
            Device device = deviceRepository.findAllByDeviceId(did).get(0);
            device.setIp(ip);
            deviceRepository.save(device);
        }
        return !deviceRepository.findAllByDeviceId(did).isEmpty();
    }

    @RequestMapping("/opendoor")
    public boolean open(int did) throws IOException {
        TCP_Client client = new TCP_Client();
        String ip = deviceRepository.findAllByDeviceId(did).get(0).getIp();
        client.openDoor(ip);
        return true;
    }
}

