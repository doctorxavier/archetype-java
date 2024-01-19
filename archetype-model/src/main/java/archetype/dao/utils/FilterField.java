package archetype.dao.utils;

import java.io.Serializable;

import archetype.dao.utils.filter.Ordering;


public class FilterField implements Serializable {

	private static final long	serialVersionUID	= 8912238635094802540L;
	
	private String propertyName;

	private Object filterValue;

	private Ordering order = Ordering.UNSORTED;

	private boolean groupBy;
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public Object getFilterValue() {
		return filterValue;
	}
	
	public void setFilterValue(Object filterValue) {
		this.filterValue = filterValue;
	}

	public Ordering getOrder() {
		return order;
	}

	public void setOrder(Ordering order) {
		this.order = order;
	}
	
	public boolean isGroupBy() {
		return groupBy;
	}
	
	public void setGroupBy(boolean groupBy) {
		this.groupBy = groupBy;
	}

}
