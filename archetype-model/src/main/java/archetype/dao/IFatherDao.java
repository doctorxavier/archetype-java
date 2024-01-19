package archetype.dao;

import archetype.model.Father;


public interface IFatherDao extends IDao<Father> {
	
	void loadAllFathersInCache();
	
	Father getCacheFather(Integer id);

}
