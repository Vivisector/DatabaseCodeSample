package com.it.sampleProject.database.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.it.sampleProject.database.search.CriteriaEntry;
import com.it.sampleProject.database.search.Filter;
import com.it.sampleProject.database.search.FilterOptions;
import com.it.sampleProject.database.search.enums.ConditionType;
import com.it.sampleProject.database.search.enums.SortDirection;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.it.sampleProject.database.search.SortOptionsEntry;

public class DAOUtil {

    public static void addSortOptionsToDetachedCriteria(List<SortOptionsEntry> sortOptions, DetachedCriteria dc) {
        if (sortOptions != null) {
            for (SortOptionsEntry so : sortOptions) {
                if (so.getSortDirection() == SortDirection.ASC) {
                    dc.addOrder(Order.asc(so.getFieldName()));
                } else {
                    dc.addOrder(Order.desc(so.getFieldName()));
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static void addFilterOptionsToDetachedCriteria(Filter filter, DetachedCriteria criteria, String fieldNameTimestamp) {
        if (filter != null) {
            if (filter.getFilterEntries() != null && !filter.getFilterEntries().isEmpty()) {
                for (Map.Entry<String, CriteriaEntry> entry : filter.getFilterEntries().entrySet()) {
                    if (entry.getValue().getConditionType() == ConditionType.EQUALS) {

                        criteria.add(Restrictions.eq(entry.getKey(), entry.getValue().getValue()));
                    }
                    if (entry.getValue().getConditionType() == ConditionType.LIKE) {
                        criteria.add(Restrictions.like(entry.getKey(), (String) entry.getValue().getValue(), MatchMode.ANYWHERE));
                    }
                    if (entry.getValue().getConditionType() == ConditionType.IN) {
                        criteria.add(Restrictions.in(entry.getKey(), (Collection) entry.getValue().getValue()));
                    }
                }
            }
            if (filter.getFilterOrEntries() != null && !filter.getFilterOrEntries().isEmpty()) {
                Disjunction disj = Restrictions.disjunction();
                for (Map.Entry<String, CriteriaEntry> entry : filter.getFilterOrEntries().entrySet()) {
                    if (entry.getValue().getConditionType() == ConditionType.EQUALS) {
                        disj.add(Restrictions.eq(entry.getKey(), entry.getValue().getValue()));
                    }
                    if (entry.getValue().getConditionType() == ConditionType.LIKE) {
                        disj.add(Restrictions.like(entry.getKey(), (String) entry.getValue().getValue(), MatchMode.ANYWHERE));
                    }
                    if (entry.getValue().getConditionType() == ConditionType.IN) {
                        disj.add(Restrictions.in(entry.getKey(), (Collection) entry.getValue().getValue()));
                    }
                }
                criteria.add(disj);
            }
            if (filter.getUseThisDates()) {
                criteria.add(Restrictions.between(fieldNameTimestamp, filter.getFrom(), filter.getTo()));
            }
        }
    }

    public static boolean filterOptionsHaveDocumentFilterEntries(FilterOptions filterOptions) {
        boolean filterOptionsHaveFilter = false;
        boolean filterOptionsFilterNotNull = filterOptions.getDocumentsFilter() != null;
        if (filterOptionsFilterNotNull) {
            boolean filterHasFilterEntries = filterOptions.getDocumentsFilter().getFilterEntries() != null && filterOptions.getDocumentsFilter().getFilterEntries().size() > 0;
            boolean filterHasFilterOrEntries = filterOptions.getDocumentsFilter().getFilterOrEntries() != null && filterOptions.getDocumentsFilter().getFilterOrEntries().size() > 0;
            if (filterHasFilterEntries | filterHasFilterOrEntries) {
                filterOptionsHaveFilter = true;
            }
        }
        return filterOptionsHaveFilter;
    }

    public static boolean filterOptionsHaveExchangeFilterEntries(FilterOptions filterOptions) {
        boolean filterOptionsHaveFilter = false;
        boolean filterOptionsFilterNotNull = filterOptions.getExchangesFilter() != null;
        if (filterOptionsFilterNotNull) {
            boolean filterHasFilterEntries = filterOptions.getExchangesFilter().getFilterEntries() != null && filterOptions.getExchangesFilter().getFilterEntries().size() > 0;
            boolean filterHasFilterOrEntries = filterOptions.getExchangesFilter().getFilterOrEntries() != null && filterOptions.getExchangesFilter().getFilterOrEntries().size() > 0;
            if (filterHasFilterEntries | filterHasFilterOrEntries) {
                filterOptionsHaveFilter = true;
            }
        }
        return filterOptionsHaveFilter;
    }

    public static boolean filterOptionsHaveEventFilterEntries(FilterOptions filterOptions) {
        boolean filterOptionsHaveFilter = false;
        boolean filterOptionsFilterNotNull = filterOptions.getEventsFilter() != null;
        if (filterOptionsFilterNotNull) {
            boolean filterHasFilterEntries = filterOptions.getEventsFilter().getFilterEntries() != null && filterOptions.getEventsFilter().getFilterEntries().size() > 0;
            boolean filterHasFilterOrEntries = filterOptions.getEventsFilter().getFilterOrEntries() != null && filterOptions.getEventsFilter().getFilterOrEntries().size() > 0;
            if (filterHasFilterEntries | filterHasFilterOrEntries) {
                filterOptionsHaveFilter = true;
            }
        }
        return filterOptionsHaveFilter;
    }

    public static boolean filterOptionsHaveCustomerFilterEntries(FilterOptions filterOptions) {
        boolean filterOptionsHaveFilter = false;
        boolean filterOptionsFilterNotNull = filterOptions.getCustomersFilter() != null;
        if (filterOptionsFilterNotNull) {
            boolean filterHasFilterEntries = filterOptions.getCustomersFilter().getFilterEntries() != null && filterOptions.getCustomersFilter().getFilterEntries().size() > 0;
            boolean filterHasFilterOrEntries = filterOptions.getCustomersFilter().getFilterOrEntries() != null && filterOptions.getCustomersFilter().getFilterOrEntries().size() > 0;
            if (filterHasFilterEntries | filterHasFilterOrEntries) {
                filterOptionsHaveFilter = true;
            }
        }
        return filterOptionsHaveFilter;
    }
}
