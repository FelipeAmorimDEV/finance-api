package br.com.felipeamorimdev.finance_api.infra.database;

import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountStatus;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountType;
import br.com.felipeamorimdev.finance_api.domain.enterprise.repositories.AccountsRepository;
import br.com.felipeamorimdev.finance_api.infra.database.mapper.AccountMapper;
import br.com.felipeamorimdev.finance_api.infra.database.models.AccountModel;
import br.com.felipeamorimdev.finance_api.infra.exceptions.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountsRepositoryImpl implements AccountsRepository {
    AccountRepositoryJPA repository;
    public AccountsRepositoryImpl(AccountRepositoryJPA repository){
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        AccountModel model = AccountMapper.toModel(account);
        model = repository.save(model);

        return AccountMapper.toEntity(model);
    }

    @Override
    public List<Account> findAllByUser(Long userId) {
        List<AccountModel> accounts = repository.findAllByUserId(userId);

        return accounts.stream().map(AccountMapper::toEntity).toList();
    }



    @Override
    public Account findById(Long id) {
        AccountModel accountModel = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada com ID: " + id));

       return AccountMapper.toEntity(accountModel);
    }

    @Override
    public Account update(Account account) {
        AccountModel accountModel = AccountMapper.toModel(account);
        accountModel = repository.save(accountModel);

        return AccountMapper.toEntity(accountModel);
    }
}
