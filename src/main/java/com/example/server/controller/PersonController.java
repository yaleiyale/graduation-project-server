package com.example.server.controller;

import com.example.server.entity.Person;
import com.example.server.repository.PersonRepository;
import com.example.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        recordRepository.save(recordRepository.generateRecord(pid, did,time, able));
        return able;
    }

    @RequestMapping("/generatepid")
    private Integer generatePid(String name) {
        Person person = new Person();
        person.setPersonName(name);
        person.setPower(2147483647);
        person = personRepository.save(person);
        int id = person.getPersonId();
        String filename = id + ".jpg";
        person.setImageUrl(filename);
        personRepository.save(person);
        return person.getPersonId();
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile file) {
        if (!file.isEmpty()) {
            String uploadPath = "C:/bitmap";
            String filename = file.getOriginalFilename();
            File localFile = new File(uploadPath + "/" + filename);
            try {
                file.transferTo(localFile); //把上传的文件保存至本地
                System.out.println(filename);
                return filename;//上传成功，返回保存的文件地址
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败");
                return "";
            }
        } else {
            System.out.println("文件为空");
            return "";
        }
    }

    @RequestMapping("/getidbybitmap")
    public String FaceRecognition(@RequestParam MultipartFile file) {
    return "1";
    }

}
