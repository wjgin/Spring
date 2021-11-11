package com.jcpdev.board.service;

import com.jcpdev.board.dto.Customer;
import com.jcpdev.board.entity.CustomerEntity;

public interface CustomerService {

	default Customer toDto(CustomerEntity entity) {
		Customer cus = Customer.builder().addr(entity.getAddr())
				.age(entity.getAge()).email(entity.getEmail())
				.gender(entity.getGender()).hobby(entity.getHobby())
				.idx(entity.getIdx()).name(entity.getName())
				.password(entity.getPassword()).build();

		return cus;
	}

	default CustomerEntity toEntity(Customer dto) {
		CustomerEntity entity = CustomerEntity.builder().addr(dto.getAddr())
				.age(dto.getAge()).email(dto.getEmail())
				.gender(dto.getGender()).hobby(dto.getHobby())
				.idx(dto.getIdx()).name(dto.getName())
				.password(dto.getPassword()).build();
		return entity;
	}

}