package com.example.Department.Controller;

import com.example.Department.Model.Department;
import com.example.Department.Service.DepartmentServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*")
public class DepartmentController {

    private final DepartmentServices service;

    public DepartmentController(DepartmentServices service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return service.addDepartment(department);
    }

    // READ ALL
    @GetMapping
    public List<Department> getAllDepartments() {
        return service.getAllDepartments();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return service.getDepartmentById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {
        return service.updateDepartment(id, department);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
        return "Department deleted successfully";
    }
}
