package com.jcpdev.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jcpdev.board.dto.Customer;
import com.jcpdev.board.entity.CustomerEntity;
import com.jcpdev.board.repository.CustomerRepository;
import com.jcpdev.board.service.CustomerService;

@RestController
public class RestCustomerController {
	private static final Logger logger = LoggerFactory.getLogger(RestCustomerController.class);

	@Autowired
	CustomerRepository repository;

	@Autowired
	CustomerService service;

	@RequestMapping(value = "/customers/list", method = RequestMethod.GET)
	@ResponseBody // 리턴이 응답(response)의 본문(body)
	public Map<String, Object> getCustomerList() {
		List<CustomerEntity> list = repository.findAll();
		List<Customer> dtolist = new ArrayList<Customer>();
		list.forEach(item -> {
			dtolist.add(service.toDto(item));
		});
		logger.info("List :" + dtolist);
		Map<String, Object> result = new HashMap<String, Object>(); // html에서 ajax요청했을때 전달할 응답데이터
		result.put("data", dtolist);
		result.put("result", Boolean.TRUE); // 리스트 생성에 성공했을때 리턴 결과 참
		return result;
	}

	@RequestMapping(value = "/customers/regist", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public Map<String, Object> insertUser(@RequestBody Customer customer) {
		if (customer != null) {
			CustomerEntity entity = service.toEntity(customer);
			repository.save(entity);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}

	@RequestMapping(value = "/customers/{idx}", method = RequestMethod.GET) // http://localhost:8085/myrest/users/3
	@ResponseBody
	public Map<String, Object> getUser(@PathVariable int idx) { // idx는 ?로 시작하는 파라미터가 아님
		Optional<CustomerEntity> user = repository.findById(idx);
		Customer customer = null;
		if (user.isPresent())
			customer = service.toDto(user.get());
		Map<String, Object> result = new HashMap<>();
		Boolean state;
		if (customer == null)
			state = false;
		else
			state = true;
		result.put("result", state);
		result.put("data", customer);
		return result;
	}

	@RequestMapping(value = "/customers/{idx}", method = RequestMethod.PUT, headers = {
			"Content-type=application/json" })
	@ResponseBody
	public Map<String, Object> updateUser(@RequestBody Customer user) { // user_json.html의 userUpdate()메소드에서 전달되는데이터
																		// @RequetBody
		if (user != null)
		repository.save(service.toEntity(user));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}

	@RequestMapping(value = "/customers/{idx}", method = RequestMethod.DELETE) // {idx:.+}
	@ResponseBody
	public Map<String, Object> deleteUser(@PathVariable int idx) {
		repository.deleteById(idx);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}

	/*
	 * @ResponseBody 동작 -HTTP 응답 본문(Http response Body)에 메소드 리턴값을 전달한다. -스프링 MVC는
	 * 메서드 반환값 자바객체를 HttpMessageConverter 를 통해 json형식의 String 변환한 후 전달한다.
	 * 
	 */
	/*	
		
	*/
	/*
	 * @RequestBody 동작 -HTTP 요청 본문(HTTP request Body)을 메소드 인자로 전달한다. -스프링 MVC는 인자값을
	 * HttpMessageConverter를 통해 자바 객체로 변환한다. -POST,PUT 메소드에서 사용된다.
	 * 
	 * 
	 * **자바객체와 HTTP 응답(또는 요청)의 본문 간의 변환은 servlet-context.xml에서
	 * RequestMappingHandlerAdapter 빈 객체 생성을 정의하고 여기에 messageConverters 프로퍼티가 설정되어
	 * 있어야 한다.
	 */
	/*	
		
	
		
	*/
}
