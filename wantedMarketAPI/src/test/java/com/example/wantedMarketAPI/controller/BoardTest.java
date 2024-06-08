package com.example.wantedMarketAPI.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.wantedMarketAPI.entity.PreProductEntity;
import com.example.wantedMarketAPI.repository.PreProductDealRepository;
import com.example.wantedMarketAPI.repository.PreProductRepository;

import lombok.RequiredArgsConstructor;
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class BoardTest {
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private PreProductRepository preProductRepository;
	@Autowired
	private PreProductDealRepository preProductDealRepository;
	
	@BeforeEach
	public void mockMvcSetUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.build();
	}
	
	@DisplayName("전체 물품 조회 테스트")
	@Test
	void testBoardList() throws Exception {
		// given
		final String url = "/board/list";
		// when
		final ResultActions result = mockMvc.perform(get(url)
				.accept(MediaType.APPLICATION_JSON));
		// then
		result
			.andExpect(status().isOk());
	
//		fail("Not yet implemented");
	}

	@DisplayName("물품 상세 조회 테스트")
	@Test
	void testDetailList() throws Exception {
		// given
		final String url = "/board/detailList?productSeq=1";
		
		preProductRepository.save(new PreProductEntity());
		
		// when
		final ResultActions result = mockMvc.perform(get(url)
				.accept(MediaType.APPLICATION_JSON));
		
		//then
		result
		.andExpect(status().isOk());
		
//		fail("Not yet implemented");
	}

	@Test
	void testBoardController() {
		fail("Not yet implemented");
	}

}
