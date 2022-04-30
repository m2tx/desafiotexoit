package br.com.github.m2tx.desafiotexoit.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import br.com.github.m2tx.desafiotexoit.model.Movie;
import br.com.github.m2tx.desafiotexoit.service.MovieService;

@Component
@Order(1)
public class MovieListRunner implements ApplicationRunner {

	@Autowired
	private MovieService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		Resource resource = patternResolver.getResource("classpath:movielist.csv");
		try(Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
			Map<String, String> mapping = new HashMap<String, String>();
			mapping.put("title", "title");
			mapping.put("year", "year");
			mapping.put("producers", "producer");
			mapping.put("studios", "studio");
			mapping.put("winner", "winner");
			HeaderColumnNameTranslateMappingStrategy<Movie> mappingStrategy = new HeaderColumnNameTranslateMappingStrategy<Movie>();
			mappingStrategy.setType(Movie.class);
			mappingStrategy.setColumnMapping(mapping);


			CsvToBean<Movie> csvToBean = new CsvToBeanBuilder<Movie>(reader)
					.withType(Movie.class)
					.withSeparator(';')
					.withIgnoreLeadingWhiteSpace(true)
					.withMappingStrategy(mappingStrategy)
					.build();

			List<Movie> movies = csvToBean.parse();
			movies.forEach(movie -> service.save(movie));
		}
	}

}
