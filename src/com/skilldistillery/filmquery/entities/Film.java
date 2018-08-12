package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private int id;
	private String title;
	private String desc;
	private int releaseYear;
	private int langId;
	private String langName;
	private int rentDur;
	private double rentalRate;
	private int length;
	private double repCost;
	private String rating;
	private String features;
	private List<Actor> actors = new ArrayList<>();
	private String actor;

	public Film() {
		
	}
	
	public Film (int id, String title,  String desc, short releaseYear, 
			 int langId, String langName, int rentDur, double rate, int length, double repCost, 
			 String rating, String features, String actor) {
		this.id = id;
		this.title = title;
		this.desc = desc ;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.langName = langName;
		this.rentDur = rentDur;
		this.rentalRate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.actor = actor;
	}
	
	public Film (int id, String title,  String desc, short releaseYear, 
			 int langId, String langName, int rentDur, double rate, int length, double repCost, 
			 String rating, String features, List<Actor> actors) {
		this.id = id;
		this.title = title;
		this.desc = desc ;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.langName = langName;
		this.rentDur = rentDur;
		this.rentalRate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.actors = actors;
	}
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String description) {
		this.desc = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}
	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getRentDur() {
		return rentDur;
	}

	public void setRentDur(int rentalDuration) {
		this.rentDur = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public double getRepCost() {
		return repCost;
	}

	public void setRepCost(double replacementCost) {
		this.repCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + langId;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + rentDur;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(repCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (langId != other.langId)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (rentDur != other.rentDur)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(repCost) != Double.doubleToLongBits(other.repCost))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film -- id: " + id + ", title: " + title + ", description: " + desc + ", release year: "
				+ releaseYear + ", language Id: " + langId + ", language Name: " + langName + ", rentalDuration: " + rentDur + ", rentalRate: "
				+ rentalRate + ", replacement cost: " + repCost + ", rating: " + rating + ", special features: "
				+ features + ", Actors: " + actors.toString() + ".";
	}
}

//Complete the Film class with attributes that map to the columns of
//the film table. Use appropriate datatypes and Java naming conventions. 
//Provide getters and setters, and appropriate constructors. 
//Also add a good toString, and equals and hashCode methods.