package com.example.Department.Service;

import com.example.Department.Model.Department;
import com.example.Department.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServices {

    private final DepartmentRepository repository;

    public DepartmentServices(DepartmentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Department addDepartment(Department department) {
        return repository.save(department);
    }

    // READ ALL
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    // READ BY ID
    public Optional<Department> getDepartmentById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public Department updateDepartment(Long id, Department newDept) {
        return repository.findById(id).map(dept -> {
            dept.setName(newDept.getName());
            dept.setLocation(newDept.getLocation());
            return repository.save(dept);
        }).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // DELETE
    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}
