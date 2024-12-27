package com.hfenelsoftllc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.hfenelsoftllc.Models.Account;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/accounts")
public class AccountResource {

    // @GET
    // @Produces(MediaType.TEXT_PLAIN)
    // public String welcome() {
    //     return "Lakay Banking Services API";
    // }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> allAccounts(){
        return accounts;
    }

    Set<Account> accounts =  new HashSet<>();


    @PostConstruct
    public void setup(){
        accounts.add(new Account("1001", "Checking", new BigDecimal("1000.00")));
        accounts.add(new Account("1002", "Savings", new BigDecimal("2000.00")));
        accounts.add(new Account("1003", "Credit", new BigDecimal("3000.00")));
        accounts.add(new Account("1004", "Loan", new BigDecimal("4000.00")));
        accounts.add(new Account("1005", "Deposit", new BigDecimal("5000.00")));
        accounts.add(new Account("1006", "Student", new BigDecimal("6000.00")));
        accounts.add(new Account("1007", "Checking", new BigDecimal("7000.00")));
    }
}
