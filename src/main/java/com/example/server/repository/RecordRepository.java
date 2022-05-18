package com.example.server.repository;

import com.example.server.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    default Record generateRecord(int pid, int did, String time, boolean able) {
        Record record = new Record();
        record.setPersonId(pid);
        record.setDeviceId(did);
        record.setResult(able);
        record.setTime(time);
        return record;
    }
}
