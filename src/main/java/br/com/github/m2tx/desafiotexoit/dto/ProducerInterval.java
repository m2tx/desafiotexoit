package br.com.github.m2tx.desafiotexoit.dto;

public class ProducerInterval {

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
	
	public ProducerInterval() {}
	
	public ProducerInterval(String producer, Integer interval, Integer previousWin, Integer followingWin) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return producer;
	}
	
	public Integer getInterval() {
		return interval;
	}
	
	public Integer getFollowingWin() {
		return followingWin;
	}
	
	public Integer getPreviousWin() {
		return previousWin;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}
	
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
}
