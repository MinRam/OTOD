package com.otod.server.otod.market.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.otod.server.otod.market.domain.Product;
import com.otod.server.otod.market.pojo.ProductPojo;
import com.otod.server.otod.market.pojo.ResultVo;
import com.otod.server.otod.market.repository.ProductRepository;
import com.otod.server.otod.market.service.OrderService;
import com.otod.server.otod.market.service.ProductService;

@CrossOrigin
@Controller
@RequestMapping("/market")
public class MarketController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private ProductRepository repository;
	
	@Autowired
	private OrderService OrderService;
	
	 @Value("${cbs.imagesPath}")
	 private String webUploadPath;
	
	@RequestMapping("/product")
	@ResponseBody
	public Product FindById(@RequestParam String product_id){
		System.out.println(product_id);
		Product product = productService.FindById(Integer.parseInt(product_id));
		return product;
	}
	
	@ResponseBody
	@RequestMapping(value="/addproduct",method=RequestMethod.POST)
	public String add(@RequestBody ProductPojo pojo) {
		System.out.println(pojo.toString());
		productService.SaveByPojo(pojo);
		return "success";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Page<Product> search(@RequestParam Map<String, String> map){
		String key = "";
		if(map.containsKey("product_key")){
			key = map.get("product_key");
		}
		System.out.println(key);
		String page_num = map.get("page_num");
		System.out.println(page_num);
		Pageable pageable = new PageRequest(Integer.parseInt(page_num) - 1 , 15);
		Page<Product> page= repository.findByName(key, pageable);
		System.out.println(page.toString());
		return page;
	}
	
	
	@RequestMapping(value="/addorder",method=RequestMethod.POST)
	public void addorder(@RequestParam("uid")String uid,
			@RequestParam("product_num")String product_num,
			@RequestParam("product_id")String product_id){
		OrderService.SaveOrder(Integer.parseInt(uid), Integer.parseInt(product_id), Integer.parseInt(product_num));
	}
	

	@RequestMapping(value="/saveimg",method=RequestMethod.POST)
	@ResponseBody
	public ResultVo saveimg(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ResultVo resultVo = new ResultVo();
		if (!file.isEmpty()) {
			if (file.getContentType().contains("image")) {
				 {
					String temp = "market" + File.separator;
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
					  Map<String, String> resultMap = new HashMap<>();
					  resultMap.put("file", data);
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
