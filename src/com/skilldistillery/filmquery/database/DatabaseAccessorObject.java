package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Actor getActorById(int actorId) {
		String user = "student";
		String pass = "student";
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
			}

			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return actor;
	}

	public List<Film> getFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		String actor;
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			String sql = "SELECT f.id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film f JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				short releaseYear = rs.getShort(4);
				int langId = rs.getInt(5);
				String langName = rs.getString(6);
				int rentDur = rs.getInt(7);
				double rentalRate = rs.getDouble(8);
				int length = rs.getInt(9);
				double repCost = rs.getDouble(10);
				String rating = rs.getString(11);
				String features = rs.getString(12);
				actor = rs.getString(1);
				Film film = new Film(id, title, desc, releaseYear, langId, langName, rentDur, rentalRate, length, repCost, rating,
						features, actor);
				films.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Film getFilmById(int filmId) {
		Film film = null;
		String languageResult = null;
		String sql = "SELECT film.id, title, description, release_year, language_id, rental_duration, ";
		sql += " rental_rate, length, replacement_cost, rating, special_features " + " FROM film  JOIN language l ON film.language_id = l.id WHERE film.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film(); // Create the object
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDesc(rs.getString(3));
				film.setReleaseYear(rs.getShort(4));
				film.setLangId(rs.getInt(5));
//				film.setLangName(rs.getString(6));
				film.setRentDur(rs.getInt(6));
				film.setRentalRate(rs.getDouble(7));
				film.setLength(rs.getInt(8));
				film.setRepCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setFeatures(rs.getString(11));
				languageResult = getLanguage(rs.getInt(5));
				film.setLangName(languageResult);
				film.setActors(getActorsByFilmId(filmId));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return film;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor "
				+ "ON film_actor.actor_id = actor.id "
				+ "JOIN film on film.id = film_actor.film_id WHERE film_id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actors;
	}

	public String getLanguage(int input) {
		String languageResult = null;
		String sql = "SELECT name FROM language JOIN film ON film.language_id = language.id WHERE film.language_id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, input);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				languageResult = rs.getString(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return languageResult;
	}
	
	public List<Film> getFilmByKeyword(String input) {
		List<Film> films = new ArrayList<>();
		String languageResult = null;
		List <Actor> actorsList = null;
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration,"
				+ " rental_rate, length, replacement_cost, rating, special_features FROM film "
				+ "WHERE (title LIKE ? OR description LIKE ?)";
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + input + "%");
			stmt.setString(2, "%" + input + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				short releaseYear = rs.getShort(4);
				int langId = rs.getInt(5);
				int rentDur = rs.getInt(6);
				double rentalRate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				languageResult = getLanguage(rs.getInt(5));
				actorsList = getActorsByFilmId(1);
				
				Film film = new Film(id, title, desc, releaseYear, langId, languageResult, rentDur, rentalRate, length, repCost, rating,
						features, actorsList);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
		return films;
	}

}
