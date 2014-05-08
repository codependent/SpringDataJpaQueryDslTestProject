package com.josesa.springtest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josesa.springtest.entity.Person;
import com.josesa.springtest.entity.Project;
import com.josesa.springtest.test.dto.PageImplBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/test/applicationContext.xml" })
public class PersonProjectTest {

	@Autowired
	private RestTemplate restTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void testAll() {
		
		try {
			URI uri = new URI("http://localhost:8080/SpringTest/action/people");
			Person person = new Person();
			person.setName("jose");
			ResponseEntity<Person> personEntity1 = restTemplate.postForEntity(uri, person, Person.class);
			
			person = new Person();
			person.setName("luis");
			ResponseEntity<Person> personEntity2 = restTemplate.postForEntity(uri, person, Person.class);
			
			person = new Person();
			person.setName("pepe");
			ResponseEntity<Person> personEntity3 = restTemplate.postForEntity(uri, person, Person.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/"+personEntity1.getBody().getId());
			Project proy = new Project();
			proy.setName("mayhem");
			ResponseEntity<Project> projectEntity1 = restTemplate.postForEntity(uri, proy, Project.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/"+personEntity2.getBody().getId());
			proy = new Project();
			proy.setName("avestruz");
			ResponseEntity<Project> projectEntity2 = restTemplate.postForEntity(uri, proy, Project.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/"+personEntity1.getBody().getId());
			proy = new Project();
			proy.setName("ultra64");
			ResponseEntity<Project> projectEntity3 = restTemplate.postForEntity(uri, proy, Project.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/"+personEntity3.getBody().getId());
			proy = new Project();
			proy.setName("pepitoproy");
			ResponseEntity<Project> projectEntity4 = restTemplate.postForEntity(uri, proy, Project.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/"+personEntity1.getBody().getId()+"/page/0/size/100/sort/NAME");
			ResponseEntity<String> owner1ProjectsStr = restTemplate.getForEntity(uri, String.class);
			Page<Project> owner1Projects = ((PageImplBean<Project>)objectMapper.readValue(owner1ProjectsStr.getBody(), new TypeReference<PageImplBean<Project>>() {})).pageImpl();
			assertEquals(2, owner1Projects.getTotalElements());
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/"+personEntity2.getBody().getId()+"/page/0/size/100/sort/NAME");
			ResponseEntity<String> owner2ProjectsStr = restTemplate.getForEntity(uri, String.class);
			Page<Project> owner2Projects = ((PageImplBean<Project>)objectMapper.readValue(owner2ProjectsStr.getBody(), new TypeReference<PageImplBean<Project>>() {})).pageImpl();
			assertEquals(1, owner2Projects.getTotalElements());
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/name/"+personEntity1.getBody().getName());
			ResponseEntity<List> owner1List = restTemplate.getForEntity(uri, List.class);
			assertEquals(2, owner1List.getBody().size());
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/names/jose-pepe");
			ResponseEntity<List> projectList = restTemplate.getForEntity(uri, List.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/owner/names/jose-pepe/ids/1-20");
			ResponseEntity<List> projectList2 = restTemplate.getForEntity(uri, List.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/Q/owner/names/jose-pepe/ids/1-20");
			ResponseEntity<List> projectList3 = restTemplate.getForEntity(uri, List.class);
			
			uri = new URI("http://localhost:8080/SpringTest/action/projects/Q2/owner/names/jose-pepe/ids/1-20");
			ResponseEntity<List> projectList4 = restTemplate.getForEntity(uri, List.class);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
