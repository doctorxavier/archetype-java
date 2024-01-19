package archetype.business.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archetype.business.IChildrenHandler;
import archetype.dao.utils.DataModelFilter;
import archetype.dao.utils.FilterField;
import archetype.model.Children;

public class ChildrenHandler extends AbstractHandler<Children> implements IChildrenHandler {

	protected static Logger	logger	= LoggerFactory.getLogger(ChildrenHandler.class);

	public List<Children> getChildrens(int start, int rows) throws SecurityException, NoSuchFieldException {

		DataModelFilter dataFilters = new DataModelFilter();
		HashMap<String, FilterField> filters = new HashMap<String, FilterField>(0);
		FilterField filterField = new FilterField();

		filterField.setOrder(archetype.dao.utils.filter.Ordering.ASCENDING);
		filterField.setPropertyName("id");
		filters.put("id", filterField);
		dataFilters.setFilters(filters);

		return dao.getFilteredItems(dataFilters, start, rows);
	}

}
