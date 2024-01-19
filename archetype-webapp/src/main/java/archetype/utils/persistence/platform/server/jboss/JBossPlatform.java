package archetype.utils.persistence.platform.server.jboss;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.spi.PersistenceUnitInfo;

import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.helper.JPAClassLoaderHolder;
import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.platform.server.ServerPlatformBase;
import org.eclipse.persistence.services.mbean.MBeanDevelopmentServices;
import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.transaction.jboss.JBossTransactionController;
import org.jboss.mx.util.MBeanServerLocator;

import archetype.utils.persistence.services.jboss.MBeanJBossRuntimeServices;


public class JBossPlatform extends ServerPlatformBase {

	public static final String			JMX_REGISTER_DEV_MBEAN_PROPERTY	= "eclipselink.register.dev.mbean";
	public static final String			JMX_REGISTER_RUN_MBEAN_PROPERTY	= "eclipselink.register.run.mbean";
	private static final String			JMX_REGISTRATION_PREFIX			= "EclipseLink:Name=";
	private static Logger				logger							= Logger.getLogger(JBossPlatform.class);

	protected boolean					shouldRegisterDevelopmentBean	= System.getProperty(JMX_REGISTER_DEV_MBEAN_PROPERTY) != null;
	protected boolean					shouldRegisterRuntimeBean		= System.getProperty(JMX_REGISTER_RUN_MBEAN_PROPERTY) != null;
	@SuppressWarnings("unused")
	private MBeanJBossRuntimeServices	runtimeServicesMBean;

	public JBossPlatform(DatabaseSession newDatabaseSession) {
		super(newDatabaseSession);
	}

	@SuppressWarnings("rawtypes")
	public Class getExternalTransactionControllerClass() {
		if (externalTransactionControllerClass == null) {
			externalTransactionControllerClass = JBossTransactionController.class;
		}
		return externalTransactionControllerClass;
	}

	public JPAClassLoaderHolder getNewTempClassLoader(PersistenceUnitInfo puInfo) {
		// Bug 6460732: Use real classLoader instead of getNewTempClassLoader
		// for now to avoid a JBoss NPE on loadClass()
		ClassLoader realClassLoader = puInfo.getClassLoader();
		AbstractSessionLog.getLog().log(AbstractSessionLog.WARNING, "persistence_unit_processor_jboss_temp_classloader_bypassed",
				puInfo.getPersistenceUnitName(), realClassLoader);
		return new JPAClassLoaderHolder(realClassLoader, false);
	}

	public void serverSpecificRegisterMBean() {
		MBeanServer mBeanServer = null;
		Context initialContext = null;
		ObjectName name = null;
		String sessionName = getMBeanSessionName();
		if (null != sessionName && (shouldRegisterDevelopmentBean || shouldRegisterRuntimeBean)) {
			try {
				initialContext = new InitialContext();
				mBeanServer = MBeanServerLocator.locateJBoss();
				if (shouldRegisterDevelopmentBean) {
					try {
						name = new ObjectName(JMX_REGISTRATION_PREFIX + "Development-" + sessionName + ",Type=Configuration");
					} catch (MalformedObjectNameException mne) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", mne);
					} catch (Exception exception) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", exception);
					}

					MBeanDevelopmentServices mbean = new MBeanDevelopmentServices(getDatabaseSession());
					ObjectInstance info = null;
					try {
						info = mBeanServer.registerMBean(mbean, name);
					} catch (InstanceAlreadyExistsException iaee) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", iaee);
					} catch (MBeanRegistrationException registrationProblem) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", registrationProblem);
					} catch (Exception e) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", e);
					}
					AbstractSessionLog.getLog().log(SessionLog.FINEST, "registered_mbean", info);
				}
				if (shouldRegisterRuntimeBean) {
					try {
						name = new ObjectName(JMX_REGISTRATION_PREFIX + "Session(" + sessionName + ")");
						AbstractSessionLog.getLog().log(SessionLog.INFO, "ObjectName: " + JMX_REGISTRATION_PREFIX + "Session(" + sessionName + ")");
					} catch (MalformedObjectNameException mne) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", mne);
					} catch (Exception exception) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", exception);
					}

					MBeanJBossRuntimeServices mBeanRuntimeServices = new MBeanJBossRuntimeServices(getDatabaseSession());
					ObjectInstance runtimeInstance = null;
					try {
						runtimeInstance = mBeanServer.registerMBean(mBeanRuntimeServices, name);
						runtimeServicesMBean = mBeanRuntimeServices;
					} catch (InstanceAlreadyExistsException iaee) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", iaee);
					} catch (MBeanRegistrationException registrationProblem) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", registrationProblem);
					} catch (Exception e) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", e);
					}
					AbstractSessionLog.getLog().log(SessionLog.FINEST, "registered_mbean", runtimeInstance);
				}
			} catch (Exception exception) {
				AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_registering_mbean", exception);
			} finally {
				try {
					mBeanServer = null;
					if (null != initialContext) {
						initialContext.close();
					}
				} catch (NamingException ne) {
					// exceptions on context close will be ignored, the context
					// will be GC'd
					logger.error("exception on context close, this will be ignored, the context will be GC'd");
				}
			}
		}
	}

	public void serverSpecificUnregisterMBean() {
		MBeanServer mBeanServer = null;
		ObjectName name = null;
		String sessionName = getMBeanSessionName();
		Context initialContext = null;
		if (null != sessionName && (shouldRegisterDevelopmentBean || shouldRegisterRuntimeBean)) {
			try {
				initialContext = new InitialContext();
				mBeanServer = MBeanServerLocator.locateJBoss();
				// Attempt to register new mBean with the server
				if (shouldRegisterDevelopmentBean) {
					try {
						name = new ObjectName(JMX_REGISTRATION_PREFIX + "Development_" + sessionName + ",Type=Configuration");
					} catch (MalformedObjectNameException mne) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", mne);
					} catch (Exception exception) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", exception);
					}

					AbstractSessionLog.getLog().log(SessionLog.FINEST, "unregistering_mbean", name);
					try {
						mBeanServer.unregisterMBean(name);
					} catch (InstanceNotFoundException inf) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", inf);
					} catch (MBeanRegistrationException mbre) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", mbre);
					}
				}

				if (shouldRegisterRuntimeBean) {
					try {
						name = new ObjectName(JMX_REGISTRATION_PREFIX + "Session(" + sessionName + ")");
					} catch (MalformedObjectNameException mne) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", mne);
					} catch (Exception exception) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", exception);
					}

					AbstractSessionLog.getLog().log(SessionLog.FINEST, "unregistering_mbean", name);
					try {
						mBeanServer.unregisterMBean(name);
					} catch (InstanceNotFoundException inf) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", inf);
					} catch (MBeanRegistrationException registrationProblem) {
						AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", registrationProblem);
					}
				}
			} catch (NamingException ne) {
				AbstractSessionLog.getLog().log(SessionLog.WARNING, "failed_to_find_mbean_server", ne);
			} catch (Exception exception) {
				// Trap a possible JBoss specific
				// [jboss.management.NoAccessRuntimeException]
				AbstractSessionLog.getLog().log(SessionLog.WARNING, "problem_unregistering_mbean", exception);
			} finally {
				// de reference the mbean
				runtimeServicesMBean = null;
				// close the context
				// see http://forums.bea.com/thread.jspa?threadID=600004445
				// see http://e-docs.bea.com/wls/docs81/jndi/jndi.html#471919
				// see http://e-docs.bea.com/wls/docs100/jndi/jndi.html#wp467275
				try {
					mBeanServer = null;
					if (null != initialContext) {
						initialContext.close();
					}
				} catch (NamingException ne) {
					// exceptions on context close will be ignored, the context
					// will be GC'd
					logger.error("exception on context close, this will be ignored, the context will be GC'd");
				}
			}
		}
	}

	private String getMBeanSessionName() {
		// Check for a valid session - should never occur though
		if (null != getDatabaseSession() && null != getDatabaseSession().getName()) {
			// remove any JMX reserved characters when the session name is
			// file:/drive:/directory
			return getDatabaseSession().getName().replaceAll("[=,:]", "_");
		} else {
			AbstractSessionLog.getLog().log(SessionLog.WARNING, "session_key_for_mbean_name_is_null");
			return null;
		}
	}
}
