package com.project.SpringHibernateCRUD.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.SpringHibernateCRUD.entity.User;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class UserRepository {
	
	@Autowired
	SessionFactory sf; 
	
	public String insertData(User user) {
		Session ss = null;
		Transaction tr = null;
		try {
			ss = sf.openSession();
			tr = ss.beginTransaction();
			
			ss.persist(user);
			tr.commit();
			return "User Registered Successfully!!";
		}catch(HibernateException e) {
			if(tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
			return "error occured, cannot register use";
		}
		finally {
			if(ss != null) {
				ss.close();
			}
		}
	}
	
	public User getUser(Long id) {
		Session ss = null;
		Transaction tr = null;
		User user = null;
		
		try {
			ss = sf.openSession();
			tr = ss.beginTransaction();
			user = ss.get(User.class, id);
			tr.commit();
			if(user == null) {
				return null;
			}
			return user;
		}catch(HibernateException e) {
			if(tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
			return null;
		}finally {
			if(ss != null) {
				ss.close();
			}
		}
	}
	
	public List<User> getAllUsers() {
	    Session ss = null;
	    Transaction tr = null;
	    try {
	        ss = sf.openSession();
	        tr = ss.beginTransaction();
	        
	        HibernateCriteriaBuilder criteriaBuilder = ss.getCriteriaBuilder();
	        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
	        Root<User> root = criteriaQuery.from(User.class);
	        criteriaQuery.select(root);
	        List<User> users = ss.createQuery(criteriaQuery).getResultList();

	        tr.commit();
	        return users;
	    } catch (HibernateException e) {
	        if (tr != null) {
	            tr.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (ss != null) {
	            ss.close();
	        }
	    }
	}
	
	public String updateUser(User user, Long id) {
		Session ss = null;
		Transaction tr = null;
		
		try {
			ss = sf.openSession();
			tr = ss.beginTransaction();
			User u = ss.get(User.class, id);
			if(u == null) {
			return "User not found";
			}
			u.setUserName(user.getUserName());
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			ss.merge(u);
			tr.commit();
			return "User Record Updated!!";
		}catch(HibernateException e) {
			if(tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
			return "error occured can not update user";
		}finally {
			if(ss != null) {
				ss.close();
			}
		}
	}
	
	public String deleteData(Long id) {
		Session ss = null;
		Transaction tr = null;
		
		try {
			ss = sf.openSession();
			tr = ss.beginTransaction();
			User user = ss.get(User.class, id);
			if(user == null) {
				return "User not found";
			}
			ss.remove(user);
			tr.commit();
			return "User Record Deleted";
		}catch(HibernateException e) {
			if(tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
			return "error occured cannot delete user record";
		}finally {
			if(ss != null) {
				ss.close();
			}
		}
	}
}
