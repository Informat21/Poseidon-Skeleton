package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.BidList;
import org.junit.Test;

import static org.junit.Assert.*;

public class BidListTest {

    @Test
    public void testBidListConstructorAndGetters() {
        // GIVEN
        BidList bidList = new BidList();

        // WHEN
        bidList.setAccount("TestAccount");
        bidList.setType("TestType");
        bidList.setBidQuantity(100.0);

        // THEN
        assertEquals("TestAccount", bidList.getAccount());
        assertEquals("TestType", bidList.getType());
        assertEquals(Double.valueOf(100.0), bidList.getBidQuantity());
    }

    @Test
    public void testBidListSetters() {
        // GIVEN
        BidList bidList = new BidList();

        // WHEN
        bidList.setAccount("NewAccount");
        bidList.setType("NewType");

        // THEN
        assertEquals("NewAccount", bidList.getAccount());
        assertEquals("NewType", bidList.getType());
    }

    @Test
    public void testBidListAllArgsConstructor() {
        // GIVEN
        BidList bidList = new BidList(
                1,
                "Account1",
                "Type1",
                10.0,
                20.0,
                30.0,
                40.0,
                "Benchmark",
                null,
                "Commentary",
                "Security",
                "Status",
                "Trader",
                "Book",
                "CreationName",
                null,
                "RevisionName",
                null,
                "DealName",
                "DealType",
                "SourceListId",
                "Side"
        );

        // THEN
        assertEquals(Integer.valueOf(1), bidList.getBidListId());
        assertEquals("Account1", bidList.getAccount());
        assertEquals("Type1", bidList.getType());
        assertEquals(Double.valueOf(10.0), bidList.getBidQuantity());
    }
}
