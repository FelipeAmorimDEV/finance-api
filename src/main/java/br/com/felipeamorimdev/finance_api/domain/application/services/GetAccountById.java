package br.com.felipeamorimdev.finance_api.domain.application.services;

import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.repositories.AccountsRepository;

import java.util.List;

public class GetAccountById {

    AccountsRepository repository;

    public GetAccountById(AccountsRepository repository){
        this.repository = repository;
    }

    public Account execute(Long accountId, Long userId) {
        Account account = repository.findById(accountId);

        if(account == null){
            throw new IllegalArgumentException("Account not found");
        }

        if(!account.getUserId().equals(userId)){
            throw new IllegalArgumentException("Access denied");
        }

        return account;
    }
}
