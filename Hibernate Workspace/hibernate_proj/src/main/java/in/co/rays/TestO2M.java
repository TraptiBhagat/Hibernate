package in.co.rays;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class TestO2M {
	public static void main(String[] args) {
		/*
		 * AuctionItems items = new AuctionItems(); items.setDescription("iphone");
		 * 
		 * Bid bid1 = new Bid(); bid1.setAmount(500);
		 * 
		 * Bid bid2 = new Bid(); bid2.setAmount(600);
		 * 
		 * Bid bid3 = new Bid(); bid3.setAmount(700);
		 * 
		 * Bid bid4 = new Bid(); bid4.setAmount(800);
		 * 
		 * Set<Bid> set = new HashSet<Bid>(); //TreeSet<Bid> set = new TreeSet<Bid>();
		 * set.add(bid1); set.add(bid2); set.add(bid3); set.add(bid4);
		 * 
		 * items.setBid(set);
		 * 
		 * SessionFactory factory = new
		 * Configuration().configure().buildSessionFactory(); Session session =
		 * factory.openSession(); Transaction tx = session.beginTransaction();
		 * 
		 * session.save(items); tx.commit(); session.close();
		 * 
		 * System.out.println("ONE TO MANY DONE");
		 */
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		AuctionItems item = (AuctionItems) session.get(AuctionItems.class, 1);

		System.out.println(item.getId());
		System.out.println(item.getDescription());
		// System.out.println(item.getBid());

		session.close();

		System.out.println("Lazy fetching of auctionitems");

	}
}
