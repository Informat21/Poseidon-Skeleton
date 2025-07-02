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

        // WHEN
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