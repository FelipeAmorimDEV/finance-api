package br.com.felipeamorimdev.finance_api.infra.config;

import br.com.felipeamorimdev.finance_api.domain.application.services.CreateAccount;
import br.com.felipeamorimdev.finance_api.domain.application.services.FetchAllUserAccount;
import br.com.felipeamorimdev.finance_api.domain.application.services.GetAccountById;
import br.com.felipeamorimdev.finance_api.domain.application.services.UpdateAccount;
import br.com.felipeamorimdev.finance_api.domain.enterprise.repositories.AccountsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Bean
    public CreateAccount createAccount(AccountsRepository repository) {
        return new CreateAccount(repository);
    }

    @Bean
    public FetchAllUserAccount fetchAllUserAccount(AccountsRepository repository) {
        return new FetchAllUserAccount(repository);
    }

    @Bean
    public GetAccountById getAccountById(AccountsRepository repository) {
        return new GetAccountById(repository);
    }

    @Bean
    public UpdateAccount updateAccount(AccountsRepository repository) {
        return new UpdateAccount(repository);
    }
}
