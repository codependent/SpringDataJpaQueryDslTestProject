package com.josesa.springtest.repositories.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.josesa.springtest.entity.Project;
import com.josesa.springtest.entity.QPerson;
import com.josesa.springtest.entity.QProject;
import com.josesa.springtest.repositories.ProjectRepositoryCustom;

public class ProjectRepositoryImpl extends QueryDslRepositorySupport implements ProjectRepositoryCustom {

	public ProjectRepositoryImpl() {
		super(Project.class);
	}

	@Override
	public List<Project> findProjectsByOwnerNamesAndIdsQ2(String[] names, Integer[] ids) {
		QProject project = QProject.project;
		QPerson owner = new QPerson("owner");
		List<Project> projects = from(project)
									.leftJoin(project.owner, owner).fetch()
									.leftJoin(project.participants).fetch()
		.where(owner.name.eq(names[0]).and(owner.id.eq(ids[0]))).list(project);
		return projects;
	}

}
