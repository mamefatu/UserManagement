package sn.esp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.esp.beans.CompteUtilisateur;
import sn.esp.dao.CompteUtilisateurDao;
import sn.esp.dao.DAOFactory;
import sn.esp.forms.UtilisateursForm;

@WebServlet("/accueil")
public class Accueil extends HttpServlet {

	private CompteUtilisateurDao compteUtilisateurDao;

	public void init() {
		Object objet = getServletContext().getAttribute("daoFactory");
		DAOFactory daoFactory = (DAOFactory) objet;
		compteUtilisateurDao = daoFactory.getCompteUtilisateurDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtilisateursForm form = new UtilisateursForm(compteUtilisateurDao);

		List<CompteUtilisateur> utilisateurs = form.listerComptesUtilisateur(request);
		request.setAttribute("utilisateurs", utilisateurs);

		this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);

	}

}