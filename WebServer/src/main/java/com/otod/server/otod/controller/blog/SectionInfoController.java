package com.otod.server.otod.controller.blog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.otod.server.otod.model.blog.SectionInfoPO;
import com.otod.server.otod.services.blog.SectionInfoService;

@RequestMapping("/sectioninfo")
@RestController
@CrossOrigin
public class SectionInfoController {
	
	@Autowired
	private SectionInfoService sectionInfoService;
	
	@RequestMapping("/hello")
	public String Hello(){
		return "nihao";
	}
	
	@RequestMapping("/findbyid")
	public SectionInfoPO findbyid(Integer id){
		return sectionInfoService.findbyid(id);
	}
	
	@RequestMapping("/findbypage")
	public Page<SectionInfoPO> findbypage(Integer page,Integer row){
		return sectionInfoService.findbypage(page, row);
	}
	
	@ResponseBody
	@RequestMapping(value="/saveimg",method=RequestMethod.POST)
	public Map<String, String> saveimg(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String path = null;// 文件路径
        String json = "";
        Map<String, String> map = new HashMap<String, String>();
        if (file!=null) {// 判断上传的文件是否为空

            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:"+fileName);
            // 判断文件类型
            type = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                    // 设置存放图片文件的路径
                    path = realPath+trueFileName;
                    System.out.println("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");   
                    map.put("path", path);
                    map.put("res", "1");
                    }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    //return null;
                    map.put("res", "0");
                }
            }else {
                System.out.println("文件类型为空");
                //return null;
                map.put("res", "0");
            }
        }else {
        	System.out.println("文件为空");
        	map.put("res", "0");
        }
        response.setContentType("application/json;charset=UTF-8");
        return map;
	}
}
