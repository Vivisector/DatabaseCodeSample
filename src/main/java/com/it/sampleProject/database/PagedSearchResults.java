package com.it.sampleProject.database;

import java.util.List;

public class PagedSearchResults<T> {

	private int totalRecords;
	private int firstResult;
	private int maxResults;
	private List<T> searchResults;

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public List<T> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<T> searchResults) {
		this.searchResults = searchResults;
	}
}
