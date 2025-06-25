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


//package com.nnk.springboot;
//
//import com.nnk.springboot.domain.RuleName;
//import com.nnk.springboot.repositories.RuleNameRepository;
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
//public class RuleTests {
//
//	@Autowired
//	private RuleNameRepository ruleNameRepository;
//
//	@Test
//	public void ruleTest() {
//		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
//
//		// Save
//		rule = ruleNameRepository.save(rule);
//		Assert.assertNotNull(rule.getId());
//		Assert.assertTrue(rule.getName().equals("Rule Name"));
//
//		// Update
//		rule.setName("Rule Name Update");
//		rule = ruleNameRepository.save(rule);
//		Assert.assertTrue(rule.getName().equals("Rule Name Update"));
//
//		// Find
//		List<RuleName> listResult = ruleNameRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = rule.getId();
//		ruleNameRepository.delete(rule);
//		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
//		Assert.assertFalse(ruleList.isPresent());
//	}
//}
