package in.co.rays;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class TestPayment {
	public static void main(String[] args) {
		Cheque cheque = new Cheque();
		cheque.setId(1);
		cheque.setAmount(10000);
		cheque.setChqNumber(12345);
		cheque.setBankName("State Bank of India");

		CreditCard card = new CreditCard();
		card.setId(2);
		card.setAmount(20000);
		card.setCcType("Visa");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(card);
		session.save(cheque);

		tx.commit();
		session.close();
		System.out.println("eena meena deeka :*");
	}
}
