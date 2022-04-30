package br.com.github.m2tx.desafiotexoit.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.github.m2tx.desafiotexoit.dto.AwardsInterval;
import br.com.github.m2tx.desafiotexoit.model.Movie;
import br.com.github.m2tx.desafiotexoit.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	private MovieService service;
	
	@Autowired
	public MovieController(MovieService service) {
		this.service = service;
	}
	
	@GetMapping()
	public List<Movie> getAll(){
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> get(@PathVariable Long id){
		return ResponseEntity.of(service.findById(id));
	}
	
	@PostMapping()
	public ResponseEntity<Movie> save(@RequestBody Movie newMovie){
		service.save(newMovie);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMovie.getId())
                .toUri();
		return ResponseEntity.created(location).body(newMovie);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movie> update(@PathVariable Long id,@RequestBody Movie newMovie){
		return ResponseEntity.ok(service.findById(id)
		.map(movie -> {
			movie.setTitle(newMovie.getTitle());
			movie.setProducers(newMovie.getProducers());
			movie.setStudios(newMovie.getStudios());
			movie.setYear(newMovie.getYear());
			movie.setWinner(newMovie.isWinner());
			return service.save(movie);
		}).orElseGet(()->{
			newMovie.setId(id);
			return service.save(newMovie);
		}));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@GetMapping("/awards/interval/")
	public ResponseEntity<AwardsInterval> awardsInterval(){
		return ResponseEntity.ok(service.awardsInterval());
	}
}
