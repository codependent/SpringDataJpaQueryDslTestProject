package com.josesa.springtest.service.impl;

import static com.josesa.springtest.repositories.ProjectRepository.ProjectSpecification.isIdInList;
import static com.josesa.springtest.repositories.ProjectRepository.ProjectSpecification.isNameInList;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josesa.springtest.entity.Person;
import com.josesa.springtest.entity.Project;
import com.josesa.springtest.entity.QProject;
import com.josesa.springtest.repositories.PersonRepository;
import com.josesa.springtest.repositories.ProjectRepository;
import com.josesa.springtest.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService{

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Transactional
	public Person createPerson(Person person){
		return personRepository.save(person);
	}
	
	@Transactional(readOnly=true)
	public Person findPerson(Integer id){
		return personRepository.findOne(id);
	}
	
	@Transactional
	public Project createProject(Project proy, Integer owner) {
		Person person = personRepository.findOne(owner);
		proy.setOwner(person);
		return projectRepository.save(proy);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public List<Project> findProjectByName(String name){
		return projectRepository.findByName(name);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Project> findAllProjectsSorted(String ... properties){
		return projectRepository.findAll(new Sort(properties));
	}
	
	@Transactional(readOnly=true)
	public Page<Project> findAllProjectsPaginated(int page, int size, String ... properties){
		return projectRepository.findAll(new PageRequest(page, size, new Sort(properties)));
	}
	
	@Transactional(readOnly=true)
	public Page<Project> findProjectsByOwnerIdPaginated(int ownerId,int page, int size, String ... properties){
		return projectRepository.findByOwnerId(ownerId, new PageRequest(page, size, new Sort(properties)));
	}
	
	@Transactional(readOnly=true)
	public List<Project> findProjectsByOwnerName(String ownerName){
		return projectRepository.findByOwnerName2(ownerName);
	}
	
	@Transactional(readOnly=true)
	public List<Project> findProjectsByOwnerNames(String ...ownerName){
		return projectRepository.findAll(isNameInList(ownerName));
	}
	
	@Transactional(readOnly=true)
	public List<Project> findProjectsByOwnerNamesAndIds(String[]ownerName, Integer ownerId[]){
		return projectRepository.findAll(
				where(isNameInList(ownerName)).and(isIdInList(ownerId)));
	}
	
	@Transactional(readOnly=true)
	public List<Project> findProjectsByOwnerNamesAndIdsQ(String[]ownerName, Integer ownerId[]){
		return projectRepository.findAll(QProject.project.owner.name.in(ownerName)
				.and(QProject.project.owner.id.in(ownerId)));
	}
	
	@Transactional(readOnly=true)
	public List<Project> findProjectsByOwnerNamesAndIdsQ2(String[]ownerName, Integer ownerId[]){
		return projectRepository.findProjectsByOwnerNamesAndIdsQ2(ownerName, ownerId);
	}
	
}
