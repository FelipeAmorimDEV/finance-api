package br.com.felipeamorimdev.finance_api.domain.application.services;

import br.com.felipeamorimdev.finance_api.domain.application.dtos.CreateAccountDTO;
import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.repositories.AccountsRepository;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateAccountTest {

    private AccountsRepository repository;
    private CreateAccount createAccount;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(AccountsRepository.class);
        createAccount = new CreateAccount(repository);
    }

    @Test
    void shouldCreateAccountWithZeroBalanceWhenBalanceIsNotProvided() {
        CreateAccountDTO dto = new CreateAccountDTO();
        dto.setUserId(1L);
        dto.setName("Test Account");
        dto.setType(AccountType.CHECKING);
        dto.setBalance(null);

        when(repository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account account = createAccount.execute(dto);

        assertEquals(0.0, account.getBalance());
        assertEquals("BRL", account.getCurrency());
    }

    @Test
    void shouldCreateAccountWithProvidedBalance() {
        CreateAccountDTO dto = new CreateAccountDTO();
        dto.setUserId(1L);
        dto.setName("Test Account");
        dto.setType(AccountType.CHECKING);
        dto.setBalance(100.0);

        when(repository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account account = createAccount.execute(dto);

        assertEquals(100.0, account.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenRequiredFieldsAreMissing() {
        CreateAccountDTO dto = new CreateAccountDTO();
        // missing userId, name, type

        assertThrows(IllegalArgumentException.class, () -> createAccount.execute(dto));
    }
}
