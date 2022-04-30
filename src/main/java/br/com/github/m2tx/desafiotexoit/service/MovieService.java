package br.com.github.m2tx.desafiotexoit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.github.m2tx.desafiotexoit.model.Movie;
import br.com.github.m2tx.desafiotexoit.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;

	public Optional<Movie> findById(Long id) {
		return repository.findById(id);
	}

	public Movie save(Movie movie) {
		return repository.save(movie);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public List<Movie> findAll() {
		return repository.findAll();
	}

}
