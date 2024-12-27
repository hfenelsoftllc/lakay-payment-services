package com.hfenelsoftllc.Models;

import java.math.BigDecimal;

public class Account {
    private String accountNumber;
    private String accountName;
    private BigDecimal accountBalance;
    private AccountType accountType = AccountType.DEPOSIT;
    private Routine routine = Routine.ELECTRONIC;
    //lprivate int routineNumber =int.TryParse(random(1, 100000));
    private AccountStatus accountStatus = AccountStatus.OPEN;    

    public Account() {
    }

    public Account(String accountNumber, String accountName, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;        
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    

    public AccountType getAccountType() {
        return accountType;
    }

    public void markAccountTypeChecking() {
        this.accountType = AccountType.CHECKING;
    }
    public void markAccountTypeSavings() {
        this.accountType = AccountType.SAVINGS;
    }
    public void markAccountTypeCredit() {
        this.accountType = AccountType.CREDIT;
    }
    public void markAccountTypeLoan() {
        this.accountType = AccountType.LOAN;
    }
    public void markAccountTypeDeposit() {
        this.accountType = AccountType.DEPOSIT;
    }
    public void markAccountTypeStudent() {
        this.accountType = AccountType.STUDENT;
    }
    public void markAccountTypeFamily() {
        this.accountType = AccountType.FAMILY;
    }
    public void markAccountTypeTeen() {
        this.accountType = AccountType.TEEN;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void markOverdrawn(){        
        this.accountStatus = AccountStatus.OVERDRAWN;
    }
    public void removeOverdrawnStatus(){        
        this.accountStatus = AccountStatus.OPEN;
    }

    public void markClosed(){        
        this.accountStatus = AccountStatus.CLOSED;
        accountBalance = BigDecimal.valueOf(0);
    }
    public void markActive(){        
        this.accountStatus = AccountStatus.ACTIVE;
    }
    public void markInactive(){        
        this.accountStatus = AccountStatus.INACTIVE;
    }
    public void withdraw(BigDecimal amount){
        if(accountBalance.compareTo(amount) >= 0){
            accountBalance = accountBalance.subtract(amount);
        }else{
            markOverdrawn();
        }
    }
    public void deposit(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        else if (amount.compareTo(BigDecimal.valueOf(10000)) >= 0) {
            throw new IllegalArgumentException("Deposit amount must be less than 10000");
        }
        else{
            if(accountStatus == AccountStatus.CLOSED){
                return;
            }
            else {
                accountBalance = accountBalance.add(amount);
                if(accountBalance.compareTo(BigDecimal.valueOf(0)) > 0){
                    removeOverdrawnStatus();
                    //maskActive();
                }
            }         //accountBalance = accountBalance.add(amount);
                
    }
}

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                '}';
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void markRoutineElectronic() {
        this.routine = Routine.ELECTRONIC;
    }
    public void markRoutineWires() {
        this.routine = Routine.WIRES;
    }

    // private void removeOverdrawnStatus() {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
    
}
