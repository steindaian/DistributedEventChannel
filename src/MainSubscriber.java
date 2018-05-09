import java.net.UnknownHostException;

import event.CriticalErrorEvent;
import event.ErrorEvent;
import subscriber.SimpleSubscriber;

public class MainSubscriber {
	public static void main(String[] args) throws UnknownHostException {

			SimpleSubscriber s = new SimpleSubscriber(8080);
			s.doSubscribe(ErrorEvent.class);
			s.doSubscribe(CriticalErrorEvent.class);
			s.run();
			
		
	}
}
