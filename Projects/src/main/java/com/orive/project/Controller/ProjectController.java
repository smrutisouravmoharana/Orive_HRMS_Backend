package com.orive.project.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.orive.project.Dto.ProjectDto;
import com.orive.project.Entity.EmployeeProjectManagementEntity;
import com.orive.project.Entity.ProjectEntity;
import com.orive.project.Service.ProjectService;
//import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping(value = "projects")
@CrossOrigin(origins = "*")
public class ProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    
    
 // Create a new Project
    @PostMapping("/create/projects")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
  	  ProjectDto createdProject = projectService.createProject(projectDto);
        logger.info("Created project with Id: {}", createdProject.getProjectTitle());
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Get all project
    
    @GetMapping("/get/projects")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<ProjectDto>> getAllProject() {
        List<ProjectDto> projects = projectService.getAllProject();
        logger.info("Retrieved {} projects from the database", projects.size());
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get projects by ID
    @GetMapping("/get/{projectsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long projectsId) {
        Optional<ProjectDto> project = projectService.getProjectById(projectsId);
        if (project.isPresent()) {
            logger.info("Retrieved project with ID: {}", projectsId);
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            logger.warn("project with ID {} not found", projectsId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
 // Get Manager by ID
 	  @GetMapping("/{managerEmployeeId}")
 	 //@PreAuthorize("hasRole('client_HR')")
 	    public ResponseEntity<List<ProjectDto>> getProjectsByManagerEmployeeId(@PathVariable Long managerEmployeeId) {
 	        List<ProjectDto> projects = projectService.getManagerEmployeeId(managerEmployeeId);

 	        if (projects.isEmpty()) {
 	            return ResponseEntity.notFound().build();
 	        } else {
 	            return ResponseEntity.ok(projects);
 	        }
 	    }
    
 	  
// 	// Get Employee by ID
// 	    @GetMapping("/employee/{employeeId}")
 	// @PreAuthorize("hasRole('client_HR')")
// 	    public ResponseEntity<ProjectDto> getProjectsByEmployeeId(@PathVariable Long employeeId) {
// 	        Optional<ProjectDto> projects = projectService.getProjectsByEmployeeId(employeeId);
//
// 	        return projects.map(ResponseEntity::ok)
// 	                .orElse(ResponseEntity.notFound().build());
// 	    }
    

 	// Get employee details by employeeId
 	    @GetMapping("/employee/details/{username}")
 	 //@PreAuthorize("hasRole('client_Employee')||hasRole('client_HR')")
 	    public ResponseEntity<List<EmployeeProjectManagementEntity>> getEmployeeDetailsByEmployeeId(@PathVariable String username) {
 	        Optional<List<EmployeeProjectManagementEntity>> employeeDetails = projectService.getEmployeeDetailsByEmployeeId(username);

 	        return employeeDetails.map(ResponseEntity::ok)
 	                .orElse(ResponseEntity.notFound().build());
 	    }
 	  
 	  
    // Update project by ID
    @PutMapping("/update/{projectsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long projectsId, @RequestBody ProjectDto updatedProjectDTO) {
    	ProjectDto updatedProject = projectService.updateProject(projectsId, updatedProjectDTO);
        if (updatedProject != null) {
            logger.info("Updated project with ID: {}", projectsId);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } else {
            logger.warn("Project with ID {} not found for update", projectsId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
 // Update projects by managerEmployeeId
    @PutMapping("/update/manager/{managerEmployeeId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<List<ProjectEntity>> updateProjectsByManagerEmployeeId(
            @PathVariable long managerEmployeeId,
            @RequestBody List<ProjectEntity> updatedProjects) {

        List<ProjectEntity> updatedProjectEntities = projectService.updateProjectsByManagerEmployeeId(managerEmployeeId, updatedProjects);

        if (!updatedProjectEntities.isEmpty()) {
            return new ResponseEntity<>(updatedProjectEntities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Delete Project by ID
    @DeleteMapping("/delete/{projectsId}")
 // @PreAuthorize("hasRole('client_HR')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectsId) {
  	  projectService.deleteProject(projectsId);
        logger.info("Deleted project with ID: {}", projectsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	    
	    @GetMapping("/count/projects")
	 // @PreAuthorize("hasRole('client_HR')")
	    public long countProject()
	    {
	    	return projectService.countProject();
	    }

}
