package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.CurvePoint;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class CurvePointTest  {

    @Test
    public void testCurvePointConstructorAndGetters() {
        // GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(10);
        curvePoint.setTerm(10d);
        curvePoint.setValue(30d);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        curvePoint.setAsOfDate(now);
        curvePoint.setCreationDate(now);

        // THEN
        assertEquals(Integer.valueOf(10), curvePoint.getCurveId());
        assertEquals(Double.valueOf(10d), curvePoint.getTerm());
        assertEquals(Double.valueOf(30d), curvePoint.getValue());
        assertEquals(now, curvePoint.getAsOfDate());
        assertEquals(now, curvePoint.getCreationDate());
    }

    @Test
    public void testCurvePointAllArgsConstructor() {
        // GIVEN
        Timestamp now = new Timestamp(System.currentTimeMillis());
        CurvePoint curvePoint = new CurvePoint(
                1,             // id
                20,            // curveId
                now,           // asOfDate
                15.5,          // term
                25.5,          // value
                now            // creationDate
        );

        // THEN
        assertEquals(Integer.valueOf(1), curvePoint.getId());
        assertEquals(Integer.valueOf(20), curvePoint.getCurveId());
        assertEquals(now, curvePoint.getAsOfDate());
        assertEquals(Double.valueOf(15.5), curvePoint.getTerm());
        assertEquals(Double.valueOf(25.5), curvePoint.getValue());
        assertEquals(now, curvePoint.getCreationDate());
    }
}



//package com.nnk.springboot;
//
//import com.nnk.springboot.domain.CurvePoint;
//import com.nnk.springboot.repositories.CurvePointRepository;
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
//public class CurvePointTests {
//
//    @Autowired
//    private CurvePointRepository curvePointRepository;
//
//    @Test
//    public void curvePointTest() {
//        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
//
//        // Save
//        curvePoint = curvePointRepository.save(curvePoint);
//        Assert.assertNotNull(curvePoint.getId());
//        Assert.assertTrue(curvePoint.getCurveId() == 10);
//
//        // Update
//        curvePoint.setCurveId(20);
//        curvePoint = curvePointRepository.save(curvePoint);
//        Assert.assertTrue(curvePoint.getCurveId() == 20);
//
//        // Find
//        List<CurvePoint> listResult = curvePointRepository.findAll();
//        Assert.assertTrue(listResult.size() > 0);
//
//        // Delete
//        Integer id = curvePoint.getId();
//        curvePointRepository.delete(curvePoint);
//        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
//        Assert.assertFalse(curvePointList.isPresent());
//    }
//
//}