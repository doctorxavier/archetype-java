package archetype.ws.manager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archetype.business.IFatherHandler;
import archetype.ws.model.request.GetFathers;
import archetype.ws.model.response.Fathers;

public class FatherManager {

	private final Logger	logger	= LoggerFactory.getLogger(FatherManager.class);

	private IFatherHandler	fatherHandler;

	public IFatherHandler getFatherHandler() {
		return fatherHandler;
	}

	public void setFatherHandler(IFatherHandler fatherHandler) {
		this.fatherHandler = fatherHandler;
	}
	
	public Fathers getFathers(GetFathers getFathers) {
		Fathers fathers = new Fathers();
		try {
			fathers.setFathers(fatherHandler.getFathers(getFathers.getStart(), getFathers.getRows()));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
		}
		return fathers;
	}
	
}
