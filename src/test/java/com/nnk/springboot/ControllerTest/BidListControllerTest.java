package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BidListControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BidListService bidListService;

    @InjectMocks
    private BidListController bidListController;

    private AutoCloseable closeable;
    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(bidListController).build();

    }


    @Test
    public void testHome() throws Exception {
        when(bidListService.findAll()).thenReturn(Arrays.asList(new BidList()));

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"));
    }

    @Test
    public void testAddBidForm() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public void testValidateValidBid() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .param("account", "Test")
                        .param("type", "TestType")
                        .param("bidQuantity", "10.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).save(any(BidList.class));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        BidList bid = new BidList();
        bid.setBidListId(1) ;
        when(bidListService.findById(1)).thenReturn(Optional.of(bid));

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    public void testUpdateBidValid() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .param("account", "UpdatedAccount")
                        .param("type", "UpdatedType")
                        .param("bidQuantity", "20.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).save(any(BidList.class));
    }

    @Test
    public void testDeleteBid() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).deleteById(1);
    }
}
