package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Investment {
    private String investmentId;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private String status;
    private LocalDate deadline;
    private String userId;

    public String getInvestmentId() { return investmentId; }
    public void setInvestmentId(String investmentId) { this.investmentId = investmentId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
