package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class RatingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingController ratingController;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }

    @Test
    public void testHome() throws Exception {
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(new Rating()));

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratings"));
    }

    @Test
    public void testAddRatingForm() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    public void testValidateValidRating() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "Moodys A")
                        .param("sandPRating", "S&P A")
                        .param("fitchRating", "Fitch A")
                        .param("orderNumber", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingRepository, times(1)).save(any(Rating.class));
    }
    @Test
    public void testValidateInvalidRating() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "") // champ vide = invalide
                        .param("sandPRating", "S&P A")
                        .param("fitchRating", "Fitch A")
                        .param("orderNumber", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));

        verify(ratingRepository, times(0)).save(any(Rating.class));
    }


    @Test
    public void testShowUpdateForm() throws Exception {
        Rating rating = new Rating();
        rating.setId(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    @Test
    public void testUpdateRatingValid() throws Exception {
        mockMvc.perform(post("/rating/update/1")
                        .param("moodysRating", "Moodys A")
                        .param("sandPRating", "S&P A")
                        .param("fitchRating", "Fitch A")
                        .param("orderNumber", "15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    public void testDeleteRating() throws Exception {
        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
}
