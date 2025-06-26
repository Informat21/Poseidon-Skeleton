package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.RuleName;
import org.junit.Test;

import static org.junit.Assert.*;

public class RuleTest {

    @Test
    public void testRuleNameConstructorAndGetters() {
        // GIVEN
        RuleName rule = new RuleName();
        rule.setName("Test Name");
        rule.setDescription("Test Description");
        rule.setJson("Test Json");
        rule.setTemplate("Test Template");
        rule.setSqlStr("Test SQL");
        rule.setSqlPart("Test SQL Part");

        // THEN
        assertEquals("Test Name", rule.getName());
        assertEquals("Test Description", rule.getDescription());
        assertEquals("Test Json", rule.getJson());
        assertEquals("Test Template", rule.getTemplate());
        assertEquals("Test SQL", rule.getSqlStr());
        assertEquals("Test SQL Part", rule.getSqlPart());
    }

    @Test
    public void testRuleNameAllArgsConstructor() {
        // WHEN
        RuleName rule = new RuleName();
        rule.setName("Rule Name");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SQL");
        rule.setSqlPart("SQL Part");



        // THEN
        assertEquals("Rule Name", rule.getName());
        assertEquals("Description", rule.getDescription());
        assertEquals("Json", rule.getJson());
        assertEquals("Template", rule.getTemplate());
        assertEquals("SQL", rule.getSqlStr());
        assertEquals("SQL Part", rule.getSqlPart());
    }
}