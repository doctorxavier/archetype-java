package test.archetype.ws.manager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.utils.jibx.JibxMarshaller;
import archetype.ws.manager.FatherManager;
import archetype.ws.model.request.GetFathers;
import archetype.ws.model.response.Fathers;

public class TestFatherManager extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestFatherManager.class);

	@Test
	public void testGetFathers() {
		try {
			FatherManager fatherManager = (FatherManager) applicationContext.getBean("fatherManager");
			GetFathers getFathers = new GetFathers();
			getFathers.setStart(1);
			getFathers.setRows(3);
			Fathers fathers = fatherManager.getFathers(getFathers);
			
			JibxMarshaller<Fathers> marshaller = new JibxMarshaller<Fathers>();
			
			Object obj = marshaller.toString(fathers);

			if (obj instanceof String) {
				String value = (String) obj;
				logger.info(value);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
