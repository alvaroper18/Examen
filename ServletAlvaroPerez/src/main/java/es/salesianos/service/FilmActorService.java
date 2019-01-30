package es.salesianos.service;



import es.salesianos.model.FilmActors;
import es.salesianos.repository.Repository;

public class FilmActorService {

	private Repository repository = new Repository();

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	public void insertFilmActor(FilmActors filmActor) {
		repository.insert(filmActor);
		;
	}

	public FilmActors filterAllFilmsActor(String role) {
		return repository.filterAllFilmActors(role);
	}


}
