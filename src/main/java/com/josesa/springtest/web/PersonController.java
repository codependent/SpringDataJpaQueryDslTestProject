package com.josesa.springtest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josesa.springtest.entity.Person;
import com.josesa.springtest.service.BusinessService;

@Controller
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Person create(@RequestBody Person person){
		person = businessService.createPerson(person);
		return person;
	}
	
}
