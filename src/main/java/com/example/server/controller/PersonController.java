package com.example.server.controller;

import com.example.server.repository.PersonRepository;
import com.example.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RecordRepository recordRepository;

    @RequestMapping("/judgeandrecord")
    private Boolean judgeAndRecord(int pid, int did) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        int temp_power = personRepository.findByPersonId(pid).get(0).getPower();
        boolean able = (temp_power >> (did - 1) & 1) != 0;
        recordRepository.save(recordRepository.generateRecord(pid,did,time,able));
        return able;
    }

}
