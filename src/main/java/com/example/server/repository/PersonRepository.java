package com.example.server.repository;

import com.example.server.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
  List<Person> findByPersonId(Integer pid);
}
