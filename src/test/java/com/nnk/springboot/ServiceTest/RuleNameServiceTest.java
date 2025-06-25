package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    private RuleName ruleName;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Rule Name");
        ruleName.setDescription("Description test");
        ruleName.setJson("Json test");
        ruleName.setTemplate("Template test");
        ruleName.setSqlStr("SQL string");
        ruleName.setSqlPart("SQL part");
    }

    @Test
    public void testFindAll() {
        when(ruleNameRepository.findAll()).thenReturn(Arrays.asList(ruleName));

        List<RuleName> result = ruleNameService.findAll();

        assertEquals(1, result.size());
        assertEquals("Rule Name", result.get(0).getName());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

        RuleName saved = ruleNameService.save(ruleName);

        assertNotNull(saved);
        assertEquals("Description test", saved.getDescription());
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void testFindById_Existing() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        Optional<RuleName> found = ruleNameService.findById(1);

        assertTrue(found.isPresent());
        assertEquals("SQL string", found.get().getSqlStr());
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_NotFound() {
        when(ruleNameRepository.findById(999)).thenReturn(Optional.empty());

        Optional<RuleName> result = ruleNameService.findById(999);

        assertFalse(result.isPresent());
        verify(ruleNameRepository, times(1)).findById(999);
    }

    @Test
    public void testDeleteById() {
        ruleNameService.deleteById(1);

        verify(ruleNameRepository, times(1)).deleteById(1);
    }
}
