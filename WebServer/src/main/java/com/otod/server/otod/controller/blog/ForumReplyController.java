package com.otod.server.otod.controller.blog;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otod.server.otod.model.blog.ForumReplyPO;
import com.otod.server.otod.services.blog.ForumReplyService;


@RequestMapping("/forumreply")
@RestController
@CrossOrigin
public class ForumReplyController {
	
	@Autowired
	private ForumReplyService forumReplyService;
	
	@RequestMapping("/save")
	public void save(@RequestBody Map<String, String> map){
		forumReplyService.save(map);
	}
	
	@RequestMapping("/findbypage")
	public Page<ForumReplyPO> findbypage(Integer page,Integer row){
		return forumReplyService.findByPage(page,row);
	}
	
	@RequestMapping("/findbyconditions")
	public Page<ForumReplyPO> findbyconditions(@RequestBody Map<String, String> map){
		return forumReplyService.findByConditions(map, Integer.parseInt(map.get("page")),Integer.parseInt(map.get("row")));
	}
}
