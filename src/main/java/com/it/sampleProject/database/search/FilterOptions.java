package com.it.sampleProject.database.search;

public class FilterOptions {

	private Filter customersFilter;
    private Filter exchangesFilter;
	private Filter documentsFilter;
	private Filter eventsFilter;

    public Filter getCustomersFilter() {
        return customersFilter;
    }

    public void setCustomersFilter(Filter customersFilter) {
        this.customersFilter = customersFilter;
    }

    public Filter getExchangesFilter() {
		return exchangesFilter;
	}

	public void setExchangesFilter(Filter exchangesFilter) {
		this.exchangesFilter = exchangesFilter;
	}

	public Filter getDocumentsFilter() {
		return documentsFilter;
	}

	public void setDocumentsFilter(Filter documentsFilter) {
		this.documentsFilter = documentsFilter;
	}

	public Filter getEventsFilter() {
		return eventsFilter;
	}

	public void setEventsFilter(Filter eventsFilter) {
		this.eventsFilter = eventsFilter;
	}

}
