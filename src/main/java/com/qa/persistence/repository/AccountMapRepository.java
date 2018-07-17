package com.qa.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Transaction;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountMapRepository implements AccountRepository {

	private static final Logger LOGGER = Logger.getLogger(AccountMapRepository.class);

	
	private final Long INITIAL_COUNT = 1L;
	private Map<Long, Account> accountMap;
	private Long ID;

	@Inject
	private JSONUtil util;

	public AccountMapRepository() {
		this.accountMap = new HashMap<Long, Account>();
		ID = INITIAL_COUNT;
		initAccountMap();
	}

	public String getAllAccounts() {
		LOGGER.info("In AccountMapRepository getAllAccounts");
		return util.getJSONForObject(accountMap.values());
	}

	public String createAccount(String account) {
		LOGGER.info("In AccountMapRepository createAccount");
		ID++;
		Account newAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(ID, newAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	public String updateAccount(Long id, String accountToUpdate) {
		LOGGER.info("In AccountMapRepository updateAccount");
		Account newAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		accountMap.put(id, newAccount);
		return "{\"message\": \"account sucessfully updated\"}";
	}

	public String deleteAccount(Long id) {
		LOGGER.info("In AccountMapRepository deleteAccount");
		accountMap.remove(id);
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	private void initAccountMap() {
		LOGGER.info("In AccountMapRepository initAccountMap");
		Transaction transaction = new Transaction("sample");
		transaction.setId(1L);
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		Account account = new Account("Joe", "Bloggs", "1234",transactions);
		accountMap.put(1L, account);
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}


	public void setAccountMap(Map<Long, Account> accountMap) {
		this.accountMap = accountMap;
	}


}

