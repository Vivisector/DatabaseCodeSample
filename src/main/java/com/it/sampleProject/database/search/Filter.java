package com.it.sampleProject.database.search;

import java.util.Date;
import java.util.HashMap;

@SuppressWarnings("rawtypes")
public class Filter {

    private Date from;
    private Date to;
    private Boolean useThisDates = false;
    private HashMap<String, CriteriaEntry> filterEntries;
    private HashMap<String, CriteriaEntry> filterOrEntries;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public HashMap<String, CriteriaEntry> getFilterEntries() {
        return filterEntries;
    }

    public void setFilterEntries(HashMap<String, CriteriaEntry> filterEntries) {
        this.filterEntries = filterEntries;
    }

    public HashMap<String, CriteriaEntry> getFilterOrEntries() {
        return filterOrEntries;
    }

    public void setFilterOrEntries(HashMap<String, CriteriaEntry> filterOrEntries) {
        this.filterOrEntries = filterOrEntries;
    }

    public Boolean getUseThisDates() {
        return useThisDates;
    }

    public void setUseThisDates(Boolean useThisDates) {
        this.useThisDates = useThisDates;
    }
}
