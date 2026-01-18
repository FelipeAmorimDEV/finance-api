package br.com.felipeamorimdev.finance_api.domain.enterprise.repositories;

import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountStatus;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountType;

import java.util.List;

public interface AccountsRepository {

    public Account save(Account account);
    public List<Account> findAllByUser(Long userId);
//    public List<Account> findAllByUser(Long userId, AccountStatus status, AccountType type, String sortBy);
    public Account findById(Long id);
    public Account update(Account account);
}
