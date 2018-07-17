package com.qa.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountMapRepositoryTest {

	@InjectMocks
	private AccountMapRepository repo;
	
	private Map<Long, Account> list = new HashMap<Long, Account>();
	
	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";

	@Before
	public void setup() {
		repo.setAccountMap(list);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Account account = util.getObjectForJSON(MOCK_OBJECT, Account.class);
		list.put(1L, account);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account has been sucessfully added\"}");
	}

	@Test
	public void testUpdateAccount() {
		String reply = repo.updateAccount(1L, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully updated\"}");
	}

	@Test
	public void testDeleteAccount() {
		String reply = repo.deleteAccount(1L);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully deleted\"}");
	}

}