package com.example.server.repository;

import com.example.server.entity.PassRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<PassRecord, Integer> {


    @Transactional
    void deleteByPersonId(Integer pid);

    @Transactional
    void deleteByDeviceId(Integer did);

    @NotNull
    @Override
    List<PassRecord> findAll();


    default PassRecord generateRecord(int pid, int did, String time, boolean able, String faceImage) {

        PassRecord passRecord = new PassRecord();
        if (pid == 0) {
            passRecord.setPersonId(-1);
        } else {
            passRecord.setPersonId(pid);
        }
        passRecord.setDeviceId(did);
        passRecord.setResult(able);
        passRecord.setTime(time);
        passRecord.setImageUrl(faceImage);
        return passRecord;

    }
}
