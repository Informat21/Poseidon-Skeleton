package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    private BidList bid1;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bid1 = new BidList();
        bid1.setBidListId(1);
        bid1.setAccount("Account Test");
        bid1.setType("Type Test");
        bid1.setBidQuantity(10.0);
    }

    @Test
    public void testFindAll() {
        when(bidListRepository.findAll()).thenReturn(Arrays.asList(bid1));

        List<BidList> result = bidListService.findAll();

        assertEquals(1, result.size());
        assertEquals("Account Test", result.get(0).getAccount());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        when(bidListRepository.save(bid1)).thenReturn(bid1);

        BidList saved = bidListService.save(bid1);

        assertNotNull(saved);
        assertEquals("Account Test", saved.getAccount());
        verify(bidListRepository, times(1)).save(bid1);
    }

    @Test
    public void testFindById_Existing() {
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid1));

        Optional<BidList> found = bidListService.findById(1);

        assertTrue(found.isPresent());
        assertEquals("Account Test", found.get().getAccount());
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_NotFound() {
        when(bidListRepository.findById(999)).thenReturn(Optional.empty());

        Optional<BidList> result = bidListService.findById(999);

        assertFalse(result.isPresent());
        verify(bidListRepository, times(1)).findById(999);
    }

    @Test
    public void testDeleteById() {
        bidListService.deleteById(1);

        verify(bidListRepository, times(1)).deleteById(1);
    }
}
