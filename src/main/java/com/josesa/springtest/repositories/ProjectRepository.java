package com.josesa.springtest.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.josesa.springtest.entity.Person;
import com.josesa.springtest.entity.Person_;
import com.josesa.springtest.entity.Project;
import com.josesa.springtest.entity.Project_;

@SuppressWarnings("unchecked")
public interface ProjectRepository extends  JpaRepository<Project, Integer>, 
											JpaSpecificationExecutor<Project>, 
											QueryDslPredicateExecutor<Project>,
											ProjectRepositoryCustom{
	/**
	 * XXX Si la entidad tiene relaciones eager no habrá hecho joins (n+1)
	 * @param name
	 * @return
	 */
	List<Project> findByName(String name);
	
	/**
	 * XXX No permite hacer fetches!!! Esto generará N+1 queries
	 * @param name
	 * @return
	 */
	Page<Project> findByOwnerId(Integer ownerId, Pageable page);
	
	/**
	 * XXX No permite hacer fetches!!! Esto generará N+1 queries
	 * @param name
	 * @return
	 */
	List<Project> findByOwnerName(String name);
	
	/**
	 * Spring Data JPA con JQPL custom que permite hacer joins y fetches
	 * @param name
	 * @return
	 */
	@Query("from Project p join fetch p.owner o left join fetch p.participants p where o.name = ?1")
	List<Project> findByOwnerName2(String name);
	
	
	/**XXX De esta manera no se hacen fetches, mejor controlar la query como se ve en 
	 * {@link ProjectRepositoryCustom#findProjectsByOwnerNamesAndIdsQ2(String[], Integer[])}*/
	List<Project> findAll(com.mysema.query.types.Predicate pred);
	
	/**
	 * Aproximación pura de Spring Data JPA, es necesario generar Specifications donde se pueden
	 * customizar los joins y fetches {@link ProjectSpecification}. También permite pasar {@link Pageable}
	 */
	List<Project> findAll(Specification<Project> spec);
	
	public class ProjectSpecification{
		public static Specification<Project> isNameInList(final String ... names) {
			return new Specification<Project>() {
				public Predicate toPredicate(Root<Project> root,
						CriteriaQuery<?> query, CriteriaBuilder builder) {
					Join<Project, Person> people = (Join<Project, Person>)root.fetch(Project_.owner, JoinType.LEFT);
					root.fetch(Project_.participants, JoinType.LEFT);
					return people.get(Person_.name).in((Object[])names);
				}
			};
		}
		public static Specification<Project> isIdInList(final Integer ... ids) {
			return new Specification<Project>() {
				public Predicate toPredicate(Root<Project> root,
						CriteriaQuery<?> query, CriteriaBuilder builder) {
					Join<Project, Person> people = (Join<Project, Person>)root.fetch(Project_.owner, JoinType.LEFT);
					root.fetch(Project_.participants, JoinType.LEFT);
					return people.get(Person_.id).in((Object[])ids);
				}
			};
		}
	}
}
