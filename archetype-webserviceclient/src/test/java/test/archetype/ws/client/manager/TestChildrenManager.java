package test.archetype.ws.client.manager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.utils.jibx.JibxMarshaller;
import archetype.ws.client.manager.ChildrenManager;
import archetype.ws.client.model.request.GetChildrens;
import archetype.ws.client.model.response.Childrens;

public class TestChildrenManager extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestChildrenManager.class);

	@Test
	public void testGetChildrens() {
		try {
			ChildrenManager childrenManager = new ChildrenManager();
			GetChildrens getChildrens = new GetChildrens();
			getChildrens.setStart(0);
			getChildrens.setRows(1);
			Childrens childrens = childrenManager.getChildrens(getChildrens);

			JibxMarshaller<Childrens> marshaller = new JibxMarshaller<Childrens>();

			Object obj = marshaller.toString(childrens);

			if (obj instanceof String) {
				String value = (String) obj;
				logger.info(value);
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
