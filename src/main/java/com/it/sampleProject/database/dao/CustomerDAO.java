package com.it.sampleProject.database.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.it.sampleProject.database.entities.CustomerEntity;

@Repository
public class CustomerDAO extends BaseDAO {

	private static final String FIELD_NAME_CUSTOMER_NAME = "name";
	private static final int CUSTOMER_IS_DELETED = 1;
	private static final String GET_NON_DELETED_CUSTOMER = "from CustomerEntity c where c.deleted = 0";

	public CustomerDAO() {
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CustomerEntity> getCustomerEntities() {
		return (ArrayList<CustomerEntity>) this.getCurrentSession().createQuery(GET_NON_DELETED_CUSTOMER).list();
		/*(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createQuery(
								"from CustomerEntity c where c.deleted = 0").list();
					}
				});*/
	}
	public CustomerEntity findCustomerEntityById(BigInteger id) {
		CustomerEntity customerEntity = (CustomerEntity) getCurrentSession().get(CustomerEntity.class, id);
		return customerEntity;
	}

	public void deleteCustomerEntity(CustomerEntity customerEntity) {
		customerEntity.setDeleted(CUSTOMER_IS_DELETED);
		String name = Long.toString(Calendar.getInstance().getTimeInMillis()) + "_" + customerEntity.getName();
		if (name.length() > 50) {
			name = name.substring(0, 49);
		}
		customerEntity.setName(name);
		getCurrentSession().saveOrUpdate(customerEntity);
	}

	public CustomerEntity addCustomerEntity(String name) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(name);
		getCurrentSession().save(customerEntity);
		return customerEntity;
	}

	@SuppressWarnings("unchecked")
	public CustomerEntity findCustomerEntityByName(String name) {
		DetachedCriteria customerCriteria = DetachedCriteria.forClass(CustomerEntity.class);
		customerCriteria.add(Restrictions.eq(FIELD_NAME_CUSTOMER_NAME, name));
		//customerCriteria.getExecutableCriteria(HibernateUtil.getSession());
		List<CustomerEntity> customerEntitiesList = findByCriteria(customerCriteria);
		CustomerEntity customerEntity = null;
		if (customerEntitiesList != null && !customerEntitiesList.isEmpty()) {
			customerEntity = customerEntitiesList.get(0);
		}
		return customerEntity;
	}
}
