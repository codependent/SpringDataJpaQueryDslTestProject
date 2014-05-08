package com.josesa.springtest.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josesa.springtest.entity.Project;
import com.josesa.springtest.enu.ProjectSortType;
import com.josesa.springtest.service.BusinessService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value="/owner/{owner}",method=RequestMethod.POST)
	public @ResponseBody Project create(@PathVariable Integer owner, @RequestBody Project project){
		project = businessService.createProject(project, owner);
		return project;
	}
	
	@RequestMapping("/{name}")
	public @ResponseBody List<Project> listByName(@PathVariable String name, HttpServletResponse response){
		List<Project> mayhem = businessService.findProjectByName(name);
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("/sort/{type}")
	public @ResponseBody Iterable<Project> listSorted(@PathVariable ProjectSortType type, HttpServletResponse response){
		Iterable<Project> mayhem = businessService.findAllProjectsSorted(type.getType());
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("/page/{page}/size/{size}/sort/{type}")
	public @ResponseBody Iterable<Project> listPaginatedSorted(
			@PathVariable Integer page,
			@PathVariable Integer size, 
			@PathVariable ProjectSortType type,
			HttpServletResponse response){
		Page<Project> mayhem = businessService.findAllProjectsPaginated(page, size, type.getType());
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("/owner/{ownerId}/page/{page}/size/{size}/sort/{type}")
	public @ResponseBody Iterable<Project> listPaginatedSorted(
			@PathVariable Integer ownerId,
			@PathVariable Integer page,
			@PathVariable Integer size, 
			@PathVariable ProjectSortType type,
			HttpServletResponse response){
		//XXX Aquí no podemos hacer fetch luego hay que quitar los lazy
		Page<Project> mayhem = businessService.findProjectsByOwnerIdPaginated(ownerId, page, size, type.getType());
		for (Project project : mayhem) {
			project.setParticipants(null);
		}
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("/owner/name/{ownerName}")
	public @ResponseBody List<Project> findByOwnerName(
			@PathVariable String ownerName,
			HttpServletResponse response){
		List<Project> mayhem = businessService.findProjectsByOwnerName(ownerName);
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("/owner/names/{ownerNames}")
	public @ResponseBody List<Project> findByOwnerNames(
			@PathVariable String ownerNames, HttpServletResponse response){
		String[] split = ownerNames.split("-");
		List<Project> mayhem = businessService.findProjectsByOwnerNames(split);
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("/owner/names/{ownerNames}/ids/{ownerIds}")
	public @ResponseBody List<Project> findByOwnerNamesIds(
			@PathVariable String ownerNames,
			@PathVariable String ownerIds,
			HttpServletResponse response){
		String[] split = ownerNames.split("-");
		String[] splitIds = ownerIds.split("-");
		List<Integer> ids = new ArrayList<Integer>();
		for (String idsStr : splitIds) {
			ids.add(Integer.parseInt(idsStr));
		}
		List<Project> mayhem = businessService.findProjectsByOwnerNamesAndIds(split, ids.toArray(new Integer[0]));
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("Q/owner/names/{ownerNames}/ids/{ownerIds}")
	public @ResponseBody List<Project> findByOwnerNamesIdsQ(
			@PathVariable String ownerNames,
			@PathVariable String ownerIds,
			HttpServletResponse response){
		String[] split = ownerNames.split("-");
		String[] splitIds = ownerIds.split("-");
		List<Integer> ids = new ArrayList<Integer>();
		for (String idsStr : splitIds) {
			ids.add(Integer.parseInt(idsStr));
		}
		List<Project> mayhem = businessService.findProjectsByOwnerNamesAndIdsQ(split, ids.toArray(new Integer[0]));
		response.setContentType("application/json");
		return mayhem;
	}
	
	@RequestMapping("Q2/owner/names/{ownerNames}/ids/{ownerIds}")
	public @ResponseBody List<Project> findByOwnerNamesIdsQ2(
			@PathVariable String ownerNames,
			@PathVariable String ownerIds,
			HttpServletResponse response){
		String[] split = ownerNames.split("-");
		String[] splitIds = ownerIds.split("-");
		List<Integer> ids = new ArrayList<Integer>();
		for (String idsStr : splitIds) {
			ids.add(Integer.parseInt(idsStr));
		}
		List<Project> mayhem = businessService.findProjectsByOwnerNamesAndIdsQ2(split, ids.toArray(new Integer[0]));
		response.setContentType("application/json");
		return mayhem;
	}
}
