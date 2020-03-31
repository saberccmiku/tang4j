package com.tang4j.generator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Tang4jGeneratorApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {

		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from th_sys_user");
		maps.forEach(System.out::println);
	}

}
