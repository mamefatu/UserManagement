package sn.esp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import sn.esp.dao.DAOFactory;

/**
 * Application Lifecycle Listener implementation class InitialDaoFactory
 *
 */
@WebListener
public class InitialDaoFactory implements ServletContextListener {

	private DAOFactory daoFactory;
	
    /**
     * Default constructor. 
     */
    public InitialDaoFactory() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         daoFactory= DAOFactory.getInstance();
         ServletContext contexte= arg0.getServletContext();
         contexte.setAttribute("daoFactory", daoFactory);
    }
	
}
