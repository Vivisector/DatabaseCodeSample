/**
 * **************************************************************
 *
 * Copyright IntellectTechnologies LLC. All rights reserved.
 *
 * This software and its associated documentation contain proprietary,
 * confidential and trade secret information of IntellectTechnlogies LLC and
 * except as provided by written agreement with IntellectTechnologies LLC.
 *
 * a) no part may be disclosed, distributed, reproduced, transmitted,
 * transcribed, stored in a retrieval system, adapted or translated in any form
 * or by any means electronic, mechanical, magnetic, optical, chemical, manual
 * or otherwise, and b) the recipient is not entitled to discover through
 * reverse engineering or reverse compiling or other such techniques or
 * processes the trade secrets contained in the software code or to use any such
 * trade secrets contained therein or in the documentation.
 *
 **************************************************************
 */
package com.it.sampleProject.database.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.it.sampleProject.database.PagedSearchResults;

/**
 * 
 * @author Igor Ivaniuk
 */
public abstract class BaseDAO {

	public static final int DEFAULT_FIRST_RESULT = 0;
	public static final int DEFAULT_MAX_RESULTS = 50;

	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public BaseDAO() {
	}
	@SuppressWarnings("rawtypes")
	public List findByCriteria(DetachedCriteria detachedCriteria){
		return detachedCriteria.getExecutableCriteria(getCurrentSession()).list();
	}
	
	@SuppressWarnings("unchecked")
	public <T> PagedSearchResults<T> getPagedSearchResults(
			DetachedCriteria detachedCriteria,
			DetachedCriteria detachedCriteriaWithoutSort, Integer firstResult,
			Integer maxResults, SessionFactory sessionFactory) {
		PagedSearchResults<T> results = new PagedSearchResults<T>();
		results.setFirstResult(firstResult);
		results.setMaxResults(maxResults);

		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();

		// Get total rows count using criteria without sort options
		Criteria criteriaWithoutSort = detachedCriteriaWithoutSort
				.getExecutableCriteria(sessionFactory.getCurrentSession());
		criteriaWithoutSort.setProjection(Projections.rowCount());
		results.setTotalRecords(((Long) (criteriaWithoutSort.uniqueResult()))
				.intValue());
		// return criteria to original state
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(sessionFactory.getCurrentSession());
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);

		// perform select
		criteria.setFirstResult(firstResult).setMaxResults(maxResults);
		results.setSearchResults(Collections.unmodifiableList(criteria.list()));

		return results;
	}
}
