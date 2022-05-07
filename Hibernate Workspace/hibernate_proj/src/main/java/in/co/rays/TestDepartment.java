package in.co.rays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestDepartment {
	public static void main(String[] args) {
		testAdd();
	}

	private static void testAdd() {
		Department department = new Department();
		department.setDepartment("Operations");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(department);
		tx.commit();
		session.close();

		System.out.println("Inserted values into table");

	}
}
