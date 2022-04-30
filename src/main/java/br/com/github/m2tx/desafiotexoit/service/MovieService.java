package br.com.github.m2tx.desafiotexoit.service;

import static java.util.Collections.sort;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.size;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.replaceIgnoreCase;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.trim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import br.com.github.m2tx.desafiotexoit.dto.AwardsInterval;
import br.com.github.m2tx.desafiotexoit.dto.ProducerInterval;
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

	public AwardsInterval awardsInterval() {
		List<Movie> winnerMovies = repository.findByWinnerTrue();
		Map<String, List<Integer>> producerYears = winnerMovies.stream()
		.map(m->{
			String producers = replaceIgnoreCase(m.getProducers()," and ",",");
			return Stream.of(split(producers,","))
					.flatMap(Stream::of)
					.filter(s->isNotBlank(s))
					.map(p->Pair.of(trim(p),m.getYear()));
		})
		.flatMap(identity())
		.collect(groupingBy(Pair::getFirst,collectingAndThen(toList(), list->{
			return list.stream().map(p->p.getSecond()).collect(toList());
		})));

		Map<Integer, List<ProducerInterval>> interval = producerYears.keySet().stream()
		.filter(s->size(producerYears.get(s))>1)
		.map(s->{
			List<ProducerInterval> intervals = new ArrayList<ProducerInterval>();
			List<Integer> years = producerYears.get(s);
			sort(years);
			Integer lastYear = null;
			for(Integer year:years) {
				if(lastYear!=null) {
					intervals.add(new ProducerInterval(s,year-lastYear,lastYear,year));
				}
				lastYear = year;
			}
			return intervals;
		})
		.flatMap(Collection::stream)
		.collect(groupingBy(ProducerInterval::getInterval));
		
		Integer min = interval.keySet().stream().min(Integer::compareTo).get();
		Integer max = interval.keySet().stream().max(Integer::compareTo).get();

		AwardsInterval awardsInterval = new AwardsInterval();
		awardsInterval.setMin(interval.get(min));
		awardsInterval.setMax(interval.get(max));
		return awardsInterval;
	}

}
