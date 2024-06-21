package sn.esp.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import sn.esp.beans.CompteUtilisateur;
import sn.esp.dao.CompteUtilisateurDao;
import sn.esp.dao.DAOException;

public class UtilisateursForm {

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private CompteUtilisateurDao compteUtilisateurDao;

	public UtilisateursForm(CompteUtilisateurDao compteUtilisateurDao) {
		this.compteUtilisateurDao = compteUtilisateurDao;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void traiterLogin(CompteUtilisateur compteUtilisateur, String login) {

		try {
			validationLogin(login);
		} catch (Exception e) {
			setErreurs("login", e.getMessage());
		}
		compteUtilisateur.setMailUtilisateur(login);

	}

	public void traiterMotDePasse(CompteUtilisateur compteUtilisateur, String motDePasse, String confirmation) {
		try {
			validationMotDePasse(motDePasse, confirmation);
		} catch (Exception e) {
			setErreurs("motDePasse", e.getMessage());
		}

		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-256");
		passwordEncryptor.setPlainDigest(false);
		String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasse);

		compteUtilisateur.setMotDePasse(motDePasseChiffre);
	}

	public void traiterNom(CompteUtilisateur compteUtilisateur, String nom) {
		try {
			validationChamp(nom);
		} catch (Exception e) {
			setErreurs("nom", e.getMessage());
		}
		compteUtilisateur.setNom(nom);
	}

	public void traiterPrenom(CompteUtilisateur compteUtilisateur, String prenom) {
		try {
			validationChamp(prenom);
		} catch (Exception e) {
			setErreurs("prenom", e.getMessage());
		}
		compteUtilisateur.setPrenom(prenom);
	}

	public void traiterAdresse(CompteUtilisateur compteUtilisateur, String adresse) {
		try {
			validationAdresse(adresse);
		} catch (Exception e) {
			setErreurs("adresse", e.getMessage());
		}
		compteUtilisateur.setAdresseUtilisateur(adresse);
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private void validationLogin(String login) throws Exception {
		if (login != null) {
			if (!login.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
				throw new Exception("Please type a valid email.");
			else if (compteUtilisateurDao.trouver(login) != null)
				throw new Exception("This user already exists.");
		} else {
			throw new Exception("Required");
		}
	}

	private void validationMotDePasse(String motDePasse, String confirmation) throws Exception {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("The passwords do not match.");
			} else if (motDePasse.length() < 3) {
				throw new Exception("The password must have at least 3 characters.");
			}
		} else {
			throw new Exception("Please enter and confirm your password");
		}
	}

	private void validationChamp(String champ) throws Exception {
		if (champ != null && champ.length() < 2) {
			throw new Exception("Field must have at least two characters.");
		}
		if (champ == null) {
			throw new Exception("Required");
		}
	}

	private void validationAdresse(String champ) throws Exception {
		if (champ != null && champ.length() < 3) {
			throw new Exception("This field must have at least 3 characters");
		}
		if (champ == null) {
			throw new Exception("Required");
		}
	}

	private String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0)
			return null;
		else
			return valeur.trim();
	}

	private void setErreurs(String nomChamp, String message) {
		erreurs.put(nomChamp, message);
	}

	public CompteUtilisateur ajouterCompteUtilisateur(HttpServletRequest request) {

		String nom = getValeurChamp(request, "nom");
		String prenom = getValeurChamp(request, "prenom");
		String motDePasse = getValeurChamp(request, "motdepasse");
		String confirmation = getValeurChamp(request, "confirmation");
		String email = getValeurChamp(request, "login");
		String adresse = getValeurChamp(request, "adresse");

		CompteUtilisateur compteUtilisateur = new CompteUtilisateur();

		try {

			traiterLogin(compteUtilisateur, email);
			traiterNom(compteUtilisateur, nom);
			traiterPrenom(compteUtilisateur, prenom);
			traiterMotDePasse(compteUtilisateur, motDePasse, confirmation);
			traiterAdresse(compteUtilisateur, adresse);
			compteUtilisateur.setTypeUtilisateur("requerant");

			if (erreurs.isEmpty()) {
				compteUtilisateurDao.creer(compteUtilisateur);
				resultat = "Registered successfully";
			} else {
				resultat = "Registration failed";
			}
		} catch (DAOException e) {
			resultat = "An internal error occured.";
			e.printStackTrace();
		}

		return compteUtilisateur;

	}

	public CompteUtilisateur modifierCompteUtilisateur(HttpServletRequest request) {

		String nom = getValeurChamp(request, "nom");
		String prenom = getValeurChamp(request, "prenom");
		String motDePasse = getValeurChamp(request, "motdepasse");
		String confirmation = getValeurChamp(request, "confirmation");
		String email = getValeurChamp(request, "login");
		String adresse = getValeurChamp(request, "adresse");

		CompteUtilisateur compteUtilisateur = new CompteUtilisateur();

		try {

			traiterNom(compteUtilisateur, nom);
			traiterPrenom(compteUtilisateur, prenom);
			traiterMotDePasse(compteUtilisateur, motDePasse, confirmation);
			traiterAdresse(compteUtilisateur, adresse);
			compteUtilisateur.setTypeUtilisateur("requerant");
			compteUtilisateur.setMailUtilisateur(email);

			if (erreurs.isEmpty()) {
				compteUtilisateurDao.modifier(compteUtilisateur);
				resultat = "Edited successfully";
			} else {
				resultat = "Edition failed";
			}
		} catch (DAOException e) {
			resultat = "Une erreur interne liée au serveur est survenue.";
			e.printStackTrace();
		}

		return compteUtilisateur;

	}

	public CompteUtilisateur trouverCompteUtilisateur(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String login = getValeurChamp(request, "login");

		CompteUtilisateur compteUtilisateur = new CompteUtilisateur();

		try {
			if (erreurs.isEmpty()) {
				compteUtilisateur = compteUtilisateurDao.trouver(login);
				resultat = "Connected successfully.";
			} else {
				resultat = "Connection failed";
			}
		} catch (DAOException e) {
			resultat = "an internal error occured.";
			e.printStackTrace();
		}

		return compteUtilisateur;
	}

	public List<CompteUtilisateur> listerComptesUtilisateur(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String login = getValeurChamp(request, "login");

		List<CompteUtilisateur> comptesUtilisateur;

		try {
			comptesUtilisateur = compteUtilisateurDao.lister();
			resultat = "Connected successfully.";
			return comptesUtilisateur;
		} catch (DAOException e) {
			resultat = "An internal error occured.";
			e.printStackTrace();
			return null;
		}

	}

	public boolean supprimerCompteUtilisateur(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String login = getValeurChamp(request, "login");

		try {
			compteUtilisateurDao.supprimer(login);
			resultat = "Connected successfully.";
			return true;
		} catch (DAOException e) {
			resultat = "Une erreur inattendue est survenue. Veuillez réessayer plus tard.";
			e.printStackTrace();
			return false;
		}

	}

}
