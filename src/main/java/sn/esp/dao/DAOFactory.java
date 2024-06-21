package sn.esp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

	private String url;
	private String username;
	private String password;

	public DAOFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DAOFactory getInstance() throws DAOConfigurationException {

		String url;
		String username;
		String password;
		String driver;

		url = "jdbc:mysql://localhost:3306/UserManagement";
		driver = "org.mysql.Driver";
		username = "mamefatou";
		password = "mamefatou123";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Echec du chargement du driver.", e);
		}

		return new DAOFactory(url, username, password);

	}

	public Connection getConnexion() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public CompteUtilisateurDao getCompteUtilisateurDao() {
		return new CompteUtilisateurDaoImpl(this);
	}

}
