package archetype.ws.endpoint;

import archetype.ws.manager.ChildrenManager;
import archetype.ws.manager.FatherManager;
import archetype.ws.model.request.GetChildrens;
import archetype.ws.model.request.GetFathers;
import archetype.ws.model.response.Childrens;
import archetype.ws.model.response.Fathers;

public class WSEndPoint {

	private FatherManager fatherManager;
	private ChildrenManager childrenManager;
	
	public void setFatherManager(FatherManager fatherManager) {
		this.fatherManager = fatherManager;
	}
	
	public void setChildrenManager(ChildrenManager childrenManager) {
		this.childrenManager = childrenManager;
	}
	
	public Fathers getFathers(GetFathers getFathers) {
		return this.fatherManager.getFathers(getFathers);
	}
	
	public Childrens getChildrens(GetChildrens getChildrens) {
		return this.childrenManager.getChildrens(getChildrens);
	}

}
