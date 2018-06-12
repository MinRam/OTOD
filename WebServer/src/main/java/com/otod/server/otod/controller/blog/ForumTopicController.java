package com.otod.server.otod.controller.blog;



import com.otod.server.otod.model.blog.ForumTopicPO;
import com.otod.server.otod.services.blog.ForumTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
	public Page<ForumTopicPO> findbycondition(@RequestBody Map<String, String> map){
		    return forumTopicService.findByConditions(map, Integer.parseInt(map.get("page")),Integer.parseInt(map.get("row")));
	}	

}
