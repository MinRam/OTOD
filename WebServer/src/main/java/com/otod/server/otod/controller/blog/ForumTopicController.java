package com.otod.server.otod.controller.blog;



import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.Path;
import com.otod.server.otod.model.blog.ForumTopicPO;
import com.otod.server.otod.services.blog.ForumTopicService;

@RequestMapping("/forumtopic")
@RestController
@CrossOrigin
public class ForumTopicController {
	
	@Autowired
	ForumTopicService forumTopicService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@RequestBody Map<String, String> map){
		
		
		forumTopicService.save(map);
		return "success";
	}
	
	@RequestMapping("/deletebyid")
	public String deletebyid(Integer id){
		forumTopicService.deleteByid(id);
		return "success";
	}
	
	@RequestMapping("/findbyid")
	public ForumTopicPO findbyid(Integer id){
		//Optional<ForumTopicPO> f = forumTopicRepository.findById(id);
		return forumTopicService.findById(id);
	}
	
	@RequestMapping("/findbypage")
	public Page<ForumTopicPO> findbypage(Integer page,Integer row){
		return forumTopicService.findByPage(page, row);
	}
	
	@RequestMapping("/findbycondition")
	public Map<String, Object> findbycondition(@RequestBody Map<String, String> map){
		    return forumTopicService.findByConditions(map, Integer.parseInt(map.get("page")),Integer.parseInt(map.get("row")));
	}	

}
