package br.com.github.m2tx.desafiotexoit.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.github.m2tx.desafiotexoit.model.Movie;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void returnAllMovies() throws JsonProcessingException, Exception {
		this.mockMvc.perform(get("/movies")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void returnMovieWithIdOne() throws JsonProcessingException, Exception {
		this.mockMvc.perform(get("/movies/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1));
	}
	
	@Test
	public void saveMovie() throws JsonProcessingException, Exception {
		this.mockMvc.perform(post("/movies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Movie("Title 1","Producer 1","Studio 1",1972,true))))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(any(Integer.class)));
	}
	
	@Test
	public void updateMovie() throws JsonProcessingException, Exception {
		this.mockMvc.perform(put("/movies/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new Movie("Title Updated","Producer Updated","Studio Updated",1984,false))))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(2))
				.andExpect(jsonPath("$.title").value("Title Updated"));
	}
	
	@Test
	public void deleteMovie() throws JsonProcessingException, Exception {
		this.mockMvc.perform(delete("/movies/3"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void returnProducerMinAndMaxIntervalAwards() throws JsonProcessingException, Exception {
		this.mockMvc.perform(get("/movies/awards/interval/")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
