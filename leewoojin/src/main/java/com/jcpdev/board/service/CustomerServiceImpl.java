package com.jcpdev.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jcpdev.board.dao.CustomerMapper;
import com.jcpdev.board.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper dao;
	
	public CustomerServiceImpl(CustomerMapper dao) { //생성자 자동주입 @Autowired 생략
		this.dao = dao;
	}
	
	@Override
	public Customer login(Customer user) {
		return dao.login(user);
	}

	@Override
	public Customer selectOne(int idx) {
		return dao.selectOne(idx);
	}

	@Override
	public int insert(Customer customer) {
		return dao.insert(customer);
	}

	@Override
	public int update(Customer customer) {
		return dao.update(customer);
	}

	@Override
	public int delete(int idx) {
		return dao.delete(idx);
	}

	@Override
	public List<Customer> selectAll() {
		return dao.selectAll();
	}

}
