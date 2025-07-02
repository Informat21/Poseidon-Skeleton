package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeControllerTest {


    @Mock
    private TradeService tradeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;


    private TradeController tradeController;

    @Before
    public void setup() {

        tradeController = new TradeController(tradeService);
    }

    @Test
    public void testHome() {
        when(tradeService.findAll()).thenReturn(Arrays.asList(new Trade()));

        String result = tradeController.home(model);

        verify(tradeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("trades"), any());
        assertEquals("trade/list", result);
    }

    @Test
    public void testAddUser() {
        String result = tradeController.addUser(new Trade());

        assertEquals("trade/add", result);
    }

    @Test
    public void testValidate_Valid() {
        Trade trade = new Trade();
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = tradeController.validate(trade, bindingResult, model);

        verify(tradeService, times(1)).save(trade);
        assertEquals("redirect:/trade/list", result);
    }

    @Test
    public void testValidate_Invalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = tradeController.validate(new Trade(), bindingResult, model);

        verify(tradeService, never()).save(any());
        assertEquals("trade/add", result);
    }

    @Test
    public void testShowUpdateForm_ValidId() {
        Trade trade = new Trade();
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));

        String result = tradeController.showUpdateForm(1, model);

        verify(tradeService, times(1)).findById(1);
        verify(model, times(1)).addAttribute("trade", trade);
        assertEquals("trade/update", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShowUpdateForm_InvalidId() {
        when(tradeService.findById(999)).thenReturn(Optional.empty());

        tradeController.showUpdateForm(999, model);
    }

    @Test
    public void testUpdateTrade_Valid() {
        Trade trade = new Trade();
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = tradeController.updateTrade(1, trade, bindingResult, model);

        verify(tradeService, times(1)).save(trade);
        assertEquals("redirect:/trade/list", result);
    }

    @Test
    public void testUpdateTrade_Invalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = tradeController.updateTrade(1, new Trade(), bindingResult, model);

        verify(tradeService, never()).save(any());
        assertEquals("trade/update", result);
    }

    @Test
    public void testDeleteTrade_ValidId() {
        Trade trade = new Trade();
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));
        doNothing().when(tradeService).deleteById(1);

        String result = tradeController.deleteTrade(1, model);

        verify(tradeService, times(1)).deleteById(1);
        assertEquals("redirect:/trade/list", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTrade_InvalidId() {
        when(tradeService.findById(999)).thenReturn(Optional.empty());

        tradeController.deleteTrade(999, model);
    }
}
