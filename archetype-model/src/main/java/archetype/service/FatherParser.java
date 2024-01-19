package archetype.service;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import archetype.business.IPrintData;
import archetype.business.impl.PrintData;
import archetype.dao.IFatherDao;
import archetype.model.Father;


public class FatherParser {

	private static Logger	logger	= Logger.getLogger(FatherParser.class);

	private IFatherDao		fatherDao;

	public void setFatherDao(IFatherDao fatherDao) {
		this.fatherDao = fatherDao;
	}

	public Father parseFather(Father father) {
		Father fatherCopy = null;
		try {
			fatherCopy = fatherDao.getEntityByPrimaryKey(father);
			IPrintData printData = new PrintData();
			printData.marshall(fatherCopy, "father.xml");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return fatherCopy;
	}
	
}
