package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    private CurvePoint curvePoint;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(1.5);
        curvePoint.setValue(2.5);
    }

    @Test
    public void testFindAll() {
        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(curvePoint));

        List<CurvePoint> result = curvePointService.findAll();

        assertEquals(1, result.size());
        assertEquals((Integer) 10, result.get(0).getCurveId());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

        CurvePoint saved = curvePointService.save(curvePoint);

        assertNotNull(saved);
        assertEquals(1.5, saved.getTerm(), 0.0);
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    public void testFindById_Existing() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        Optional<CurvePoint> found = curvePointService.findById(1);

        assertTrue(found.isPresent());
        assertEquals((Double) 2.5, found.get().getValue());
        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_NotFound() {
        when(curvePointRepository.findById(999)).thenReturn(Optional.empty());

        Optional<CurvePoint> result = curvePointService.findById(999);

        assertFalse(result.isPresent());
        verify(curvePointRepository, times(1)).findById(999);
    }

    @Test
    public void testDeleteById() {
        curvePointService.deleteById(1);

        verify(curvePointRepository, times(1)).deleteById(1);
    }
}
