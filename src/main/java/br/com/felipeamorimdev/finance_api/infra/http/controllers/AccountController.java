package br.com.felipeamorimdev.finance_api.infra.http.controllers;

import br.com.felipeamorimdev.finance_api.domain.application.dtos.CreateAccountDTO;
import br.com.felipeamorimdev.finance_api.domain.application.dtos.FetchAccountsDTO;
import br.com.felipeamorimdev.finance_api.domain.application.dtos.UpdateAccountDTO;
import br.com.felipeamorimdev.finance_api.domain.application.dtos.UserAccountsResponseDTO;
import br.com.felipeamorimdev.finance_api.domain.application.services.CreateAccount;
import br.com.felipeamorimdev.finance_api.domain.application.services.FetchAllUserAccount;
import br.com.felipeamorimdev.finance_api.domain.application.services.GetAccountById;
import br.com.felipeamorimdev.finance_api.domain.application.services.UpdateAccount;
import br.com.felipeamorimdev.finance_api.domain.enterprise.entities.Account;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountStatus;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CreateAccount createAccount;
    private final FetchAllUserAccount fetchAllUserAccount;
    private final GetAccountById getAccountById;
    private final UpdateAccount updateAccount;

    public AccountController(
            CreateAccount createAccount,
            FetchAllUserAccount fetchAllUserAccount,
            GetAccountById getAccountById,
            UpdateAccount updateAccount
    ) {
        this.createAccount = createAccount;
        this.fetchAllUserAccount = fetchAllUserAccount;
        this.getAccountById = getAccountById;
        this.updateAccount = updateAccount;
    }

    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody CreateAccountDTO dto) {
        Account account = createAccount.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping
    public ResponseEntity<UserAccountsResponseDTO> list(
            @RequestParam Long userId,
            @RequestParam(required = false) AccountStatus status,
            @RequestParam(required = false) AccountType type,
            @RequestParam(required = false) String sortBy
    ) {
        FetchAccountsDTO dto = new FetchAccountsDTO();
        dto.setUserId(userId);
        dto.setStatus(status);
        dto.setType(type);
        dto.setSortBy(sortBy);

        UserAccountsResponseDTO response = fetchAllUserAccount.execute(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(
            @PathVariable Long id,
            @RequestParam Long userId
    ) {
        Account account = getAccountById.execute(id, userId);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(
            @PathVariable Long id,
            @RequestBody UpdateAccountDTO dto
    ) {
        dto.setAccountId(id);
        Account account = updateAccount.execute(dto);
        return ResponseEntity.ok(account);
    }
}
