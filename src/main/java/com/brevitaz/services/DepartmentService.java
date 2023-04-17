package com.brevitaz.services;

import com.brevitaz.DTO.*;
import com.brevitaz.entity.Department;
import com.brevitaz.entity.Student;
import com.brevitaz.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department addDepartment(Department department) {
        Department department1 = departmentRepository.findByName(department.getName());
        if (department1 == null) {
            return departmentRepository.save(department);
        }
        return department1;
    }

    public List<DepartmentResponseDTO> getAll() {
        List<DepartmentResponseDTO> departmentResponseDTOS = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : department.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO(
                    department.getId(),
                    department.getName(),
                    studentResponseDTOS);


            departmentResponseDTOS.add(departmentResponseDTO);

        }
        return departmentResponseDTOS;
    }

    public List<DepartmentResponseDTO> getByName(final String name) {
        Department departments = departmentRepository.findByName(name);
        List<DepartmentResponseDTO> departmentResponseDTOS = new ArrayList<>();

            Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : departments.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO(
                    departments.getId(),
                    departments.getName(),
                    studentResponseDTOS);


            departmentResponseDTOS.add(departmentResponseDTO);


        return departmentResponseDTOS;
    }
}

