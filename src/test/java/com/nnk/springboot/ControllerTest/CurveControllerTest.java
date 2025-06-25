package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CurvePointService curvePointService;

    @InjectMocks
    private CurveController curveController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(curveController).build();
    }

    @Test
    public void testHome() throws Exception {
        when(curvePointService.findAll()).thenReturn(Arrays.asList(new CurvePoint()));

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoints"));
    }

    @Test
    public void testAddBidForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void testValidateValidCurvePoint() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "10")
                        .param("term", "20.0")
                        .param("value", "30.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, times(1)).save(any(CurvePoint.class));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        when(curvePointService.findById(1)).thenReturn(Optional.of(curvePoint));

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    public void testUpdateBidValid() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .param("curveId", "10")
                        .param("term", "25.0")
                        .param("value", "35.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, times(1)).save(any(CurvePoint.class));
    }

    @Test
    public void testDeleteBid() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, times(1)).deleteById(1);
    }
}
