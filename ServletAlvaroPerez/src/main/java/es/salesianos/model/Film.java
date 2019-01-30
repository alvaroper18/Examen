package es.salesianos.model;

import java.util.List;

public class Film {
	private Integer cod;
	private String title;
	private Integer codDirector;
	private Director director;
	private List<FilmActors> actorFilms;

	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		cod = cod;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		title = title;
	}
	public Integer getCodDirector() {
		return codDirector;
	}

	public void setCodDirector(Integer codDirector) {
		codDirector = codDirector;
	}
	
	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<FilmActors> getActorFilms() {
		return actorFilms;
	}

	public void setActorFilms(List<FilmActors> actorFilms) {
		this.actorFilms = actorFilms;
	}
	
}
