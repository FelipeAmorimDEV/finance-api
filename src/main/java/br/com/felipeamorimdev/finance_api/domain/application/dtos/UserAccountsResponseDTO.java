package br.com.felipeamorimdev.finance_api.domain.application.dtos;

import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountType;

import java.util.List;
import java.util.Map;

public class UserAccountsResponseDTO {
    private List<Account> accounts;
    private Double totalBalance;
    private Map<AccountType, Long> accountCountByType;
    private Double netWorth;

    public UserAccountsResponseDTO(List<Account> accounts, Double totalBalance, Map<AccountType, Long> accountCountByType, Double netWorth) {
        this.accounts = accounts;
        this.totalBalance = totalBalance;
        this.accountCountByType = accountCountByType;
        this.netWorth = netWorth;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public Map<AccountType, Long> getAccountCountByType() {
        return accountCountByType;
    }

    public Double getNetWorth() {
        return netWorth;
    }
}
