package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    private Trade trade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account Test");
        trade.setType("Type Test");
        trade.setBuyQuantity(10.0);
    }

    @Test
    public void testFindAll() {
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade));

        List<Trade> result = tradeService.findAll();

        assertEquals(1, result.size());
        assertEquals("Account Test", result.get(0).getAccount());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade saved = tradeService.save(trade);

        assertNotNull(saved);
        assertEquals("Type Test", saved.getType());
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void testFindById_Existing() {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Optional<Trade> result = tradeService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(10.0, result.get().getBuyQuantity(), 0.01);
        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_NotFound() {
        when(tradeRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Trade> result = tradeService.findById(999);

        assertFalse(result.isPresent());
        verify(tradeRepository, times(1)).findById(999);
    }

    @Test
    public void testDeleteById() {
        tradeService.deleteById(1);

        verify(tradeRepository, times(1)).deleteById(1);
    }
}
