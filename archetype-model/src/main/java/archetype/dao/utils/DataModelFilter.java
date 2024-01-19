package archetype.dao.utils;

import java.io.Serializable;
import java.util.HashMap;

public class DataModelFilter implements Serializable {

	private static final long				serialVersionUID	= -4598352442169840025L;

	private HashMap<String, FilterField>	filters				= new HashMap<String, FilterField>();

	public HashMap<String, FilterField> getFilters() {
		return filters;
	}

	public void setFilters(HashMap<String, FilterField> filters) {
		this.filters = filters;
	}

}
