package com.putnin.controller;

import com.putnin.model.TransferParams;
import com.putnin.service.MoneyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Data base connection pool.
 *
 * @author putnin.v@gmail.com
 */
@Path("money")
public class MoneyController {
    private MoneyService moneyService;

    public MoneyController() {
        moneyService = new MoneyService();
    }

    @GET
    @Path("checkAccountSum/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAccountSum(@PathParam("id") Long accountId) {
        BigDecimal sum = null;
        try {
            sum = moneyService.getAccountSum(accountId);
        } catch (SQLException e) {
            return Response.serverError().build();
        }
        return Response.ok(sum, MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("transfer")
    @Produces(MediaType.TEXT_PLAIN)
    public Response transferMoney(TransferParams params) {
        try {
            moneyService.transferMoneyToPerson(params.getSenderId(),params.getRecipientPhone(), params.getSum());
        } catch (SQLException e) {
            return Response.serverError().build();
        }
        return Response.ok("cuccess", MediaType.TEXT_PLAIN).build();
    }
}
