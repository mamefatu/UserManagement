package sn.esp.dao;

import java.util.List;

import sn.esp.beans.CompteUtilisateur;

public interface CompteUtilisateurDao {

	void creer(CompteUtilisateur compteUtilisateur) throws DAOException;

	void modifier(CompteUtilisateur compteUtilisateur) throws DAOException;

	void supprimer(String email) throws DAOException;

	CompteUtilisateur trouver(String email) throws DAOException;

	public List<CompteUtilisateur> lister() throws DAOException;

}
