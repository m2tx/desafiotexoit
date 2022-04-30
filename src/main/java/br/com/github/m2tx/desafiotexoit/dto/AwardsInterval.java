package br.com.github.m2tx.desafiotexoit.dto;

import java.util.List;

public class AwardsInterval {

	private List<ProducerInterval> min;
	private List<ProducerInterval> max;
	
	public AwardsInterval() {}

	public List<ProducerInterval> getMax() {
		return max;
	}
	
	public List<ProducerInterval> getMin() {
		return min;
	}
	
	public void setMax(List<ProducerInterval> max) {
		this.max = max;
	}
	
	public void setMin(List<ProducerInterval> min) {
		this.min = min;
	}
}
