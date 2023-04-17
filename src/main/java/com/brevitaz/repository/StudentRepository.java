package com.brevitaz.repository;

import com.brevitaz.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getByFirstNameAndEmail(String firstName, String email);
    List<Student> findByAddressCity(String city);

}
