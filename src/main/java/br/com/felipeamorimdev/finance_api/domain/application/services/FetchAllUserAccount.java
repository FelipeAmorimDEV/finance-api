package br.com.felipeamorimdev.finance_api.domain.application.services;

import br.com.felipeamorimdev.finance_api.domain.application.dtos.FetchAccountsDTO;
import br.com.felipeamorimdev.finance_api.domain.application.dtos.UserAccountsResponseDTO;
import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountStatus;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountType;
import br.com.felipeamorimdev.finance_api.domain.enterprise.repositories.AccountsRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchAllUserAccount {

    AccountsRepository repository;

    public FetchAllUserAccount(AccountsRepository repository){
        this.repository = repository;
    }

    public UserAccountsResponseDTO execute(FetchAccountsDTO dto) {
        List<Account> accounts = repository.findAllByUser(
                dto.getUserId(),
                dto.getStatus(),
                dto.getType(),
                dto.getSortBy()
        );

        Double totalBalance = accounts.stream()
                .filter(a -> a.getStatus() == AccountStatus.ACTIVE)
                .mapToDouble(Account::getBalance)
                .sum();

        Map<AccountType, Long> countByType = accounts.stream()
                .collect(Collectors.groupingBy(Account::getType, Collectors.counting()));

        // Patrimônio líquido: Soma de todos os saldos de contas ativas (pode incluir passivos se houver cartões de crédito com saldo devedor no futuro)
        Double netWorth = totalBalance;

        return new UserAccountsResponseDTO(accounts, totalBalance, countByType, netWorth);
    }
}
