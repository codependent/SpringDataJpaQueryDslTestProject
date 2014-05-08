package com.josesa.springtest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.josesa.springtest.entity.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Integer>{

}
