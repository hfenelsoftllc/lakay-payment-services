package com.hfenelsoftllc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.hfenelsoftllc.Models.Account;
import com.hfenelsoftllc.Models.AccountStatus;
import com.hfenelsoftllc.Models.AccountType;
import com.hfenelsoftllc.Models.Routine;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

@QuarkusTest
class AccountResourceTest {
    // @Test
    // void testWelcomeEndpoint() {
    //     given()
    //       .when().get("/accounts")
    //       .then()
    //          .statusCode(200)
    //          .body(is("Lakay Banking Services API"));
    // }


    @Test
    void testAllAccountsEndpoint() {
       Response result =  
                given()
                    .when().get("/accounts")
                    .then()
                        .statusCode(200)
                        .body(
                            containsString("Checking"),
                            containsString("Savings"),
                            containsString("Credit"),
                            containsString("Loan"),
                            containsString("Deposit"),
                            containsString("Teen"),
                            containsString("Student"),
                            containsString("Family")
                            )
                        .extract()
                        .response();
        List<Account> accounts = result.jsonPath().getList("$");
        assertThat(accounts, not(empty()));
        assertThat(accounts, hasSize(9)); 
    }


    @Test
    void testGetAccountEndpoint() {
       Account account =  given()
                            .when().get("/accounts/{accountNumber}", "1001")
                            .then()
                                .statusCode(200)
                                .extract()
                                .as(Account.class);
        assertThat(account.getAccountNumber(), equalTo("1001"));
        assertThat(account.getAccountName(), equalTo("Checking"));
        assertThat(account.getAccountBalance(), equalTo(new BigDecimal("1000.00")));
        assertThat(account.getAccountType(), equalTo(AccountType.DEPOSIT));
        assertThat(account.getAccountStatus(), equalTo(AccountStatus.OPEN));
        assertThat(account.getRoutine(), equalTo(Routine.ELECTRONIC));

    }

    @Test
    @Order(3)
    void testCreateAccount(){
        Account newAccount = new Account("1009", "Savings", new BigDecimal("20000.00"));
        
        Account returnedAccount =
            given()
                .contentType(ContentType.JSON)
                .body(newAccount)
                .when().post("/accounts")
                .then()
                    .statusCode(201)
                    .extract()
                    .as(Account.class);

        assertThat(returnedAccount, notNullValue());
        assertThat(returnedAccount, equalTo(newAccount));

        Response result = given()
                            .when().get("/accounts")
                            .then()
                                .statusCode(200)
                                .body(
                                    containsString("Checking"),
                                    containsString("Savings"),
                                    containsString("Credit"),
                                    containsString("Loan"),
                                    containsString("Deposit"),
                                    containsString("Teen"),
                                    containsString("Student"),
                                    containsString("Family")
                                )
                                .extract()
                                .response();
        List<Account> accounts = result.jsonPath().getList("$");
        assertThat(accounts, not(empty()));
        assertThat(accounts, hasSize(9));
        
    }

}