package test.archetype;

import javax.naming.NamingException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public abstract class AbstractTest {

	private static SimpleNamingContextBuilder	simpleNamingContextBuilder	= new SimpleNamingContextBuilder();
	private static Logger						logger						= LoggerFactory.getLogger(AbstractTest.class);
	protected ApplicationContext				applicationContext;

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			simpleNamingContextBuilder.activate();
		} catch (IllegalStateException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (NamingException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		try {
			applicationContext = new ClassPathXmlApplicationContext(new String[] {"/beans/application/context/archetype-webserviceclient-log4j.xml",});
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	@AfterClass
	public static void setDown() {
		// simpleNamingContextBuilder.deactivate();
		LogManager.shutdown();
	}

}
