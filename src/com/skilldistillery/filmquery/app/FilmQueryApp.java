package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	Scanner input = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws ClassNotFoundException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {
		Film film = db.getFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		menu();

	}

	private void menu() {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		int userInput = 0;
		int filmId;
		String filmKeyword;
		Film filmIdResult;
		List<Film> filmKeywordResult = new ArrayList<>();

		do {
			System.out.print(
					"1. Look up a film by its id.\n2. Look up a film by a search keyword.\n3. Exit the application.\n>");
			userInput = input.nextInt();
			input.nextLine();
			switch (userInput) {
			case 1:
				System.out.print("Please enter the film id: ");
				filmId = input.nextInt();
				filmIdResult = dao.getFilmById(filmId);

				if (filmIdResult == null) {
					System.out.println("Film is not found.");
				} else {
					System.out.println(filmIdResult);
				}
				break;
			case 2:
				System.out.print("Please enter a keyword to seach for: ");
				filmKeyword = input.nextLine();
				filmKeywordResult = dao.getFilmByKeyword(filmKeyword);

				if (filmKeywordResult == null || dao.getFilmByKeyword(filmKeyword) == null) {
					System.out.println("No matching films for the input keyword.");
				} else {
					for (Film film : filmKeywordResult) {
						System.out.println(film);
					}
				}
				break;
			case 3:
				System.exit(0);
			}
		} while (userInput < 3 && userInput > 0);
	}
}