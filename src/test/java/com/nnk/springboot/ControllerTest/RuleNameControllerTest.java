package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RuleNameControllerTest {

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RuleNameController ruleNameController;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHome() {
        when(ruleNameService.findAll()).thenReturn(new ArrayList<>());

        String viewName = ruleNameController.home(model);

        verify(ruleNameService).findAll();
        verify(model).addAttribute(eq("ruleNames"), anyList());
        assertEquals("ruleName/list", viewName);
    }

    @Test
    public void testAddRuleForm() {
        String viewName = ruleNameController.addRuleForm(new RuleName());

        assertEquals("ruleName/add", viewName);
    }

    @Test
    public void testValidateValid() {
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ruleNameController.validate(ruleName, bindingResult, model);

        verify(ruleNameService).save(ruleName);
        assertEquals("redirect:/ruleName/list", viewName);
    }

    @Test
    public void testValidateInvalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ruleNameController.validate(new RuleName(), bindingResult, model);

        verify(ruleNameService, never()).save(any());
        assertEquals("ruleName/add", viewName);
    }

    @Test
    public void testShowUpdateForm() {
        RuleName ruleName = new RuleName();
        when(ruleNameService.findById(1)).thenReturn(Optional.of(ruleName));

        String viewName = ruleNameController.showUpdateForm(1, model);

        verify(ruleNameService).findById(1);
        verify(model).addAttribute("ruleName", ruleName);
        assertEquals("ruleName/update", viewName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShowUpdateFormInvalidId() {
        when(ruleNameService.findById(1)).thenReturn(Optional.empty());

        ruleNameController.showUpdateForm(1, model);
    }

    @Test
    public void testUpdateRuleNameValid() {
        RuleName ruleName = new RuleName();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ruleNameController.updateRuleName(1, ruleName, bindingResult, model);

        verify(ruleNameService).save(ruleName);
        assertEquals("redirect:/ruleName/list", viewName);
    }

    @Test
    public void testUpdateRuleNameInvalid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ruleNameController.updateRuleName(1, new RuleName(), bindingResult, model);

        verify(ruleNameService, never()).save(any());
        assertEquals("ruleName/update", viewName);
    }

    @Test
    public void testDeleteRuleName() {
        String viewName = ruleNameController.deleteRuleName(1, model);

        verify(ruleNameService).deleteById(1);
        assertEquals("redirect:/ruleName/list", viewName);
    }
}