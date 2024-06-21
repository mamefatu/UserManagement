package sn.esp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.esp.dao.CompteUtilisateurDao;
import sn.esp.dao.DAOFactory;
import sn.esp.forms.UtilisateursForm;

@WebServlet("/suppressionUtilisateur")
public class SuppressionUtilisateur extends HttpServlet {

	private CompteUtilisateurDao utilisateurDao;

	public void init() {
		Object objet = getServletContext().getAttribute("daoFactory");
		DAOFactory daoFactory = (DAOFactory) objet;
		utilisateurDao = daoFactory.getCompteUtilisateurDao();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateursForm form = new UtilisateursForm(utilisateurDao);
		form.supprimerCompteUtilisateur(request);

		response.sendRedirect(request.getContextPath() + "/accueil");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}