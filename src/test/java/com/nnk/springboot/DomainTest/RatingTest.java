package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.Rating;
import org.junit.Test;

import static org.junit.Assert.*;

public class RatingTest {

    @Test
    public void testRatingConstructorAndGetters() {
        // GIVEN
        Rating rating = new Rating();

        // WHEN
        rating.setMoodysRating("Moodys Aaa");
        rating.setSandPRating("S&P AA+");
        rating.setFitchRating("Fitch AA");
        rating.setOrderNumber(10);

        // THEN
        assertEquals("Moodys Aaa", rating.getMoodysRating());
        assertEquals("S&P AA+", rating.getSandPRating());
        assertEquals("Fitch AA", rating.getFitchRating());
        assertEquals(Integer.valueOf(10), rating.getOrderNumber());
    }

    @Test
    public void testRatingAllArgsConstructor() {
        // GIVEN
        Rating rating = new Rating();

        // WHEN
        rating.setMoodysRating("Moodys Baa");
        rating.setSandPRating("S&P BBB");
        rating.setFitchRating("Fitch BBB-");
        rating.setOrderNumber(5);

        // THEN
        assertEquals("Moodys Baa", rating.getMoodysRating());
        assertEquals("S&P BBB", rating.getSandPRating());
        assertEquals("Fitch BBB-", rating.getFitchRating());
        assertEquals(Integer.valueOf(5), rating.getOrderNumber());
    }

    @Test
    public void testRatingSetters() {
        // GIVEN
        Rating rating = new Rating();

        // WHEN
        rating.setMoodysRating("New Moodys");
        rating.setSandPRating("New S&P");
        rating.setFitchRating("New Fitch");
        rating.setOrderNumber(15);

        // THEN
        assertEquals("New Moodys", rating.getMoodysRating());
        assertEquals("New S&P", rating.getSandPRating());
        assertEquals("New Fitch", rating.getFitchRating());
        assertEquals(Integer.valueOf(15), rating.getOrderNumber());
    }
}