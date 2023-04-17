package com.brevitaz.services;

import com.brevitaz.DTO.AddressDTO;
import com.brevitaz.DTO.DepartmentDTO;
import com.brevitaz.DTO.StudentDTO;
import com.brevitaz.DTO.SubjectDTO;
import com.brevitaz.entity.Address;
import com.brevitaz.entity.Department;
import com.brevitaz.entity.Student;
import com.brevitaz.entity.Subject;
import com.brevitaz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AddressService addressService;
    private final DepartmentService departmentService;
    private final SubjectService subjectService;

    @Autowired
    public StudentService(final StudentRepository studentRepository, final AddressService addressService, final DepartmentService departmentService, final SubjectService subjectService) {
        this.studentRepository = studentRepository;
        this.addressService = addressService;
        this.departmentService = departmentService;
        this.subjectService = subjectService;
    }


    public Boolean insertStudent(StudentDTO studentDTO) {
        if (studentRepository.getByFirstNameAndEmail(studentDTO.getFirstName(), studentDTO.getEmail()) == null) {
            Address address = new Address(
                    studentDTO.getAddressDTO().getHouseNo(),
                    studentDTO.getAddressDTO().getLandMark(),
                    studentDTO.getAddressDTO().getCity(),
                    studentDTO.getAddressDTO().getState(),
                    studentDTO.getAddressDTO().getPinCode()
            );
            Address address1 = addressService.addAddress(address);

            Department department = new Department(studentDTO.getDepartmentDTO().getName());
            Department department1 = departmentService.addDepartment(department);

            Set<Subject> subjects = new HashSet<>();
            for (SubjectDTO s : studentDTO.getSubjectDTOs()) {
                Subject subject = new Subject(s.getName());
                subjects.add(subject);
            }
            Set<Subject> subjectSet = new HashSet<>();

            for (Subject s : subjects) {
                Subject subject = subjectService.addSubject(s);
                subjectSet.add(subject);
            }

            Student student = new Student(
                    studentDTO.getFirstName(),
                    studentDTO.getLastName(),
                    studentDTO.getEmail(),
                    studentDTO.getPhone(),
                    studentDTO.getAge(),
                    studentDTO.getGender(), address1, department1, subjectSet);

            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public List<StudentDTO> getAll() {

        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();

        for (Student student : studentList) {
            Set<SubjectDTO> subjectDTOS = new HashSet<>();
            for (Subject subject : student.getSubject()) {
                subjectDTOS.add(new SubjectDTO(subject.getId(), subject.getName()));
            }
            StudentDTO studentDTO = new StudentDTO(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getAge(),
                    student.getGender(),
                    new AddressDTO(student.getAddress().getId(), student.getAddress().getHouseNo(), student.getAddress().getLandMark(), student.getAddress().getCity(), student.getAddress().getState(), student.getAddress().getPinCode()),
                    new DepartmentDTO(student.getDepartment().getId(), student.getDepartment().getName()),
                    subjectDTOS
            );
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }


    public boolean updateDetails(StudentDTO studentDTO, final int id) throws SQLException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Address address = new Address(
                    studentDTO.getAddressDTO().getHouseNo(),
                    studentDTO.getAddressDTO().getLandMark(),
                    studentDTO.getAddressDTO().getCity(),
                    studentDTO.getAddressDTO().getState(),
                    studentDTO.getAddressDTO().getPinCode());
            Address address1 = addressService.addAddress(address);

            Department department = new Department(studentDTO.getDepartmentDTO().getName());
            Department department1 = departmentService.addDepartment(department);

            Set<Subject> subjects = new HashSet<>();
            for (SubjectDTO subjectDTO : studentDTO.getSubjectDTOs()) {
                Subject subject = new Subject(subjectDTO.getName());
                subjects.add(subject);
            }
            Set<Subject> subjectSet = new HashSet<>();

            for (Subject s : subjects) {

                Subject subject = subjectService.addSubject(s);
                subjectSet.add(subject);
            }
            Student studentToSave = new Student(id, studentDTO.getFirstName(),
                    studentDTO.getLastName(),
                    studentDTO.getEmail(),
                    studentDTO.getPhone(),
                    studentDTO.getAge(),
                    studentDTO.getGender(),
                    address1,
                    department1,
                    subjectSet);
            studentRepository.save(studentToSave);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        System.out.println("delete");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<StudentDTO> getByCity(String city) {
        return studentRepository.findByAddressCity(city).stream().map(
                student -> new StudentDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()
                )
        ).collect(Collectors.toList());
    }
}