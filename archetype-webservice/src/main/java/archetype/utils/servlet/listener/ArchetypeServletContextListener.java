package archetype.utils.servlet.listener;

import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.xml.DOMConfigurator;

public class ArchetypeServletContextListener implements ServletContextListener {

	private static Logger	logger	= Logger.getLogger(ArchetypeServletContextListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		if (logger.isDebugEnabled()) {
			logger.debug("Context destroyed.");
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		URL url;
		//CHECKSTYLE:OFF
		System.out.println("Loading Archetype Webservice Context...");
		//CHECKSTYLE:ON
		try {
			ServletContext context = arg0.getServletContext();
			System.setProperty("rootPath", context.getRealPath("/"));
			
			url = Loader.getResource("config/webservice-log4j.xml");
			DOMConfigurator.configure(url);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Log4j loaded.");
			}
			
		} catch (Exception e) {
			//CHECKSTYLE:OFF
			System.out.println("Error loading Archetype Webservice Context");
			System.exit(0);
			//CHECKSTYLE:ON
		}

	}
	
}
