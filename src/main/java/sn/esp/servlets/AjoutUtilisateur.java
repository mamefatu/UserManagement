package sn.esp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.esp.beans.CompteUtilisateur;
import sn.esp.dao.CompteUtilisateurDao;
import sn.esp.dao.DAOFactory;
import sn.esp.forms.UtilisateursForm;

@WebServlet("/ajoutUtilisateur")
public class AjoutUtilisateur extends HttpServlet {

	private CompteUtilisateurDao compteUtilisateurDao;

	public void init() {
		Object objet = getServletContext().getAttribute("daoFactory");
		DAOFactory daoFactory = (DAOFactory) objet;
		compteUtilisateurDao = daoFactory.getCompteUtilisateurDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtilisateursForm form = new UtilisateursForm(compteUtilisateurDao);

		CompteUtilisateur utilisateur = form.trouverCompteUtilisateur(request);
		request.setAttribute("utilisateur", utilisateur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtilisateursForm form = new UtilisateursForm(compteUtilisateurDao);
		CompteUtilisateur compteUtilisateur;

		compteUtilisateur = form.ajouterCompteUtilisateur(request);

		request.setAttribute("form", form);

		request.setAttribute("utilisateur", compteUtilisateur);

		if (!form.getErreurs().isEmpty())
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutUtilisateur.jsp").forward(request, response);
		else
			response.sendRedirect(request.getContextPath() + "/accueil");

	}

}