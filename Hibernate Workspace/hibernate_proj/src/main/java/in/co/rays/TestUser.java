package in.co.rays;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TestUser {
	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testGet();
		// testList();
		// testWhere();
		// testSomeCol();
		// testCriteria();
		// testProjection();
		// testRestrictOr();
		// testOrderBy();
		// testCache();
		// testJoin();
		// testSecondLevelCache();
		testNamedQuery();
	}

	private static void testNamedQuery() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Query q = session.getNamedQuery("allUsers");
		List list = q.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User pojo = (User) it.next();
			System.out.print(pojo.getId());
			System.out.print("\t" + pojo.getFname());
			System.out.print("\t" + pojo.getLname());
			System.out.print("\t" + pojo.getUsername());
			System.out.println("\t" + pojo.getPassword());

		}
		session.close();

	}

	private static void testSecondLevelCache() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		User user = (User) session.get(User.class, 2);
		System.out.println(user.getId());
		System.out.println(user.getFname());
		System.out.println(user.getLname());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		/*
		 * session.close(); factory.close();
		 * 
		 * SessionFactory factory2 = new
		 * Configuration().configure().buildSessionFactory(); Session session2 =
		 * factory2.openSession();
		 */
		User user1 = (User) session.get(User.class, 2);
		System.out.println(user1.getId());
		System.out.println(user1.getFname());
		System.out.println(user1.getLname());
		System.out.println(user1.getUsername());
		System.out.println(user1.getPassword());

		session.close();

	}

	private static void testJoin() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Department d = new Department();
		List users = session.createCriteria(Department.class).setFetchMode("User", FetchMode.JOIN)
				.add(Restrictions.eq("id", 1)).list();
		Iterator it = users.iterator();
		while (it.hasNext()) {
			d = (Department) it.next();
			System.out.println(d.getId());
			System.out.println(d.getDepartment());
		}

		session.close();
	}

	private static void testCache() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		User user = (User) session.get(User.class, 2);
		System.out.println(user.getId());
		System.out.println(user.getFname());
		System.out.println(user.getLname());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		session.evict(user);

		User user1 = (User) session.get(User.class, 2);
		System.out.println(user1.getId());
		System.out.println(user1.getFname());
		System.out.println(user1.getLname());
		System.out.println(user1.getUsername());
		System.out.println(user1.getPassword());

		session.close();
	}

	private static void testOrderBy() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.addOrder(Order.asc("fname"));

		List list = criteria.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User names = (User) it.next();
			System.out.println(names.getFname());
		}
		session.close();
	}

	private static void testRestrictOr() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.or(Restrictions.like("lname", "%Sharma%"), Restrictions.eq("id", 6)));

		List list = criteria.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User user = (User) it.next();
			System.out.print(user.getId());
			System.out.print("\t" + user.getFname());
			System.out.print("\t" + user.getLname());
			System.out.print("\t" + user.getUsername());
			System.out.println("\t" + user.getPassword());
		}

	}

	private static void testProjection() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(User.class);
		// criteria.add(Restrictions.like("lname", "%Sharma%"));

		ProjectionList p = Projections.projectionList();
		p.add(Projections.property("fname"));
		p.add(Projections.property("lname"));
		// p.add(Projections.rowCount());
		// p.add(Projections.groupProperty("fname"));

		criteria.setProjection(p);

		List list = criteria.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			// int count = (int) it.next();

			Object[] columns = (Object[]) it.next();

			String fname = (String) columns[0];
			String lname = (String) columns[1];

			// System.out.println(count);
			System.out.println(fname + "\t" + lname);

		}
		session.close();
	}

	private static void testCriteria() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("id", 6));
		crit.add(Restrictions.like("lname", "%Sharma%"));

		List<User> list = crit.list();
		Iterator<User> it = list.iterator();
		while (it.hasNext()) {
			User user = it.next();
			System.out.print(user.getId());
			System.out.print("\t" + user.getFname());
			System.out.print("\t" + user.getLname());
			System.out.print("\t" + user.getUsername());
			System.out.println("\t" + user.getPassword());

		}
		session.close();
	}

	private static void testSomeCol() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Query q = session.createQuery("Select u.id, u.fname from User u");
		List list = q.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object[] columns = (Object[]) it.next();
			Integer id = (Integer) columns[0];
			String fname = (String) columns[1];
			System.out.println(id + "\t" + fname);
		}
		session.close();

	}

	private static void testWhere() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User where lname like '%sharma%'");
		List list = q.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User pojo = (User) it.next();
			System.out.print(pojo.getId());
			System.out.print("\t" + pojo.getFname());
			System.out.print("\t" + pojo.getLname());
			System.out.print("\t" + pojo.getUsername());
			System.out.println("\t" + pojo.getPassword());

		}
		session.close();
	}

	private static void testList() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User");
		List list = q.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User pojo = (User) it.next();
			System.out.print(pojo.getId());
			System.out.print("\t" + pojo.getFname());
			System.out.print("\t" + pojo.getLname());
			System.out.print("\t" + pojo.getUsername());
			System.out.println("\t" + pojo.getPassword());

		}
		session.close();
	}

	private static void testGet() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		User pojo = (User) session.get(User.class, 7);
		System.out.println(pojo.getFname());

		/*
		 * System.out.println(pojo.getFname()); System.out.println(pojo.getLname());
		 * System.out.println(pojo.getUsername());
		 * System.out.println(pojo.getPassword());
		 */
		session.close();
		pojo.setFname("abc");

		Session session1 = factory.openSession();
		User pojo1 = (User) session1.get(User.class, 7);

		Transaction tx = session1.beginTransaction();

		session1.merge(pojo);
		tx.commit();
		session1.close();

		System.out.println("Inserted values into table");

	}

	private static void testDelete() {
		User user = new User();
		user.setId(6);
		/*
		 * user.setFname("Udita"); user.setLname("Bhagat");
		 * user.setUsername("udita@1234"); user.setPassword("12345");
		 */

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.delete(user);
		tx.commit();
		session.close();

		System.out.println("Deleted values from table");

	}

	private static void testUpdate() throws Exception {
		User user = new User();
		user.setId(6);
		user.setFname("Ram");
		user.setLname("Sharma");
		user.setUsername("ram@1234");
		user.setPassword("12345");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.update(user);
		tx.commit();
		session.close();

		System.out.println("Updated values into table");

	}

	private static void testAdd() {
		User user = new User();
		user.setFname("Shyam");
		user.setLname("Sharma");
		user.setUsername("Shyam@1234");
		user.setPassword("12345");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(user);
		tx.commit();
		session.close();

		System.out.println("Inserted values into table");

	}
}
