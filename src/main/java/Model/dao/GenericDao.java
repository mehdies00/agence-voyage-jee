package Model.dao;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.Session;

import org.hibernate.Transaction;

import Model.HibernateUtils;



public class GenericDao<ENTITY, PRIMARY_KEY> {
	private Class<ENTITY> clazz;
	
	public GenericDao(Class<ENTITY> clazz) {
		this.clazz = clazz;
	}
	
	public <R> R runInTransaction(Function<Session, R> function) {
		Transaction t = null;
		R result;
		try(var s = HibernateUtils.getSessionFactory().openSession()){
			t = s.beginTransaction();
			result = function.apply(s);
			t.commit();
			return result;
		}catch(Exception e) {
			try {
				if(t!=null) t.rollback();
			}catch(Exception e1) {
				e.addSuppressed(e1);
			}
			throw e;
		}
		
	}

	public Collection<ENTITY> findAll(){
		try(var s = HibernateUtils.getSessionFactory().openSession()){
			return s.createQuery("from "+clazz.getSimpleName(), clazz)
					.getResultList();
		}
	}
	public Optional<ENTITY> findById(PRIMARY_KEY id){
		try(var s = HibernateUtils.getSessionFactory().openSession()){
			return Optional.ofNullable(s.get(clazz,id));
		}
	}
	public Optional<ENTITY> findByStr(String value,String tabName){
		try(var s = HibernateUtils.getSessionFactory().openSession()){
			String hql = String.format("FROM %s WHERE lower(%s) = lower(:val)", clazz.getSimpleName(),tabName);
			return s.createQuery(hql,clazz)
					.setParameter("val", value)
					.uniqueResultOptional();
		}
	}
	public ENTITY save(ENTITY u) {
		return runInTransaction(s->{
			s.persist(u);
			return u;
		});
	}
	
	public ENTITY update(ENTITY u) {
		return runInTransaction(s-> s.merge(u));
	}
	 public void deleteById(PRIMARY_KEY id){ 
		 ENTITY entity = (ENTITY) findById(id)
		.orElseThrow(()-> new IllegalArgumentException("Entity not found"));
		 delete(entity);
}
	 public void delete(ENTITY entity) {
		 runInTransaction(s->{
		s.remove(entity);
		return null;
		 });
	 }
}