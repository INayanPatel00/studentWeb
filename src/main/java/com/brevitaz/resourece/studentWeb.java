package com.brevitaz.resourece;

import com.brevitaz.entity.Student;
import com.brevitaz.repository.StudentRepository;
import com.brevitaz.repository.StudentwebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class  studentWeb {
    private final StudentwebRepository studentwebRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public studentWeb(final StudentwebRepository studentwebRepository, final StudentRepository studentRepository) {
        this.studentwebRepository = studentwebRepository;
        this.studentRepository = studentRepository;
    }


    @GetMapping("")
    public String welcome(Model model) {
        Student studentweb = new Student();
        model.addAttribute("user",studentweb);
        return "web";
    }


    @PostMapping("/insert")
    public String insertStudentsDetails(@ModelAttribute("user") Student student) {
        studentRepository.save(student);
        return "success";
    }
}

