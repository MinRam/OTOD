package com.otod.server.otod.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.otod.server.otod.model.Market_user;
import com.otod.server.otod.model.P_Order;
import com.otod.server.otod.model.PageModel;
import com.otod.server.otod.model.Product;
import com.otod.server.otod.model.user.UserInfo;
import com.otod.server.otod.pojos.P_OrderPojo;
import com.otod.server.otod.pojos.PayPojo;
import com.otod.server.otod.pojos.ProductPojo;
import com.otod.server.otod.pojos.ResultVo;
import com.otod.server.otod.respository.MURepository;
import com.otod.server.otod.respository.P_OrderRepository;
import com.otod.server.otod.respository.ProductRepository;
import com.otod.server.otod.services.OrderService;
import com.otod.server.otod.services.ProductService;
import com.otod.server.otod.services.UserService;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private P_OrderRepository orderRepository;
	
	@Autowired
	private MURepository mURepository;
	
	@Autowired
	private OrderService orderService;
	
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
		UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		productService.SaveByPojo(pojo,userInfo);
		return "success";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> search(@RequestParam Map<String, String> map){
		String key = "";
		if(map.containsKey("product_key")){
			key = map.get("product_key");
		}
		String page_num = map.get("page_num");
		List<Product> list = repository.FindByName(key);
		list = productService.ManageList(list);
		PageModel pm = new PageModel(list, 10);
		List<Product> products = pm.getObjects(Integer.parseInt(page_num));
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("products", products);
		map2.put("totalElements", pm.getTotalRows());
		map2.put("totalPages", pm.getTotalPages());
		return map2;
	}
	
	
	@RequestMapping(value="/addorder",method=RequestMethod.POST)
	@ResponseBody
	public String addorder(@RequestBody P_OrderPojo pojo){
		UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		Market_user mUser = mURepository.findByUserInfo(userInfo);
		OrderService.SaveByPojo(pojo,mUser);
		return "success";
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
	
	@RequestMapping(value="/getmuser",method=RequestMethod.POST)
	@ResponseBody
	public int GetMuser(){
		UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		Market_user mUser = mURepository.findByUserInfo(userInfo);
		return mUser.getMarket_user_id();
	}
	
	@RequestMapping(value="/getPbyMU",method=RequestMethod.POST)
	@ResponseBody
	public Page<Product> GetPbyMU(@RequestParam Map<String, String> map){
		UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		Market_user mUser = mURepository.findByUserInfo(userInfo);
		String page_num = map.get("page_num");
		Pageable pageable = new PageRequest(Integer.parseInt(page_num) - 1 , 10 , Sort.Direction.DESC,"product_createtime");
		return repository.findBySeller(mUser.getMarket_user_id(), pageable);
	}
	
	@RequestMapping(value="/getObyMU",method=RequestMethod.POST)
	@ResponseBody
	public List<PayPojo> GetObyMU(@RequestParam Map<String, String> map){
		UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		Market_user mUser = mURepository.findByUserInfo(userInfo);
		String page_num = map.get("page_num");
		Pageable pageable = new PageRequest(Integer.parseInt(page_num) - 1 , 10 , Sort.Direction.DESC,"createtime");
		Page<P_Order> orders = orderRepository.findByBuyer(mUser,pageable);
		List<PayPojo> list = new ArrayList<PayPojo>();
		
		for (int i = 0; i < orders.getContent().size(); i++){
			PayPojo pojo = new PayPojo();
			pojo.setOrder_id(orders.getContent().get(i).getOrder_id());
			pojo.setProduct_id(orders.getContent().get(i).getProduct().getProduct_id());
			pojo.setCreatetime(orders.getContent().get(i).getCreatetime());
			pojo.setOrder_encoding(orders.getContent().get(i).getUser_order_encoding());
			pojo.setProduct_encoding(orders.getContent().get(i).getProduct().getProduct_encoding());
			pojo.setProduct_img_url(orders.getContent().get(i).getProduct().getProduct_img_url());
			pojo.setProduct_name(orders.getContent().get(i).getProduct().getProduct_name());
			pojo.setProduct_num(orders.getContent().get(i).getProduct_num());
			pojo.setProduct_price(orders.getContent().get(i).getProduct().getProduct_price());
			pojo.setOrderstatus(orders.getContent().get(i).getStatus());
			list.add(pojo);
		}
		return list;
	}
	
	@RequestMapping(value="/BuyerOrderManage",method=RequestMethod.POST)
	@ResponseBody
	public String BuyerOrderMange(@RequestParam Map<String, String> map){
		String order_id = map.get("order_id");
		String operation = map.get("operation");
		System.out.println(operation);
		P_Order order = orderRepository.findById(Integer.parseInt(order_id)).get();
		
		//申请取消订单 10
		if (operation.equals("10")) {
			orderService.CancelOrder(order);
		}
		
		//确认收货 9
		if (operation.equals("9")) {
			orderService.Deliver(order);
		}
		
		//申请退货
		if (operation.equals("7")) {
			orderService.Return(order);
		}
		
		//取消申请取消订单 12
		if (operation.equals("12") ) {
			orderService.CancelCancelOrder(order);
		}
		
		//取消申请退货
		if (operation.equals("13")) {
			orderService.CancelReturn(order);
		}
		
		return "success";
	}
	@RequestMapping(value="/getObySeller",method=RequestMethod.POST)
	@ResponseBody
	public List<PayPojo> getObySeller(@RequestParam Map<String, String> map)
	{
		UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		Market_user mUser = mURepository.findByUserInfo(userInfo);
		String page_num = map.get("page_num");
		Pageable pageable = new PageRequest(Integer.parseInt(page_num) - 1 , 10 , Sort.Direction.DESC,"product_createtime");
		Page<Product> products = repository.findBySeller(mUser.getMarket_user_id(), pageable);
		List<PayPojo> list = new ArrayList<PayPojo>();
		for(int i = 0; i < products.getContent().size(); i++){
			Iterator<P_Order> iterator = products.getContent().get(i).getOrders().iterator();
			while(iterator.hasNext()){
				P_Order temp = iterator.next();
				PayPojo pojo = new PayPojo();
				pojo.setCreatetime(temp.getCreatetime());
				pojo.setOrder_encoding(temp.getUser_order_encoding());
				pojo.setOrder_id(temp.getOrder_id());
				pojo.setOrderstatus(temp.getStatus());
				pojo.setProduct_encoding(products.getContent().get(i).getProduct_encoding());
				pojo.setProduct_id(products.getContent().get(i).getProduct_id());
				pojo.setProduct_img_url(products.getContent().get(i).getProduct_img_url());
				pojo.setProduct_name(products.getContent().get(i).getProduct_name());
				pojo.setProduct_num(temp.getProduct_num());
				pojo.setProduct_price(products.getContent().get(i).getProduct_price());
				list.add(pojo);
			}
		}
		return list;
	}
	@RequestMapping(value="/SellerOrderManage",method=RequestMethod.POST)
	@ResponseBody
	public String SellerOrderMange(@RequestParam Map<String, String> map){
		String order_id = map.get("order_id");
		String operation = map.get("operation");
		P_Order order = orderRepository.findById(Integer.parseInt(order_id)).get();
		
		//发货完成
		if (operation.equals("5")) {
			orderService.Deliver(order);
		}
		
		//同意取消订单
		if (operation.equals("11")) {
			orderService.CancelOrder(order);
		}
		
		//同意退货
		if (operation.equals("8")) {
			orderService.Return(order);
		}
		
		//确认退货
		if (operation.equals("9")) {
			orderService.Return(order);
		}
		return "success";
	}
	
	@RequestMapping(value="/UpdateProduct",method=RequestMethod.POST)
	@ResponseBody
	public String UpdateProduct(@RequestBody Map<String, Object>map){
		UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		Market_user mUser = mURepository.findByUserInfo(userInfo);
		productService.UpdateByMap(map,mUser);
		return "success";
	}
	
	@RequestMapping(value="/DeleteProduct",method=RequestMethod.POST)
	@ResponseBody
	public String DeleteProduct(@RequestParam int product_id){
		productService.DeleteProduct(product_id);
		return "success";
	}
}
