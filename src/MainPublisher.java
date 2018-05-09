import event.CriticalErrorEvent;
import event.ErrorEvent;
import event.NovelBook;
import publisher.SimplePublisher;

public class MainPublisher {
	public static void main(String[] args) {
		SimplePublisher p = new SimplePublisher("ExamplePublisher");
		//new Thread(p::run).start();
		p.publish(new ErrorEvent(),ErrorEvent.class);
		p.publish(new CriticalErrorEvent(),CriticalErrorEvent.class);
		p.publish(new NovelBook(), NovelBook.class);
		p.stop();
	}
}
