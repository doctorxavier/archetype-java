package archetype.business.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archetype.business.IFatherHandler;
import archetype.dao.utils.DataModelFilter;
import archetype.dao.utils.FilterField;
import archetype.model.Father;

public class FatherHandler extends AbstractHandler<Father> implements IFatherHandler {

	protected static Logger	logger	= LoggerFactory.getLogger(FatherHandler.class);

	public List<Father> getFathers(int start, int rows) throws SecurityException, NoSuchFieldException {
		
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
