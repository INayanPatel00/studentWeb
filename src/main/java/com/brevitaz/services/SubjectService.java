package com.brevitaz.services;

import com.brevitaz.DTO.StudentResponseDTO;
import com.brevitaz.DTO.SubjectResponseDTO;
import com.brevitaz.entity.Student;
import com.brevitaz.entity.Subject;
import com.brevitaz.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(final SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject addSubject(Subject subject) {
        Subject subject1 = subjectRepository.findByName(subject.getName());

        if (subject1 == null) {
            return subjectRepository.save(subject);
        }
        return subject1;

    }


    public List<SubjectResponseDTO> getAll() {
        List<SubjectResponseDTO> subjectResponseDTOS = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findAll();
        for (Subject subject : subjects) {
            Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : subject.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO(
                    subject.getId(),
                    subject.getName(),
                    studentResponseDTOS);

            subjectResponseDTOS.add(subjectResponseDTO);

        }
        return subjectResponseDTOS;

    }

    public List<SubjectResponseDTO> getByName(final String name) {
        Subject subjects = subjectRepository.findByName(name);
        List<SubjectResponseDTO> subjectResponseDTOS = new ArrayList<>();


        Set<StudentResponseDTO> studentResponseDTOS = new HashSet<>();
            for (Student student : subjects.getStudent()) {
                studentResponseDTOS.add(new StudentResponseDTO(student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAge(),
                        student.getGender()));
            }
            SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO(
                    subjects.getId(),
                    subjects.getName(),
                    studentResponseDTOS);

            subjectResponseDTOS.add(subjectResponseDTO);
        return subjectResponseDTOS;

        }

}
