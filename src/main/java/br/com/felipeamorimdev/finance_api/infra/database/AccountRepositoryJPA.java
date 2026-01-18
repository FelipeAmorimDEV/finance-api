package br.com.felipeamorimdev.finance_api.infra.database;

import br.com.felipeamorimdev.finance_api.infra.database.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepositoryJPA extends JpaRepository<AccountModel, Long> {

    public List<AccountModel> findAllByUserId(Long userId);
}
