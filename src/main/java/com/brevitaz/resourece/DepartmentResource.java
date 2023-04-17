package com.brevitaz.resourece;

import com.brevitaz.DTO.DepartmentResponseDTO;
import com.brevitaz.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentResource {
    private final DepartmentService departmentService;

    public DepartmentResource(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public List<DepartmentResponseDTO> getAll() {
        return departmentService.getAll();
    }
    @GetMapping("/{name}")
    public List<DepartmentResponseDTO> getByName(@PathVariable String name) {
        return departmentService.getByName(name);
    }

}
