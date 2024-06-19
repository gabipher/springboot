package com.example.environmentalprojects.controller;

import com.example.environmentalprojects.model.Project;
import com.example.environmentalprojects.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            project.setCategory(projectDetails.getCategory());
            project.setStatus(projectDetails.getStatus());
            return projectRepository.save(project);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }

    @GetMapping("/category/{category}")
    public List<Project> getProjectsByCategory(@PathVariable String category) {
        return projectRepository.findByCategory(category);
    }

    @GetMapping("/status/{status}")
    public List<Project> getProjectsByStatus(@PathVariable String status) {
        return projectRepository.findByStatus(status);
    }

    @DeleteMapping("/completed")
    public void deleteCompletedProjects() {
        List<Project> completedProjects = projectRepository.findByStatus("Finalizado");
        projectRepository.deleteAll(completedProjects);
    }

    @DeleteMapping("/cancelled")
    public void deleteCancelledProjects() {
        List<Project> cancelledProjects = projectRepository.findByStatus("Cancelado");
        projectRepository.deleteAll(cancelledProjects);
    }
}
