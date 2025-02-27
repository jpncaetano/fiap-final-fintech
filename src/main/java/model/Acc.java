package model;
import java.math.BigDecimal;

public class Acc {
    private String accId;
    private String agency;
    private String accNum;
    private BigDecimal balance;
    private String userId;
    private String bankId;

    public String getAccId() { return accId; }
    public void setAccId(String accId) { this.accId = accId; }

    public String getAgency() { return agency; }
    public void setAgency(String agency) { this.agency = agency; }

    public String getAccNum() { return accNum; }
    public void setAccNum(String accNum) { this.accNum = accNum; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getBankId() { return bankId; }
    public void setBankId(String bankId) { this.bankId = bankId; }
}
