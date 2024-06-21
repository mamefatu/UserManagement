package sn.esp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtilitaire {
	
	public static PreparedStatement prepareStatement( Connection connexion, String sql, boolean keys, Object...objects) throws SQLException {
		PreparedStatement preparedStatement= connexion.prepareStatement(sql, keys? Statement.RETURN_GENERATED_KEYS: Statement.NO_GENERATED_KEYS);
		for(int i=0; i<objects.length; i++) {
			preparedStatement.setObject(i+1, objects[i]);
		}
		return preparedStatement;
	}
	
	public static void fermetureSilencieuse(ResultSet resultat){
		if(resultat!=null) {
			try {
				resultat.close();
			}catch(SQLException e) {
				System.out.println("Echec de la fermeture du ResultSet: "+e.getMessage());
			}
		}
	}
	
	public static void fermetureSilencieuse(PreparedStatement preparedStatement){
		if(preparedStatement!=null) {
			try {
				preparedStatement.close();
			}catch(SQLException e) {
				System.out.println("Echec de la fermeture du PreparedStatement: "+e.getMessage());
			}
		}
	}	
	
	public static void fermetureSilencieuse(Connection connexion){
		if(connexion!=null) {
			try {
				connexion.close();
			}catch(SQLException e) {
				System.out.println("Echec de la fermeture de la connexion: "+e.getMessage());
			}
		}
	}
	
	public static void fermeturesSilencieuses(PreparedStatement preparedStatement, Connection connexion) {
		fermetureSilencieuse(preparedStatement);
		fermetureSilencieuse(connexion);
	}
	
public static void fermeturesSilencieuses(ResultSet resultat, PreparedStatement preparedStatement, Connection connexion) {
		fermetureSilencieuse(resultat);
		fermetureSilencieuse(preparedStatement);
		fermetureSilencieuse(connexion);
	}

}
