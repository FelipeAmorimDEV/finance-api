package br.com.felipeamorimdev.finance_api.domain.application.services;

import br.com.felipeamorimdev.finance_api.domain.application.dtos.CreateAccountDTO;
import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountStatus;
import br.com.felipeamorimdev.finance_api.domain.enterprise.repositories.AccountsRepository;

public class CreateAccount {

    AccountsRepository repository;

    public CreateAccount(AccountsRepository repository){
        this.repository = repository;
    }

    public Account execute(CreateAccountDTO dto){
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("UserId is required");
        }
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (dto.getType() == null) {
            throw new IllegalArgumentException("Type is required");
        }

        Account account = new Account();
        account.setUserId(dto.getUserId());
        account.setName(dto.getName());
        account.setDescription(dto.getDescription());

        String currency = dto.getCurrency() != null ? dto.getCurrency() : "BRL";
        account.setCurrency(currency);

        account.setType(dto.getType());

        Double balance = dto.getBalance() != null ? dto.getBalance() : 0.0;
        account.setBalance(balance);

        account.setColor(dto.getColor());
        account.setIcon(dto.getIcon());
        account.setStatus(AccountStatus.ACTIVE);

        return repository.save(account);
    }
}
