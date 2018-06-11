package com.otod.server.otod.market.controller;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	@ResponseBody
	@RequestMapping(value="/saveimg",method=RequestMethod.POST)
	public Map<String, String> saveimg(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String path = null;// 文件路径
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
