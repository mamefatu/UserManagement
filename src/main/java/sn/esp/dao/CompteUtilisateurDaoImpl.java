package sn.esp.dao;

import static sn.esp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static sn.esp.dao.DAOUtilitaire.prepareStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sn.esp.beans.CompteUtilisateur;

public class CompteUtilisateurDaoImpl implements CompteUtilisateurDao {

	private DAOFactory daoFactory;

	public CompteUtilisateurDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public CompteUtilisateur map(ResultSet resultat) throws SQLException {

		CompteUtilisateur compteUtilisateur = new CompteUtilisateur();
		compteUtilisateur.setAdresseUtilisateur(resultat.getString("adresse_utilisateur"));
		compteUtilisateur.setMailUtilisateur((resultat.getString("mail_utilisateur")));
		compteUtilisateur.setMotDePasse((resultat.getString("mot_de_passe_compte")));
		compteUtilisateur.setNationaliteUtilisateur("Sénégal");
		compteUtilisateur.setNom(resultat.getString("nom"));
		compteUtilisateur.setPrenom(resultat.getString("prenom"));
		compteUtilisateur.setTelUtilisateur("775896565");
		compteUtilisateur.setTypeUtilisateur(resultat.getString("type_utilisateur"));

		return compteUtilisateur;
	}

	@Override
	public void creer(CompteUtilisateur compteUtilisateur) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnexion();
			String mailUtilisateur = compteUtilisateur.getMailUtilisateur();
			String motDePasse = compteUtilisateur.getMotDePasse();
			String adresseUtilisateur = compteUtilisateur.getAdresseUtilisateur();
			String nationaliteUtilisateur = "Sénégal";
			String nom = compteUtilisateur.getNom();
			String prenom = compteUtilisateur.getPrenom();
			String telUtilisateur = "775896545";
			String typeUtilisateur = compteUtilisateur.getTypeUtilisateur();

			preparedStatement = prepareStatement(connexion,
					"INSERT INTO compte_utilisateur(mail_utilisateur, mot_de_passe_compte, adresse_utilisateur, nationalite_utilisateur, nom, prenom, tel_utilisateur, type_utilisateur) VALUES(?, ?, ?, ?, ?, ?, ?, ?);",
					false, mailUtilisateur, motDePasse, adresseUtilisateur, nationaliteUtilisateur, nom, prenom,
					telUtilisateur, typeUtilisateur);
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				throw new DAOException("Echec de l'insertion du compteUtilisateur.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
	}

	@Override
	public CompteUtilisateur trouver(String mailUtilisateur) throws DAOException {
		CompteUtilisateur compteUtilisateur = null;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = prepareStatement(connexion,
					"SELECT * FROM compte_utilisateur WHERE mail_utilisateur=?;", false, mailUtilisateur);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				compteUtilisateur = map(resultat);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultat, preparedStatement, connexion);
		}

		return compteUtilisateur;
	}

	@Override
	public void modifier(CompteUtilisateur compteUtilisateur) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnexion();
			String mailUtilisateur = compteUtilisateur.getMailUtilisateur();
			String motDePasse = compteUtilisateur.getMotDePasse();
			String adresseUtilisateur = compteUtilisateur.getAdresseUtilisateur();
			String nationaliteUtilisateur = "Sénégal";
			String nom = compteUtilisateur.getNom();
			String prenom = compteUtilisateur.getPrenom();
			String telUtilisateur = "778589888";
			String typeUtilisateur = compteUtilisateur.getTypeUtilisateur();

			preparedStatement = prepareStatement(connexion,
					"UPDATE compte_utilisateur SET mot_de_passe_compte=?, adresse_utilisateur=?, nationalite_utilisateur=?, nom=?, prenom=?, tel_utilisateur=?, type_utilisateur=? WHERE mail_utilisateur=? ;",
					false, motDePasse, adresseUtilisateur, nationaliteUtilisateur, nom, prenom, telUtilisateur,
					typeUtilisateur, mailUtilisateur);
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				throw new DAOException("Echec de la modification du compteUtilisateur.");
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void supprimer(String mail_utilisateur) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = prepareStatement(connexion, "DELETE FROM compte_utilisateur WHERE mail_utilisateur=? ;",
					false, mail_utilisateur);
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				throw new DAOException("Echec de la suppression du compte utilisateur.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
	}

	@Override
	public List<CompteUtilisateur> lister() throws DAOException {
		List<CompteUtilisateur> comptesUtilisateur = new ArrayList<CompteUtilisateur>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = prepareStatement(connexion, "SELECT * FROM compte_utilisateur;", false);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				comptesUtilisateur.add(map(resultat));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultat, preparedStatement, connexion);
		}

		return comptesUtilisateur;
	}

}
