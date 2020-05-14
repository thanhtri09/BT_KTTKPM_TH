package demoMS;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MySyncMessageConsumer {
	public static void main(String[] args) {
		ConnectionFactory factory = null;
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;
		TextMessage message = null;
		boolean useStransaction = false;
		try {
//			 thiết lập môi trường cho JJNDI
			Properties settings = new Properties();
			settings.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
			Context context = new InitialContext();
			factory = (ConnectionFactory) context.lookup("connectionFactoryName");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(useStransaction, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("MyQueue");
			consumer = session.createConsumer(destination);
			message = (TextMessage) consumer.receive();
			System.out.println("Receive Message:" + message.getText());

		} catch (JMSException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				consumer.close();
				session.close();
				connection.close();
			} catch (JMSException e2) {
				// TODO: handle exception
				e2.printStackTrace();

			}
		}

	}
}
