package com.otod.server.otod.controller.blog;


import com.otod.server.otod.model.blog.ForumTopicPO;
import com.otod.server.otod.model.user.Notice;
import com.otod.server.otod.model.user.User;
import com.otod.server.otod.services.NoticeService;
import com.otod.server.otod.services.UserService;
import com.otod.server.otod.services.blog.ForumTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/forumtopic")
@RestController
@CrossOrigin
public class ForumTopicController {
	
	@Autowired
	ForumTopicService forumTopicService;

	@Autowired
	UserService userService;

	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@RequestBody Map<String, String> map){
		User user=  userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		List<User>  userFollowList = userService.getUserFollowed(user);

		for(User follower: userFollowList) {
			Notice notice = new Notice();
			notice.setAddtime(new Date());
			notice.setObject(123);
			notice.setType(2);
			notice.setTitle("发表了一篇博客");
			notice.setUserOwn(follower);
			notice.setUserOut(user);
			notice.setRead(1);
			noticeService.addNotice(notice);
		}

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
