package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	private static final Logger LOGGER = Logger.getLogger(AccountDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllAccounts() {
		LOGGER.info("In AccountDBRepository getAllAccounts");
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String accout) {
		LOGGER.info("In AccountDBRepository createAccount");
		Account anAccount = util.getObjectForJSON(accout, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		LOGGER.info("In AccountDBRepository updateAccount");
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		if (accountToUpdate != null) {
			LOGGER.info("Account passed is not null");
			accountFromDB = updatedAccount;
			accountFromDB.setId(id);
			manager.merge(accountFromDB);		
			return "{\"message\": \"account sucessfully updated\"}";
		} else {
			LOGGER.error("Account passed is null");
			return "{\"message\": \"account not updated\"}";
		}

	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		LOGGER.info("In AccountDBRepository deleteAccount");
		Account accountInDB = findAccount(id);
		if (accountInDB != null) {
			LOGGER.info("Account with given id exists in DB");
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	private Account findAccount(Long id) {
		LOGGER.info("In AccountDBRepository findAccount");
		return manager.find(Account.class, id);
	}

	public void setManager(EntityManager manager) {
		LOGGER.info("In AccountDBRepository setManager");
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}

