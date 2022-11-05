package com.skilldistillery.eventtracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private Double msrp;
	private String description;
	
	@Column(name="online_mp")
	private Boolean onlineMp;

	@ManyToMany
	@JoinTable(name="game_publisher",
			joinColumns = @JoinColumn(name= "game_id"),
			inverseJoinColumns = @JoinColumn(name = "publisher_id"))
	@JsonIgnoreProperties({"games"})
	private List<Publisher> publishers; 

	@ManyToMany
	@JoinTable(name="game_genre",
			joinColumns = @JoinColumn(name="game_id"),
			inverseJoinColumns = @JoinColumn(name="genre_id"))
	@JsonIgnoreProperties({"games"})
	private List<Genre> genres;
	
	@ManyToMany
	@JoinTable(name="sale_game",
			joinColumns = @JoinColumn(name="game_id"),
			inverseJoinColumns = @JoinColumn(name="sale_id"))
	@JsonIgnoreProperties({"games"})
	private List<Sale> sales;
	
	public Game() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getMsrp() {
		return msrp;
	}

	public void setMsrp(Double msrp) {
		this.msrp = msrp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getOnlineMp() {
		return onlineMp;
	}

	public void setOnlineMp(Boolean onlineMp) {
		this.onlineMp = onlineMp;
	}

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public void addPublisher(Publisher pub) {
		if(publishers == null) {
			publishers = new ArrayList<>();
		}
		
		if(!publishers.contains(pub)) {
			publishers.add(pub);
			pub.addGame(this);
		}
	}
	
	public void removePublisher(Publisher pub) {
		if(publishers != null && publishers.contains(pub)) {
			publishers.remove(pub);
			pub.removeGame(this);
		}
	}
	
	public void addGenre(Genre genre) {
		if(genres == null) {
			genres = new ArrayList<>();
		}
		
		if(!genres.contains(genre)) {
			genres.add(genre);
			genre.addGame(this);
		}
	}
	
	public void removeGenre(Genre genre) {
		if(genres != null && genres.contains(genre)) {
			genres.remove(genre);
			genre.removeGame(this);
		}
	}
	
	public void addSale(Sale sale) {
		if(sales == null) {
			sales = new ArrayList<>();
		}
		
		if(!sales.contains(sale)) {
			sales.add(sale);
			sale.addGame(this);
		}
	}
	
	public void removeSale(Sale sale) {
		if(sales != null && sales.contains(sale)) {
			sales.remove(sale);
			sale.removeGame(this);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return id == other.id;
	}
	
	
}
