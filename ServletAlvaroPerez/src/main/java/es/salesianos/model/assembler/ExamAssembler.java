package es.salesianos.model.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;

public class ExamAssembler {

	public static Actor assembleActorFrom(HttpServletRequest req) {
		Actor actor = new Actor();
		String name=req.getParameter("NAME");
		Integer yearofbirthday=Integer.parseInt(req.getParameter("YEAROFBIRTHDAY"));
		actor.setName(name);
		actor.setYearofbirthday(yearofbirthday);
		return actor;
	}
	
	public static Film assemblePeliculaFrom(HttpServletRequest req) {
		Film film = new Film();
		String title =req.getParameter("TITLE");
		Integer codDirector=Integer.parseInt(req.getParameter("CODDIRECTOR"));

		film.setTitle(title);
		film.setCodDirector(codDirector);
		
		
		return film;
	}
	
	
	public static Director assembleDirectorFrom(HttpServletRequest req) {
		Director director = new Director();
		String name = req.getParameter("NAME");
		director.setName(name);
		
		
		return director;
	}

	public static FilmActors assembleFilmActorFrom(HttpServletRequest req) {
		FilmActors FilmActor = new FilmActors();
		String codPelicula = req.getParameter("codPelicula");
		String codActor = req.getParameter("codActor");
		String cache = req.getParameter("cache");
		String role = req.getParameter("role");
		FilmActor.setCodFilm(Integer.parseInt(codPelicula));
		FilmActor.setCodActor(Integer.parseInt(codActor));
		FilmActor.setCache(Integer.parseInt(cache));
		FilmActor.setRole(role);
		return FilmActor;
	}

	public Actor assembleActorFromRequest(HttpServletRequest req) {
		return ExamAssembler.assembleActorFrom(req);
	}

	public Film assembleFilmFromRequest(HttpServletRequest req) {
		return ExamAssembler.assemblePeliculaFrom(req);
	}

	public Director assembleDirectorFromRequest(HttpServletRequest req) {
		return ExamAssembler.assembleDirectorFrom(req);
	}
}