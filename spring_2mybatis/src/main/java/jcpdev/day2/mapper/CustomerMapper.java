package jcpdev.day2.mapper;

import com.sun.tools.javac.util.List;

import jcpdev.day2.dto.Customer;

public interface CustomerMapper {
	List<Customer> selectAll();
	Customer selectOne(int idx);
	int insert(Customer dto);
	int update(Customer dto);
	int delete(int idx);
}
