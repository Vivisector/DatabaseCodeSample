package com.it.sampleProject.database.search;

import com.it.sampleProject.database.search.enums.ItemType;
import com.it.sampleProject.database.search.enums.SortDirection;

public class SortOptionsEntry {

	private ItemType itemType;
	private String fieldName;
	private SortDirection sortDirection;

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
}
