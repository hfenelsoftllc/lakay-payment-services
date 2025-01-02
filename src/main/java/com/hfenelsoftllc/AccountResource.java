package com.hfenelsoftllc;

import java.math.BigDecimal;
//import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.hfenelsoftllc.Models.Account;

import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Path("/api/v1/accounts")
public class AccountResource {

    // @GET
    // @Produces(MediaType.TEXT_PLAIN)
    // public String welcome() {
    //     return "Lakay Banking Services API";
    // }

    @GET
    // @Path("/api/v1/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> allAccounts(){
        return accounts;
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("accountNumber") String accountNumber){
        return accounts.stream().filter(
                account -> account.getAccountNumber()
                            .equals(accountNumber))
                            .findFirst()
                            .orElseThrow(() ->
                                new WebApplicationException("Account with id of " + accountNumber + " does not exist.", 404)           
                            );
    }

    @Provider
    public static class ErrorMapper implements jakarta.ws.rs.ext.ExceptionMapper<Throwable> {
        @Override
        public Response toResponse(Throwable exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            
            JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
                    .add("exceptionType", exception.getClass().getName())
                    .add("status", code);

            if(exception.getMessage() != null){
                entityBuilder.add("error", exception.getMessage());
            }
            return Response.status(code)
                            .entity(entityBuilder.build())
                            .type(MediaType.APPLICATION_JSON)
                            .build();
        }
    }


    @POST
    // @Path("/api/v1/accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account){
        if(accounts.contains(account)){
            return Response.status(409).entity(account).build();
        }
        else if(account.getAccountNumber() == null){
            throw new WebApplicationException("Account number is required", 400);
            //return Response.status(400).entity(account).build();
        }
        accounts.add(account);
        return Response.status(201).entity(account).build();
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
        accounts.add(new Account("1007", "Teen", new BigDecimal("7000.00")));
        accounts.add(new Account("1008", "Family", new BigDecimal("17000.00")));
    }
}
