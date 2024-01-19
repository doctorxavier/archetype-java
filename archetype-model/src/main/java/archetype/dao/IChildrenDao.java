package archetype.dao;

import archetype.model.Children;


public interface IChildrenDao extends IDao<Children> {
	
	void loadAllChildrensInCache();
	
	Children getCacheFather(Integer id);
	
}
