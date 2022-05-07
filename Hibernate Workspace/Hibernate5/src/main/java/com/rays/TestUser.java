package com.rays;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class TestUser {

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testList();
		// testGet();
		// testCriteriaList();
		testCriteriaColumns();

	}

	private static void testCriteriaColumns() throws Exception {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<User> root = query.from(User.class);
		query.multiselect(root.get("id"), root.get("firstName"));
		Query q = session.createQuery(query);
		List<Object[]> user = q.getResultList();
		Iterator<Object[]> list = user.iterator();
		while (list.hasNext()) {
			Object[] u= list.next();
			Integer id = (Integer) u[0];
			String fname = (String) u[1];
			//String lname = (String) u[2];
			System.out.println(id + "\t" + fname );
		}
		session.close();
	}

	private static void testCriteriaList() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		Query q = session.createQuery(query);
		List<User> user = q.getResultList();
		Iterator<User> list = user.iterator();
		while (list.hasNext()) {
			User pojo = (User) list.next();
			System.out.print(pojo.getId());
			System.out.print("\t" + pojo.getFirstName());
			System.out.println("\t" + pojo.getLastName());

		}
		session.close();
	}

	private static void testGet() throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();

		User pojo = (User) session.get(User.class, 1);

		System.out.println(pojo.getId());
		System.out.println(pojo.getFirstName());
		System.out.println(pojo.getLastName());

		session.close();

		System.out.println("Got values from the table");

	}

	private static void testList() throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User");
		List list = q.getResultList();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User pojo = (User) it.next();
			System.out.print(pojo.getId());
			System.out.print("\t" + pojo.getFirstName());
			System.out.println("\t" + pojo.getLastName());

		}
		session.close();

	}

	private static void testDelete() throws Exception {

		User user = new User();
		user.setId(3);
		/*
		 * user.setFname("Udita"); user.setLname("Bhagat");
		 * user.setUsername("udita@1234"); user.setPassword("12345");
		 */

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.delete(user);
		tx.commit();
		session.close();

		System.out.println("Deleted values from table");

	}

	private static void testAdd() throws Exception {

		User user = new User();
		user.setId(2);
		user.setFirstName("abc");
		user.setLastName("xyz");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();

		session.save(user);
		tx.commit();
		session.close();
		HibernateUtil.shutdown();

	}

	private static void testUpdate() throws Exception {
		User user = new User();
		user.setId(2);
		user.setFirstName("Ram");
		user.setLastName("Sharma");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.update(user);
		tx.commit();
		session.close();

		System.out.println("Updated values into table");

	}

}
