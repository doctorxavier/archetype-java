package archetype.ws.manager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archetype.business.IChildrenHandler;
import archetype.ws.model.request.GetChildrens;
import archetype.ws.model.response.Childrens;

public class ChildrenManager {

	private final Logger		logger	= LoggerFactory.getLogger(ChildrenManager.class);

	private IChildrenHandler	childrenHandler;

	public IChildrenHandler getChildrenHandler() {
		return childrenHandler;
	}

	public void setChildrenHandler(IChildrenHandler childrenHandler) {
		this.childrenHandler = childrenHandler;
	}

	public Childrens getChildrens(GetChildrens getChildrens) {
		Childrens childrens = new Childrens();
		try {
			childrens.setChildrens(childrenHandler.getChildrens(getChildrens.getStart(), getChildrens.getRows()));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
		}
		return childrens;
	}

}
