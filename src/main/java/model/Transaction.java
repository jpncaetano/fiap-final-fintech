package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private LocalDateTime date;
    private BigDecimal amount;
    private String type;
    private String status;
    private String accId;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAccId() { return accId; }
    public void setAccId(String accId) { this.accId = accId; }
}