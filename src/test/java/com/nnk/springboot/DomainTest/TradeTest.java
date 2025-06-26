package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.Trade;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class TradeTest {

    @Test
    public void testTradeSettersAndGetters() {
        // GIVEN
        Trade trade = new Trade();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // WHEN
        trade.setAccount("Trade Account");
        trade.setType("Type");
        trade.setBuyQuantity(100.0);
        trade.setTradeDate(now);

        // THEN
        assertEquals("Trade Account", trade.getAccount());
        assertEquals("Type", trade.getType());
        assertEquals(Double.valueOf(100.0), trade.getBuyQuantity());
        assertEquals(now, trade.getTradeDate());
    }

    @Test
    public void testTradeAllArgsConstructor() {
        // GIVEN
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Trade trade = new Trade();

        // WHEN
        trade.setTradeId(1);
        trade.setAccount("Account1");
        trade.setType("Type1");
        trade.setBuyQuantity(10.0);
        trade.setSellQuantity(20.0);
        trade.setBuyPrice(30.0);
        trade.setBenchmark("Benchmark");
        trade.setTradeDate(now);
        trade.setCreationName("Security");
        trade.setStatus("Status");
        trade.setTrader("Trader");
        trade.setBook("Book");
        trade.setCreationName("CreationName") ;
        trade.setCreationDate(now);
        trade.setRevisionName("RevisionName");
        trade.setRevisionDate(now);
        trade.setDealName("DealName");
        trade.setDealType("DealType");
        trade.setSourceListId("SourceListId");
        trade.setSide("Side");

        // THEN
        assertEquals(Integer.valueOf(1), trade.getTradeId());
        assertEquals("Account1", trade.getAccount());
        assertEquals("Type1", trade.getType());
        assertEquals(Double.valueOf(10.0), trade.getBuyQuantity());
        assertEquals(now, trade.getTradeDate());
    }
}