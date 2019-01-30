package es.salesianos.model;

import java.util.List;

public class Director {
	private Integer cod;
	private String name;

	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private List<Film> films;

	public List<Film> getFilms() {
		return films;
	}

	public void setPeliculas(List<Film> films) {
		this.films = films;
	}
}
