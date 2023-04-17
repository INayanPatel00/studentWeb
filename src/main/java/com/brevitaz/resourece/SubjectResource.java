package com.brevitaz.resourece;

import com.brevitaz.DTO.SubjectResponseDTO;
import com.brevitaz.services.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectResource {
    private final SubjectService subjectService;

    public SubjectResource(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("")
    public List<SubjectResponseDTO> getAll(){
        return subjectService.getAll();

    }
    @GetMapping("/{name}")
    public List<SubjectResponseDTO> getByName(@PathVariable String name){
        return subjectService.getByName(name);
    }
}

