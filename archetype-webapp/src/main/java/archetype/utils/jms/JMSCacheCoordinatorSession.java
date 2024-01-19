package archetype.utils.jms;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.coordination.CommandProcessor;
import org.eclipse.persistence.sessions.coordination.RemoteCommandManager;
import org.eclipse.persistence.sessions.coordination.jms.JMSTopicTransportManager;

public class JMSCacheCoordinatorSession implements SessionCustomizer, DescriptorCustomizer {

	private static Logger	logger	= Logger.getLogger(JMSCacheCoordinatorSession.class);

	public void customize(Session session) throws Exception {
		try {
			logger.debug("Before Session Customization");

			CommandProcessor commandProcessor = (CommandProcessor) session;
			DatabaseSession databaseSession = (DatabaseSession) session;

			RemoteCommandManager rcm = new RemoteCommandManager(commandProcessor);

			JMSTopicTransportManager tm = new JMSTopicTransportManager(rcm);
			tm.setTopicHostUrl("jnp://192.168.1.139:1100");
			tm.setInitialContextFactoryName("org.jboss.naming.NamingContextFactory");
			// tm.setNamingServiceType(TransportManager.JNDI_NAMING_SERVICE);
			tm.setTopicName("/topic/archetypeTopic");
			tm.setTopicConnectionFactoryName("java:/XAConnectionFactory");
			tm.setUserName("guest");
			tm.setPassword("guest");

			rcm.setTransportManager(tm);

			databaseSession.setCommandManager(rcm);
			databaseSession.setShouldPropagateChanges(true);

			rcm.initialize();

			logger.debug("After Session Customization");
		} catch (Exception except) {
			logger.error(except.toString(), except);
			throw except;
		}
	}

	@Override
	public void customize(ClassDescriptor descriptor) throws Exception {
		try {
			logger.debug("Before ClassDescriptor Customization");

			logger.debug("After ClassDescriptor Customization");
		} catch (Exception except) {
			logger.error(except.toString(), except);
			throw except;
		}
	}
}
