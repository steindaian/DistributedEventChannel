import eventchannel.EventChannel;

public class MainEventChannel {
	public static void main(String[] args) {
		EventChannel e = EventChannel.instance();
		e.run();
	}
}
