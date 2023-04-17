package com.brevitaz.resourece;

import com.brevitaz.DTO.StudentDTO;
import com.brevitaz.entity.Student;
import com.brevitaz.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentResource {
    private final StudentService studentService;

    public StudentResource(final StudentService studentService) {
        this.studentService = studentService;

    }


    @PostMapping("/insert")
    public String insertStudentsDetails(@RequestBody StudentDTO studentDTO) throws SQLException {

        if (studentService.insertStudent(studentDTO)) {
            return "Student Detail Inserted Successfully";
        }
        return "Student Already Exist";

    }

    @GetMapping("")
    public List<StudentDTO> viewAll() {
        return studentService.getAll();

    }


    @PutMapping("/update/{id}")
    public String updateStudentDetails(@RequestBody StudentDTO studentDTO, @PathVariable int id) throws SQLException {
        if (studentService.updateDetails(studentDTO, id)) {
            return "Student Details Updated Successfully";
        }
        return "Error updating Student";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable int id) {
        if (studentService.deleteStudent(id)) {
            return "StudentsID Deleted Successfully ";
        }
        return "StudentsID Does Not Exist";
    }

    @GetMapping("/city/{city}")
    public List<StudentDTO> findByCity(@PathVariable("city") String city){
        return studentService.getByCity(city);
    }
}
