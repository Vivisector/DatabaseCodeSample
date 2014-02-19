package com.it.sampleProject.database.search;

import com.it.sampleProject.database.search.enums.ConditionType;

public class CriteriaEntry<T> {

	private T value;
	private ConditionType conditionType;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ConditionType getConditionType() {
		return conditionType;
	}

	public void setConditionType(ConditionType conditionType) {
		this.conditionType = conditionType;
	}
}
