package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    private Rating rating;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Aaa");
        rating.setSandPRating("AAA");
        rating.setFitchRating("AAA");
        rating.setOrderNumber(1);
    }

    @Test
    public void testFindAll() {
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating));

        List<Rating> result = ratingService.findAll();

        assertEquals(1, result.size());
        assertEquals("Aaa", result.get(0).getMoodysRating());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating saved = ratingService.save(rating);

        assertNotNull(saved);
        assertEquals("AAA", saved.getSandPRating());
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    public void testFindById_Existing() {
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Optional<Rating> found = ratingService.findById(1);

        assertTrue(found.isPresent());
        assertEquals("AAA", found.get().getFitchRating());
        verify(ratingRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_NotFound() {
        when(ratingRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Rating> result = ratingService.findById(999);

        assertFalse(result.isPresent());
        verify(ratingRepository, times(1)).findById(999);
    }

    @Test
    public void testDeleteById() {
        ratingService.deleteById(1);

        verify(ratingRepository, times(1)).deleteById(1);
    }
}
