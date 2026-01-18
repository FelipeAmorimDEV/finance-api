package br.com.felipeamorimdev.finance_api.domain.application.services;

import br.com.felipeamorimdev.finance_api.domain.application.dtos.CreateAccountDTO;
import br.com.felipeamorimdev.finance_api.domain.application.dtos.UpdateAccountDTO;
import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.repositories.AccountsRepository;
import org.springframework.stereotype.Service;


@Service
public class UpdateAccount {

    AccountsRepository repository;

    public UpdateAccount(AccountsRepository repository){
        this.repository = repository;
    }

    public Account execute(UpdateAccountDTO dto){
        Account account = repository.findById(dto.getAccountId());

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if (!account.getUserId().equals(dto.getUserId())) {
            throw new IllegalArgumentException("Access denied");
        }

        if (dto.getName() != null) {
            account.setName(dto.getName());
        }
        
        if (dto.getDescription() != null) {
            account.setDescription(dto.getDescription());
        }
        
        if (dto.getIcon() != null) {
            account.setIcon(dto.getIcon());
        }
        
        if (dto.getColor() != null) {
            account.setColor(dto.getColor());
        }

        if (dto.getType() != null) {
            // RF04: Não pode alterar tipo se existirem transações vinculadas
            // TODO: Verificar se existem transações quando o módulo de transações for implementado
            account.setType(dto.getType());
        }

        return repository.update(account);
    }
}
