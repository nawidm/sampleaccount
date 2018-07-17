package com.qa.business.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountService;
import com.qa.interoperability.AccountEndPoint;
import com.qa.persistence.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
	
	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";
	
	@InjectMocks
	private AccountServiceImpl service;
	
	@Mock
	private AccountRepository repo;

	@Before
	public void setup() {
		service.setRepo(repo);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(repo.getAllAccounts()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, service.getAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		Mockito.when(repo.createAccount(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, service.addAccount(MOCK_VALUE2));
		Mockito.verify(repo).createAccount(MOCK_VALUE2);
	}

	@Test
	public void testUpdateAccount() {
		Mockito.when(repo.updateAccount(1L, MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, service.updateAccount(1L, MOCK_VALUE2));
		Mockito.verify(repo).updateAccount(1L, MOCK_VALUE2);
	}

	@Test
	public void testDeleteAccount() {
		Mockito.when(repo.deleteAccount(1L)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, service.deleteAccount(1L));
		Mockito.verify(repo).deleteAccount(1L);
	}

}