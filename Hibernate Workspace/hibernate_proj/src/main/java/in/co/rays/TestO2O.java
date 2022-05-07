package in.co.rays;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class TestO2O {
	public static void main(String[] args) {
		/*
		 * Employee emp = new Employee(); emp.setFirstName("Trapti");
		 * emp.setLastName("Bhagat");
		 * 
		 * Address add1 = new Address(); add1.setStreet("Baker's Street");
		 * add1.setCity("Melbourne"); add1.setState("Victoria");
		 */
		/*
		 * Address add2 = new Address(); add2.setStreet("Parkinson's Lounge");
		 * add2.setCity("Melbourne"); add2.setState("Victoria");
		 */

		// emp.setEmpAddr(add1);

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		AuctionItems item = (AuctionItems) session.get(AuctionItems.class, 1);

		System.out.println(item.getId());
		System.out.println(item.getDescription());
		System.out.println(item.getBid());

		System.out.println(item);
		/*
		 * Transaction tx = session.beginTransaction();
		 * 
		 * session.save(item); tx.commit();
		 */
		session.close();

		System.out.println("Lazy fetching of auctionitems");

	}
}
