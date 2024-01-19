package archetype.utils.persistence.services.jboss;

import java.util.List;
import java.util.Vector;

import org.eclipse.persistence.services.mbean.MBeanRuntimeServicesMBean;

public interface MBeanJBossRuntimeServicesMBean extends MBeanRuntimeServicesMBean {

	/**
	 * Answer the name of the EclipseLink session this MBean represents.
	 */
	String getSessionName();

	/**
	 * Answer the type of the EclipseLink session this MBean represents.
	 * Types include: "ServerSession", "DatabaseSession", "SessionBroker"
	 */
	String getSessionType();

	/**
	 * Provide an instance of 2 Dimensional Array simulating tabular format
	 * information about all
	 * classes in the session whose class names match the provided filter.
	 * 
	 * The 2 Dimensional array contains each item with values being row object
	 * array. Each row object array
	 * represents EclipseLink class details info with respect to below
	 * attributes:
	 * ["Class Name", "Parent Class Name", "Cache Type", "Configured Size",
	 * "Current Size"]
	 * 
	 */
	Object[][] getClassSummaryDetailsUsingFilter(String filter);

	/**
	 * Provide an instance of 2 Dimensional Array simulating tabular format
	 * information about all
	 * classes in the session.
	 * 
	 * The 2 Dimensional array contains each item with values being row object
	 * array. Each row object array
	 * represents EclipseLink class details info with respect to below
	 * attributes:
	 * ["Class Name", "Parent Class Name", "Cache Type", "Configured Size",
	 * "Current Size"]
	 * 
	 */
	Object[][] getClassSummaryDetails();

	/**
	 * Provide a list of instance of ClassSummaryDetail containing information
	 * about the
	 * classes in the session whose class names match the provided filter.
	 * 
	 * ClassSummaryDetail is a model specific class that can be used internally
	 * by the Portable JMX Framework to
	 * convert class attribute to JMX required open type, it has:-
	 * 1. model specific type that needs to be converted : ["Class Name",
	 * "Parent Class Name", "Cache Type", "Configured Size", "Current Size"]
	 * 2. convert methods.
	 * 
	 * @param filter
	 *            A comma separated list of strings to match against.
	 * @return A ArrayList of instance of ClassSummaryDetail containing class
	 *         information for the class names that match the filter.
	 */
	List getClassSummaryDetailsUsingFilterArray(String filter);

	/**
	 * Provide a list of instance of ClassSummaryDetail containing information
	 * about all
	 * classes in the session.
	 * 
	 * ClassSummaryDetail is a model specific class that can be used internally
	 * by the Portable JMX Framework to
	 * convert class attribute to JMX required open type, it has:-
	 * 1. model specific type that needs to be converted : ["Class Name",
	 * "Parent Class Name", "Cache Type", "Configured Size", "Current Size"]
	 * 2. convert methods.
	 * 
	 * @return A ArrayList of instance of ClassSummaryDetail containing class
	 *         information for the class names that match the filter.
	 */
	List getClassSummaryDetailsArray();

	/**
	 * INTERNAL:
	 * This method traverses the EclipseLink descriptors and returns a Vector of
	 * the descriptor's
	 * reference class names that match the provided filter. The filter is a
	 * comma separated
	 * list of strings to match against.
	 * 
	 * @param filter
	 *            A comma separated list of strings to match against.
	 * @return A Vector of class names that match the filter.
	 */
	@SuppressWarnings("rawtypes")
	Vector getMappedClassNamesUsingFilter(String filter);

	/**
	 * getModuleName(): Answer the name of the context-root of the application
	 * that this session is associated with.
	 * Answer "unknown" if there is no module name available.
	 * Default behavior is to return "unknown" - we override this behavior here
	 * for WebLogic.
	 */
	String getModuleName();

	/**
	 * Answer the EclipseLink log level at deployment time. This is read-only.
	 */
	String getDeployedEclipseLinkLogLevel();

	/**
	 * Answer the EclipseLink log level that is changeable.
	 * This does not affect the log level in the project (i.e. The next
	 * time the application is deployed, changes are forgotten)
	 */
	String getCurrentEclipseLinkLogLevel();

	/**
	 * Set the EclipseLink log level to be used at runtime.
	 * 
	 * This does not affect the log level in the project (i.e. The next
	 * time the application is deployed, changes are forgotten)
	 * 
	 * @param String
	 *            newLevel: new log level
	 */
	void setCurrentEclipseLinkLogLevel(String newLevel);

	/**
	 * This method is used to get the type of profiling.
	 * Possible values are: "EclipseLink" or "None".
	 */
	String getProfilingType();

	/**
	 * This method is used to select the type of profiling.
	 * Valid values are: "EclipseLink" or "None". These values are not case
	 * sensitive.
	 * null is considered to be "None".
	 */
	void setProfilingType(String profileType);

	/**
	 * This method is used to turn on EclipseLink Performance Profiling
	 */
	void setUseEclipseLinkProfiling();

	/**
	 * This method answers true if EclipseLink Performance Profiling is on.
	 */
	Boolean getUsesEclipseLinkProfiling();

	/**
	 * This method is used to turn off all Performance Profiling, DMS or
	 * EclipseLink.
	 */
	void setUseNoProfiling();

	/**
	 * Return the size of strings after which will be bound into the statement
	 * If we are not using a DatabaseLogin, or we're not using string binding,
	 * answer 0 (zero).
	 */
	Integer getStringBindingSize();

	/**
	 * This method will return a long indicating the exact time in Milliseconds
	 * that the
	 * session connected to the database.
	 */
	Long getTimeConnectionEstablished();

	/**
	 * This method will return if batchWriting is in use or not.
	 */
	Boolean getUsesJDBCBatchWriting();

	/**
	 * Shows if Byte Array Binding is turned on or not
	 */
	Boolean getUsesByteArrayBinding();

	/**
	 * Shows if native SQL is being used
	 */
	Boolean getUsesNativeSQL();

	/**
	 * This method indicates if streams are being used for binding
	 */
	Boolean getUsesStreamsForBinding();

	/**
	 * This method indicates if Strings are being bound
	 */
	Boolean getUsesStringBinding();

	/**
	 * Used to clear the statement cache. Only valid if statements are being
	 * cached
	 */
	void clearStatementCache();

	/**
	 * This method will print the available Connection pools to the SessionLog.
	 * 
	 * @return void
	 */
	void printAvailableConnectionPools();

	/**
	 * This method will retrieve the max size of a particular connection pool
	 * 
	 * @param poolName
	 *            the name of the pool to get the max size for
	 * @return Integer for the max size of the pool. Return -1 if pool doesn't
	 *         exist.
	 */
	Integer getMaxSizeForPool(String poolName);

	/**
	 * This method will retrieve the min size of a particular connection pool
	 * 
	 * @param poolName
	 *            the name of the pool to get the min size for
	 * @return Integer for the min size of the pool. Return -1 if pool doesn't
	 *         exist.
	 */
	Integer getMinSizeForPool(String poolName);

	/**
	 * This method is used to output those Class Names that have identity Maps
	 * in the Session.
	 * Please note that SubClasses and aggregates will be missing form this list
	 * as they do not have
	 * separate identity maps.
	 * 
	 * @return void
	 */
	void printClassesInSession();

	/**
	 * This method will log the objects in the Identity Map.
	 * There is no particular order to these objects.
	 * 
	 * @param className
	 *            the fully qualified classname identifying the identity map
	 * @exception thrown
	 *                then the IdentityMap for that class name could not be
	 *                found
	 */
	void printObjectsInIdentityMap(String className) throws ClassNotFoundException;

	/**
	 * This method will log the types of Identity Maps in the session.
	 */
	void printAllIdentityMapTypes();

	/**
	 * This method will log all objects in all Identity Maps in the session.
	 */
	void printObjectsInIdentityMaps();

	/**
	 * This method will SUM and return the number of objects in all Identity
	 * Maps in the session.
	 */
	Integer getNumberOfObjectsInAllIdentityMaps();

	/**
	 * This method will answer the number of persistent classes contained in the
	 * session.
	 * This does not include aggregates.
	 */
	Integer getNumberOfPersistentClasses();

	/**
	 * This method will log the instance level locks in all Identity Maps in the
	 * session.
	 */
	void printIdentityMapLocks();

	/**
	 * This method will log the instance level locks in the Identity Map for the
	 * given class in the session.
	 */
	void printIdentityMapLocks(String registeredClassName);

	/**
	 * This method assumes EclipseLink Profiling (as opposed to Java profiling).
	 * This will log at the INFO level a summary of all elements in the profile.
	 */
	void printProfileSummary();

	/**
	 * This method assumes EclipseLink Profiling (as opposed to Java profiling).
	 * This will log at the INFO level a summary of all elements in the profile,
	 * categorized
	 * by Class.
	 */
	void printProfileSummaryByClass();

	/**
	 * This method assumes EclipseLink Profiling (as opposed to Java profiling).
	 * This will log at the INFO level a summary of all elements in the profile,
	 * categorized
	 * by Query.
	 */
	void printProfileSummaryByQuery();

	/**
	 * Return the log type, either "EclipseLink", "Java" or the simple name of
	 * the logging class used.
	 * 
	 * @return the log type
	 */
	String getLogType();

	/**
	 * Return the database platform used by the DatabaseSession.
	 * 
	 * @return String databasePlatform
	 */
	String getDatabasePlatform();

	/**
	 * Return JDBCConnection detail information. This includes URL and
	 * datasource information.
	 */
	String getJdbcConnectionDetails();

	/**
	 * Return connection pool type. Values include: "Internal", "External" and
	 * "N/A".
	 */
	String getConnectionPoolType();

	/**
	 * Return db driver class name. This only applies to DefaultConnector.
	 * Return "N/A" otherwise.
	 */
	String getDriver();

	/**
	 * Return the log filename. This returns the fully qualified path of the log
	 * file when
	 * EclipseLink logging is enabled. Null is returned otherwise.
	 * 
	 * @return String logFilename
	 */
	String getLogFilename();

	/**
	 * This method is used to initialize the identity maps in the session.
	 */
	void initializeAllIdentityMaps();

	/**
	 * This method is used to initialize the identity maps specified by the
	 * Vector of classNames.
	 * 
	 * @param classNames
	 *            String[] of fully qualified classnames identifying the
	 *            identity maps to initialize
	 */
	void initializeIdentityMaps(String[] classNames) throws ClassNotFoundException;

	/**
	 * This method is used to initialize the identity maps specified by
	 * className.
	 * 
	 * @param className
	 *            the fully qualified classnames identifying the identity map to
	 *            initialize
	 */
	void initializeIdentityMap(String className) throws ClassNotFoundException;

	/**
	 * This method is used to invalidate the identity maps in the session.
	 */
	void invalidateAllIdentityMaps();

	/**
	 * This method is used to invalidate the identity maps specified by the
	 * String[] of classNames.
	 * 
	 * @param classNames
	 *            String[] of fully qualified classnames identifying the
	 *            identity maps to invalidate
	 * @param recurse
	 *            Boolean indicating if we want to invalidate the children
	 *            identity maps too
	 */
	void invalidateIdentityMaps(String[] classNamesParam, Boolean recurse) throws ClassNotFoundException;

	/**
	 * This method is used to invalidate the identity maps specified by
	 * className. This does not
	 * invalidate the children identity maps
	 * 
	 * @param className
	 *            the fully qualified classname identifying the identity map to
	 *            invalidate
	 */
	void invalidateIdentityMap(String className) throws ClassNotFoundException;

	/**
	 * This method is used to invalidate the identity maps specified by
	 * className.
	 * 
	 * @param className
	 *            the fully qualified classname identifying the identity map to
	 *            invalidate
	 * @param recurse
	 *            Boolean indicating if we want to invalidate the children
	 *            identity maps too
	 */
	void invalidateIdentityMap(String className, Boolean recurse) throws ClassNotFoundException;

	/**
	 * Return whether this session is an EclipseLink JPA session.
	 * The absence of this function or a value of false will signify that the
	 * session
	 * belongs to a provider other than EclipseLink.
	 * 
	 * @return
	 */
	boolean isJPASession();

}
