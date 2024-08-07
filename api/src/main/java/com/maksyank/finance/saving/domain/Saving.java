package com.maksyank.finance.saving.domain;

import com.maksyank.finance.saving.domain.businessrules.InitRulesSaving;
import com.maksyank.finance.saving.domain.enums.CurrencyCode;
import com.maksyank.finance.saving.domain.enums.SavingState;
import com.maksyank.finance.saving.domain.enums.RiskProfileType;
import com.maksyank.finance.user.domain.UserAccount;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "saving")
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_saving")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private SavingState state;
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currency;
    @Column(name = "description")
    private String description;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "target_amount")
    private BigDecimal targetAmount;
    @Column(name = "deadline")
    private LocalDate deadline;
    @Column(name = "risk_profile")
    @Enumerated(EnumType.STRING)
    private RiskProfileType riskProfile;
    @Embedded
    private ImageSaving image;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "last_change")
    private LocalDateTime lastChange;

    @ManyToOne
    @JoinColumn(name = "id_user_account")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "saving", fetch = FetchType.LAZY)
    private Collection<Transaction> transactions;

    public Saving(InitRulesSaving initRulesSaving, String title, CurrencyCode currency, String description,
                  BigDecimal targetAmount, LocalDate deadline, RiskProfileType riskProfile,
                  ImageSaving image, UserAccount userAccount
    ) {
        this.title = title;
        this.state = initRulesSaving.state();
        this.currency = currency;
        this.description = description;
        this.balance = initRulesSaving.balance();
        this.targetAmount = targetAmount;
        this.deadline = deadline;
        this.riskProfile = riskProfile;
        this.image = image;
        this.userAccount = userAccount;
    }

    public Saving(int id, String title, SavingState state, CurrencyCode currency, String description, BigDecimal balance,
                  BigDecimal targetAmount, LocalDate deadline, RiskProfileType riskProfile,
                  ImageSaving image, LocalDateTime createdOn, LocalDateTime lastChange, UserAccount userAccount
    ) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.currency = currency;
        this.description = description;
        this.balance = balance;
        this.targetAmount = targetAmount;
        this.deadline = deadline;
        this.riskProfile = riskProfile;
        this.image = image;
        this.createdOn = createdOn;
        this.lastChange = lastChange;
        this.userAccount = userAccount;
    }

    // TODO will think if there is need add image to toString
    @Override
    public String toString() {
        return "Saving(id=" + this.getId() + ", title=" + this.getTitle() +
                ", state=" + this.getState() + ", currencyCode=" + this.getCurrency() +
                ", description=" + this.getDescription() + ", amount=" + this.getBalance() +
                ", targetAmount=" + this.getTargetAmount() + ", deadline=" + this.getDeadline() +
                ", riskProfile=" + this.getRiskProfile() + ", createdOn=" + this.getCreatedOn() +
                ", lastChange=" + this.getLastChange() + ", userAccountId=" + this.getUserAccount().getId() + ")";
    }
}
