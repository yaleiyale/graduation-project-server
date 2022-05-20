package com.example.server.repository;

import com.example.server.entity.PassRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<PassRecord, Integer> {

    @Override
    List<PassRecord> findAll();

    default PassRecord generateRecord(int pid, int did, String time, boolean able) {
        PassRecord passRecord = new PassRecord();
        passRecord.setPersonId(pid);
        passRecord.setDeviceId(did);
        passRecord.setResult(able);
        passRecord.setTime(time);
        return passRecord;
    }
}
