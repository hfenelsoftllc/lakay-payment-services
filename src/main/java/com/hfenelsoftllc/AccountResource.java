package com.hfenelsoftllc;

import java.util.Collections;
import java.util.Set;

import com.hfenelsoftllc.Models.Account;

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
    public Set<Account> allAccounts() {
        return Collections.emptySet();
    }
}
