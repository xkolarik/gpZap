package com.zap.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testGETDebtoController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/find/zap"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETDetalheDebto() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/debtos/viva"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
