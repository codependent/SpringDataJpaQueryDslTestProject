package com.josesa.springtest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.josesa.springtest.entity.Person;
import com.josesa.springtest.entity.Project;

public interface BusinessService {

	Person createPerson(Person person);
	Person findPerson(Integer id);
	
	Project createProject(Project proy, Integer owner);
	Iterable<Project> findAllProjects();
	Iterable<Project> findAllProjectsSorted(String ... properties);
	Page<Project> findAllProjectsPaginated(int page, int size, String ... properties);
	List<Project> findProjectByName(String projectName);
	Page<Project> findProjectsByOwnerIdPaginated(int owner, int page, int size, String ... properties);
	List<Project> findProjectsByOwnerName(String ownerName);
	List<Project> findProjectsByOwnerNames(String ...ownerName);
	List<Project> findProjectsByOwnerNamesAndIds(String []ownerName, Integer[] ownerId);
	public List<Project> findProjectsByOwnerNamesAndIdsQ(String[]ownerName, Integer ownerId[]);
	public List<Project> findProjectsByOwnerNamesAndIdsQ2(String[]ownerName, Integer ownerId[]);
}