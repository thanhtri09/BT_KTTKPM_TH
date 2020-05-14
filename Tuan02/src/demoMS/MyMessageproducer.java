package demoMS;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MyMessageproducer {
	public static void main(String[] args) {
		ConnectionFactory factory = null;
		Connection connect = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
		Message message = null;
		boolean useTransesstion = false;

		try {
			Context context = new InitialContext();
			factory = (ConnectionFactory) context.lookup("connectionFactoryName");
			connect = factory.createConnection();
			session = connect.createSession(useTransesstion, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("MyQueue");
			producer = session.createProducer(destination);
			message = session.createTextMessage("Hello!!!");
			producer.send(message);
		} catch (JMSException e) {
			// TODO: handle exception
			e.printStackTrace();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				producer.close();
				session.close();
				connect.close();
			} catch (JMSException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}
}
