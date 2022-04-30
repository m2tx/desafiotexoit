package br.com.github.m2tx.desafiotexoit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.m2tx.desafiotexoit.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
