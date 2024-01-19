package test.archetype.dao;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Calendar;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

public class TestLocations {

	private static Logger	logger	= Logger.getLogger(TestLocations.class);

	@Test
	public void main() {
		try {
			@SuppressWarnings("unused")
			URL url = ResourceUtils.getURL("classpath:config/log4j-model.xml");
			Calendar now = Calendar.getInstance();
			logger.info(now.getTimeInMillis());
			String code = Long.valueOf(now.getTimeInMillis()).toString();
			String code2 = code;
			String code3 = code2.substring(code2.length() - 3, code2.length());
			logger.info(code3);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
}
