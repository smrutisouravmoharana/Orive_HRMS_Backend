package com.orive.project.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orive.project.Dto.ProjectDto;
import com.orive.project.Entity.EmployeeProjectManagementEntity;
import com.orive.project.Entity.ProjectEntity;
import com.orive.project.Repository.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {
	
	private static final Logger logger=LoggerFactory.getLogger(ProjectService.class);
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
    public ProjectDto createProject(ProjectDto projectDto) {
    	ProjectEntity projectEntity = convertToEntity(projectDto);
    	ProjectEntity savedProjectEntity = projectRepository.save(projectEntity);
        logger.info("Created project with ID: {}", savedProjectEntity.getProjectsId());
        return convertToDTO(savedProjectEntity);
    }
    
 // Read
    public List<ProjectDto> getAllProject() {
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        logger.info("Retrieved {} project from the database", projectEntities.size());
        return projectEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //get by ProjectId
    public Optional<ProjectDto> getProjectById(Long projectsId) {
        Optional<ProjectEntity> project = projectRepository.findById(projectsId);
        if (project.isPresent()) {
            return Optional.of(convertToDTO(project.get()));
        } else {
            logger.warn("Project with ID {} not found", projectsId);
            return Optional.empty();
        }
    }
    
    
    //get by ManagerEmployeeId
    public List<ProjectDto> getManagerEmployeeId(Long managerEmployeeId) {
        List<ProjectEntity> manager = projectRepository.findByManagerEmployeeId(managerEmployeeId);

        if (manager.isEmpty()) {
            logger.warn("Project with ID {} not found", managerEmployeeId);
            return Collections.emptyList();
        }

        return manager.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    
//    //get by employeeId
//    public Optional<ProjectDto> getProjectsByEmployeeId(Long employeeId) {
//        List<ProjectEntity> projects = projectRepository.findByEmployeeProjectManagementEntities_EmployeeId(employeeId);
//        if (projects.isEmpty()) {
//            return Optional.empty();
//        } else {
//            // Assuming you want to return the first project for simplicity
//            return Optional.of(convertToDTO(projects.get(0)));
//        }
//    }
       
    
    // Get employee details by employeeId
    public Optional<List<EmployeeProjectManagementEntity>> getEmployeeDetailsByEmployeeId(String username) {
        return projectRepository.findEmployeeDetailsByEmployeeId(username);
    }
    
    
    
 // Update list by id
    public ProjectDto updateProject(Long projectsId, ProjectDto projectDto) {
        Optional<ProjectEntity> existingProjectOptional =projectRepository.findById(projectsId);
        if (existingProjectOptional.isPresent()) {
        	ProjectEntity existingProject = existingProjectOptional.get();
        	existingProject.setBudget(projectDto.getBudget());
        	existingProject.setStartDate(projectDto.getStartDate());
        	existingProject.setEndDate(projectDto.getEndDate());
        	existingProject.setClientName(projectDto.getClientName());
        	existingProject.setStatus(projectDto.getStatus());
        	modelMapper.map(projectDto, existingProjectOptional);
           ProjectEntity updatedProject = projectRepository.save(existingProject);
            logger.info("Updated project with ID: {}", updatedProject.getProjectsId());
            return convertToDTO( updatedProject);
        } else {
            logger.warn("projectwith ID {} not found for update", projectsId);
            return null;
        }
    }
    
    
    
 // Update projects by managerEmployeeId
    public List<ProjectEntity> updateProjectsByManagerEmployeeId(long managerEmployeeId, List<ProjectEntity> updatedProjects) {
        List<ProjectEntity> existingProjects = projectRepository.findByManagerEmployeeId(managerEmployeeId);

        if (!existingProjects.isEmpty()) {
            for (ProjectEntity existingProject : existingProjects) {
                // Find the corresponding updated project
                Optional<ProjectEntity> updatedProjectOptional = updatedProjects.stream()
                        .filter(p -> p.getProjectsId() == existingProject.getProjectsId())
                        .findFirst();

                if (updatedProjectOptional.isPresent()) {
                    ProjectEntity updatedProject = updatedProjectOptional.get();

                    // Update fields based on your requirements
                    existingProject.setProjectTitle(updatedProject.getProjectTitle());
                    existingProject.setManagerEmployeeId(updatedProject.getManagerEmployeeId());
                    existingProject.setClientName(updatedProject.getClientName());
                    existingProject.setCompanyName(updatedProject.getCompanyName());
                    existingProject.setStartDate(updatedProject.getStartDate());
                    existingProject.setEndDate(updatedProject.getEndDate());
                    existingProject.setPriority(updatedProject.getPriority());
                    existingProject.setBudget(updatedProject.getBudget());
                    existingProject.setProjectManagers(updatedProject.getProjectManagers());
                    existingProject.setSummary(updatedProject.getSummary());
                    existingProject.setDescription(updatedProject.getDescription());
                    existingProject.setWorkUpdateSheet(updatedProject.getWorkUpdateSheet());
                    existingProject.setStatus(updatedProject.getStatus());
                    // Update or merge the list of EmployeeProjectManagementEntities
                    existingProject.setEmployeeProjectManagementEntities(updatedProject.getEmployeeProjectManagementEntities());

                    // Save the updated project
                    projectRepository.save(existingProject);
                }
            }

            return existingProjects;
        } else {
            logger.warn("Projects with managerEmployeeId {} not found for update", managerEmployeeId);
            throw new EntityNotFoundException("Projects not found with managerEmployeeId: " + managerEmployeeId);
        }
    }
    
    
    
    // Delete
    public void deleteProject(Long projectsId) {
    	projectRepository.deleteById(projectsId);
        logger.info("Deleted project with ID: {}", projectsId);
    }

    //count the total project
    public long countProject()
	 {
		 return projectRepository.count();
	 }
    
	// Helper method to convert ProjectDTo to Project entity
    private ProjectEntity convertToEntity(ProjectDto projectDto)
    {
    	return modelMapper.map(projectDto, ProjectEntity.class);
    }

    // Helper method to convert Company Entity entity to CompanyDTo
    private ProjectDto convertToDTO(ProjectEntity projectEntity) {
        return modelMapper.map(projectEntity, ProjectDto.class);
    } 
}
