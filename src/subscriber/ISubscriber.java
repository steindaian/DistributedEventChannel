package subscriber;

import event.Event;

public interface ISubscriber {

	void run();
	void stop();
	void inform(Event e);
	void proxyRun();
}
