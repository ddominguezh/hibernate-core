package com.ddominguezh.hibernate.core.shared.infrastructure.hibernate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import com.ddominguezh.hibernate.core.shared.domain.Identifier;
import com.ddominguezh.hibernate.core.shared.domain.IntValueObject;
import com.ddominguezh.hibernate.core.shared.domain.criteria.Criteria;

public abstract class HibernateRepository<T> {
    protected final SessionFactory sessionFactory;
    protected final Class<T> aggregateClass;
    @SuppressWarnings("rawtypes")
	protected final HibernateCriteriaConverter criteriaConverter;

    public HibernateRepository(SessionFactory sessionFactory, Class<T> aggregateClass) {
        this.sessionFactory    = sessionFactory;
        this.aggregateClass    = aggregateClass;
        this.criteriaConverter = new HibernateCriteriaConverter<T>(sessionFactory.getCriteriaBuilder());
    }

    protected void persist(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

    protected Optional<T> byId(Identifier id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().byId(aggregateClass).load(id));
    }
    
    protected Optional<T> byId(IntValueObject id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().byId(aggregateClass).load(id));
    }

    @SuppressWarnings("unchecked")
	protected List<T> byCriteria(Criteria criteria) {
        CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(criteria, aggregateClass);

        return sessionFactory.getCurrentSession().createQuery(hibernateCriteria).getResultList();
    }

    protected List<T> all() {
        CriteriaQuery<T> criteria = sessionFactory.getCriteriaBuilder().createQuery(aggregateClass);

        criteria.from(aggregateClass);

        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
    
    protected List<T> byNativeQuery(String sqlString, Map<String, Object> parameters){
    	NativeQuery<T> query = sessionFactory.getCurrentSession().createNativeQuery(sqlString, aggregateClass);
    	parameters.keySet().stream().forEach(key -> {
    		query.setParameter(key, parameters.get(key));
    	});
    	return query.getResultList();
    }
    
    protected void update(T entity) {
    	sessionFactory.getCurrentSession().clear();
    	sessionFactory.getCurrentSession().update(entity);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }
    
    protected void delete(T entity) {
    	sessionFactory.getCurrentSession().delete(entity);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }
}
