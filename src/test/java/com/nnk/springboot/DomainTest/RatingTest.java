package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.Rating;
import org.junit.Test;

import static org.junit.Assert.*;

public class RatingTest {

    @Test
    public void testRatingConstructorAndGetters() {
        // GIVEN
        Rating rating = new Rating();
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
        // WHEN
        Rating rating = new Rating();
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


//package com.nnk.springboot;
//
//import com.nnk.springboot.domain.Rating;
//import com.nnk.springboot.repositories.RatingRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RatingTests {
//
//	@Autowired
//	private RatingRepository ratingRepository;
//
//	@Test
//	public void ratingTest() {
//		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
//
//		// Save
//		rating = ratingRepository.save(rating);
//		Assert.assertNotNull(rating.getId());
//		Assert.assertTrue(rating.getOrderNumber() == 10);
//
//		// Update
//		rating.setOrderNumber(20);
//		rating = ratingRepository.save(rating);
//		Assert.assertTrue(rating.getOrderNumber() == 20);
//
//		// Find
//		List<Rating> listResult = ratingRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = rating.getId();
//		ratingRepository.delete(rating);
//		Optional<Rating> ratingList = ratingRepository.findById(id);
//		Assert.assertFalse(ratingList.isPresent());
//	}
//}
