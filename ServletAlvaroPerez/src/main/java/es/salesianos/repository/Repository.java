package es.salesianos.repository;

import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;



public class Repository {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();


	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void insert(Actor actor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO ACTOR (NAME,YEAROFBIRTHDATE)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, actor.getName());
			preparedStatement.setInt(2, actor.getYearofbirthday());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
	}
	
	public void insertFilm(Film film) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO FILM (TITTLE,CODOWNER)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, film.getTitle());
			preparedStatement.setInt(2, film.getCodDirector());


			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
	}
	
	public void insertDirector(Director director) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO DIRECTOR (NAME)" +
					"VALUES (?)");
			preparedStatement.setString(1, director.getName());


			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
	}
	

	



	public List<Actor> searchAllActors() {
		List<Actor> listActors = new ArrayList<Actor>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = conn.prepareStatement("SELECT * FROM ACTOR");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorInDataBase = new Actor();
				
				actorInDataBase.setCod(resultSet.getInt(1));
				actorInDataBase.setName(resultSet.getString(2));
				actorInDataBase.setYearofbirthday(resultSet.getInt(3));
							
				listActors.add(actorInDataBase);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
			manager.close(conn);
		}
		return listActors;
	}
	
	public List<Director> searchAllDirectors() {
		List<Director> listDirectors = new ArrayList<Director>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			
			prepareStatement = conn.prepareStatement("SELECT * FROM DIRECTOR");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				
				Director directorInDataBase = new Director();
				
				directorInDataBase.setCod(resultSet.getInt(1));
				directorInDataBase.setName(resultSet.getString(2));
			
				listDirectors.add(directorInDataBase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
			manager.close(conn);
		}
		return listDirectors;
	}
	
	public List<Film> searchAllFilms() {
		List<Film> listFilms = new ArrayList<Film>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			
			prepareStatement = conn.prepareStatement("SELECT * FROM FILM");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				
				Film filmInDataBase = new Film();
				
				filmInDataBase.setCod(resultSet.getInt(1));
				filmInDataBase.setTitle(resultSet.getString(2));
				filmInDataBase.setCodDirector(resultSet.getInt(3));

				listFilms.add(filmInDataBase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
			manager.close(conn);
		}
		return listFilms;
	}

	public Actor searchAndDeleteActor(Integer codActor) {
		Actor actorInDatabase = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM ACTOR WHERE COD = ?");
			prepareStatement.setInt(1, codActor);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return actorInDatabase;
	}
	
	public Director searchAndDeleteDirector(Integer codDirector) {
		Director directorInDatabase = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM DIRECTOR WHERE COD = ?");
			prepareStatement.setInt(1, codDirector);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return directorInDatabase;
	}
	
	public Film searchAndDeleteFilm(Integer codFilm) {
		Film filmInDatabase = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM FILM WHERE COD = ?");
			prepareStatement.setInt(1, codFilm);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return filmInDatabase;
	}
	
	public List<Actor> filterAllActor(int beginDate, int endDate) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> listActors = new ArrayList<Actor>();
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM ACTOR WHERE yearOfBirthDate BETWEEN (?) AND (?)");
			preparedStatement.setInt(1, beginDate);
			preparedStatement.setInt(2, endDate);
			System.out.println("llego");
			System.out.println(beginDate);
			System.out.println(endDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setName(resultSet.getNString(2));
				actor.setYearofbirthday(resultSet.getInt(3));
				listActors.add(actor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return listActors;
	}
	
	public List<Actor> selectAllActor() {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> listActors = new ArrayList<Actor>();
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM ACTOR");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setName(resultSet.getNString(2));
				actor.setYearofbirthday(resultSet.getInt(3));
				listActors.add(actor);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
}
		return listActors;
	}
	
	public void insert(FilmActors filmActor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("INSERT INTO FILMACTOR (cache, role, codActor, codFilm)" + "VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, filmActor.getCache());
			preparedStatement.setString(2, filmActor.getRole());
			preparedStatement.setInt(3, filmActor.getCodActor());
			preparedStatement.setInt(4, filmActor.getCodFilm());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}

	}

	public FilmActors filterAllFilmActors(String role) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		FilmActors filmActor = null;
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM FILMACTOR WHERE ROLE = (?)");
			preparedStatement.setString(1, role);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FilmActors filmActorFromDataBase = new FilmActors();
				filmActorFromDataBase.setCache(resultSet.getInt(1));
				filmActorFromDataBase.setRole(resultSet.getString(2));
				filmActorFromDataBase.setCodActor(resultSet.getInt(3));
				filmActorFromDataBase.setCodFilm(resultSet.getInt(4));
				filmActor = filmActorFromDataBase;
			}
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor where cod=" + filmActor.getCodActor());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorFromDataBase = new Actor();
				actorFromDataBase.setName(resultSet.getString(2));
				actorFromDataBase.setYearofbirthday(resultSet.getInt(3));
				filmActor.setActor(actorFromDataBase);
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILM where cod=" + filmActor.getCodFilm());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Film filmFromDataBase = new Film();
				filmFromDataBase.setCod(resultSet.getInt(1));
				filmFromDataBase.setTitle(resultSet.getString(2));
				filmFromDataBase.setCodDirector(resultSet.getInt(3));
				filmActor.setFilm(filmFromDataBase);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return filmActor;
	}

	public Actor filterAllDirector(String name) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		Actor actor = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor WHERE name = (?)");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorfromDataBase = new Actor();
				actorfromDataBase.setCod(resultSet.getInt(1));
				actorfromDataBase.setName(resultSet.getString(2));
				actorfromDataBase.setYearofbirthday(resultSet.getInt(3));
				actor = actorfromDataBase;
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILMACTOR where codactor=" + actor.getCod());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FilmActors filmActorfromDataBase = new FilmActors();
				filmActorfromDataBase.setCodFilm(resultSet.getInt(4));
				actor.getFilmActor().add(filmActorfromDataBase);
			}

			int index = 0;
			for (FilmActors filmActor : actor.getFilmActor()) {

				preparedStatement = conn
						.prepareStatement("SELECT * FROM FILM where cod=" + filmActor.getCodFilm());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Film filmfromDataBase = new Film();
					filmfromDataBase.setTitle(resultSet.getString(2));
					filmfromDataBase.setCodDirector(resultSet.getInt(3));
					actor.getFilmActor().get(index).setFilm(filmfromDataBase);
				}
				index++;
			}
			index = 0;
			for (FilmActors filmActor : actor.getFilmActor()) {
				preparedStatement = conn.prepareStatement(
						"SELECT * FROM DIRECTOR where COD=" + filmActor.getFilm().getCodDirector());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Director directorfromDataBase = new Director();
					directorfromDataBase.setName(resultSet.getString(2));
					actor.getFilmActor().get(index).getFilm().setDirector(directorfromDataBase);
				}
				index++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return actor;

	}

}
