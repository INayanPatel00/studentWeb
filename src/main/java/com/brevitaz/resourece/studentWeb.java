package com.brevitaz.resourece;

import com.brevitaz.DTO.StudentDTO;
import com.brevitaz.DTO.StudentWebDTO;
import com.brevitaz.entity.Address;
import com.brevitaz.entity.Department;
import com.brevitaz.entity.Student;
import com.brevitaz.entity.Subject;
import com.brevitaz.repository.AddressRepository;
import com.brevitaz.repository.DepartmentRepository;
import com.brevitaz.repository.StudentRepository;
import com.brevitaz.repository.SubjectRepository;
import com.brevitaz.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/web")
public class studentWeb {
    private final SubjectService subjectService;

    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public studentWeb(final SubjectService subjectService, final SubjectRepository subjectRepository, final DepartmentRepository departmentRepository, final AddressRepository addressRepository, final StudentRepository studentRepository) {
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String view(Model model) {
        List<Student> list = studentRepository.findAll();
        model.addAttribute("list", list);
        return "view";
    }


    @GetMapping("/add")
    public String add(Model model) {
        StudentWebDTO studentWebDTO = new StudentWebDTO();
        StudentDTO studentDTO = new StudentDTO();
        model.addAttribute("user", studentWebDTO);
        return "index";
    }

    @PostMapping("/insert")
    public String insertStudentsDetails(@ModelAttribute("user") StudentWebDTO studentWebDTO) {
        Student student = new Student(studentWebDTO.getFirstName(),
                studentWebDTO.getLastName(),
                studentWebDTO.getEmail(),
                studentWebDTO.getPhone(),
                studentWebDTO.getAge(),
                studentWebDTO.getGender());

        Address address = new Address(studentWebDTO.getHouseNo(),
                studentWebDTO.getLandMark(),
                studentWebDTO.getCity(),
                studentWebDTO.getState(),
                studentWebDTO.getPinCode());

        Department department = new Department(studentWebDTO.getDepartmentName());


        Set<Subject> subjects = new HashSet<>();
        Subject subject = new Subject(studentWebDTO.getSubjectName1());
        subjects.add(subject);
        Subject subject1 = new Subject(studentWebDTO.getSubjectName2());
        subjects.add(subject1);
        Subject subject2 = new Subject(studentWebDTO.getSubjectName3());
        subjects.add(subject2);


        Set<Subject> subjectSet = new HashSet<>();

        for (Subject s : subjects) {
            Subject subject3 = subjectService.addSubject(s);
            subjectSet.add(subject3);
        }


        Address address1 = addressRepository.save(address);
        Department department1 = departmentRepository.save(department);


        student.setAddress(address1);
        student.setDepartment(department1);

        student.setSubject(subjectSet);
        studentRepository.save(student);
        return "redirect:/web";
    }

}

