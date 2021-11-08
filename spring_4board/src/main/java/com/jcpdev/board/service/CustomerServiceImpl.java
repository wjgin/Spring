package com.jcpdev.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jcpdev.board.dao.CustomerMapper;
import com.jcpdev.board.model.Customer;

@Service	// bean생성을 어노테이션
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerMapper dao;
	
	// 생성자 자동주입 @Autowired 생략(더 권장됨)
	public CustomerServiceImpl(CustomerMapper dao) {	
		this.dao = dao;
	}
	
	@Override
	public Customer login(Customer user) {
		// TODO Auto-generated method stub
		return dao.login(user);
	}

	@Override
	public Customer selectOne(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Customer customer) {
		// TODO Auto-generated method stub
		return dao.insert(customer);
	}

	@Override
	public int update(Customer customer) {
		// TODO Auto-generated method stub
		return dao.update(customer);
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Customer> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
