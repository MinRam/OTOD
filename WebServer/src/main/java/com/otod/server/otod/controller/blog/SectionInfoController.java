package com.otod.server.otod.controller.blog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.otod.server.otod.model.blog.SectionInfoPO;
import com.otod.server.otod.pojos.ResultVo;
import com.otod.server.otod.services.blog.SectionInfoService;

@RequestMapping("/sectioninfo")
@RestController
@CrossOrigin
public class SectionInfoController {
	
	@Autowired
	private SectionInfoService sectionInfoService;
	
	 @Value("${cbs.imagesPath}")
	 private String webUploadPath;
	
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
	public ResultVo saveimg(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ResultVo resultVo = new ResultVo();
		if (!file.isEmpty()) {
			if (file.getContentType().contains("image")) {
				 {
					String temp = "blog" + File.separator;
					// 获取图片的文件名
					  String fileName = file.getOriginalFilename();
					  // 获取图片的扩展名
					  String extensionName = StringUtils.substringAfter(fileName, ".");
					  // 新的图片文件名 = 获取时间戳+"."图片扩展名
					  String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
					  // 数据库保存的目录
					  String datdDirectory = temp;
					  // 文件路径
					  String filePath = webUploadPath.concat(datdDirectory);
					  File dest = new File(filePath, newFileName);
					  if (!dest.getParentFile().exists()) {
						  dest.getParentFile().mkdirs();
					  }
					  System.out.println(dest.getPath());
					  file.transferTo(dest);
					  // 将反斜杠转换为正斜杠
					  String data = datdDirectory.replaceAll("\\\\", "/") + newFileName;
					  String url = "http://localhost:8081/" + data ;
					  Map<String, String> resultMap = new HashMap<>();
					  resultMap.put("file", data);
					  resultMap.put("url", url);
					  resultVo.setData(resultMap);
					  resultVo.setError(1, "上传成功!");
				} 
			} else {
				resultVo.setError(0, "上传的文件不是图片类型，请重新上传!");
			}
			return resultVo;
		} else {
			 resultVo.setError(0, "上传失败，请选择要上传的图片!");
			 return resultVo;
		}
	}
}
