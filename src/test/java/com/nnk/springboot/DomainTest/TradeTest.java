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
        //
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Trade trade = new Trade();

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



//package com.nnk.springboot;
//
//import com.nnk.springboot.domain.Trade;
//import com.nnk.springboot.repositories.TradeRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TradeTests {
//
//	@Autowired
//	private TradeRepository tradeRepository;
//
//	@Test
//	public void tradeTest() {
//		Trade trade = new Trade();
//		trade.setAccount("Trade Account");
//		trade.setType("Type");
//		trade.setBuyQuantity(100.0);
//		trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
//
//		// Save
//		trade = tradeRepository.save(trade);
//		Assert.assertNotNull(trade.getTradeId());
//		Assert.assertEquals("Trade Account", trade.getAccount());
//
//		// Update
//		trade.setAccount("Trade Account Update");
//		trade = tradeRepository.save(trade);
//		Assert.assertEquals("Trade Account Update", trade.getAccount());
//
//		// Find
//		List<Trade> listResult = tradeRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = trade.getTradeId();
//		tradeRepository.delete(trade);
//		Optional<Trade> tradeList = tradeRepository.findById(id);
//		Assert.assertFalse(tradeList.isPresent());
//	}
//}
