package br.com.felipeamorimdev.finance_api.infra.database.mapper;

import br.com.felipeamorimdev.finance_api.domain.application.dtos.CreateAccountDTO;
import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.infra.database.models.AccountModel;

public class AccountMapper {

    public static AccountModel toModel(Account dto){
        AccountModel model = new AccountModel();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setCurrency(dto.getCurrency());
        model.setType(dto.getType());
        model.setBalance(dto.getBalance());
        model.setColor(dto.getColor());
        model.setIcon(dto.getIcon());
        model.setStatus(dto.getStatus());
        model.setUserId(dto.getUserId());
        model.setId(dto.getId());
        model.setCreatedAt(dto.getCreatedAt());
        model.setUpdatedAt(dto.getUpdatedAt());
        model.setDeletedAt(dto.getDeletedAt());

        return model;
    }

    public static Account toEntity(AccountModel dto){
            Account account = new Account();
            account.setId(dto.getId());
            account.setName(dto.getName());
            account.setDescription(dto.getDescription());
            account.setCurrency(dto.getCurrency());
            account.setType(dto.getType());
            account.setBalance(dto.getBalance());
            account.setColor(dto.getColor());
            account.setIcon(dto.getIcon());
            account.setStatus(dto.getStatus());
            account.setUserId(dto.getUserId());
            account.setCreatedAt(dto.getCreatedAt());
            account.setUpdatedAt(dto.getUpdatedAt());
            account.setDeletedAt(dto.getDeletedAt());
            return account;
    }
}
