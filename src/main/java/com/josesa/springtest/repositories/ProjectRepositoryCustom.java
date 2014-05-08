package com.josesa.springtest.repositories;

import java.util.List;

import com.josesa.springtest.entity.Project;

public interface ProjectRepositoryCustom {

	List<Project> findProjectsByOwnerNamesAndIdsQ2(String[] names, Integer ids[]);
	
}
