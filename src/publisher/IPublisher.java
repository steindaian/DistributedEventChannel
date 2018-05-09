package publisher;

import event.Event;

public interface IPublisher extends Runnable {
	void run();
	void publish(Event e, Class<? extends Event> type);
	//void proxyRun();
	void stop();
}
