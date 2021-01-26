package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Long >{
	public List<UserAccount> findAll();
}
