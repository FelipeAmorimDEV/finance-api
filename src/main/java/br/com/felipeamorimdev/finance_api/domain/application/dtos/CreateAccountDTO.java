package br.com.felipeamorimdev.finance_api.domain.application.dtos;

import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountStatus;
import br.com.felipeamorimdev.finance_api.domain.enterprise.enums.AccountType;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class CreateAccountDTO {
    @NotNull(message = "Usuário é obrigatório")
    @Positive(message = "Usuário deve ser um id válido")
    private Long userId;
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String description;

    @NotBlank(message = "Moeda é obrigatória")
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Moeda deve seguir o padrão ISO 4217 (ex: BRL, USD)"
    )
    private String currency;
    @NotNull(message = "Tipo da conta é obrigatório")
    private AccountType type;
    @NotNull(message = "Saldo é obrigatório")
    @DecimalMin(value = "0.00", inclusive = true, message = "Saldo não pode ser negativo")
    private Double balance;
    @NotBlank(message = "Cor é obrigatória")
    @Pattern(
            regexp = "^#[0-9A-Fa-f]{6}$",
            message = "Cor deve estar no formato hexadecimal (#AABBCC)"
    )
    private String color;
    private String icon;

    public CreateAccountDTO() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

