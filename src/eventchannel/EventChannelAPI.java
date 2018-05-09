package eventchannel;

import MessageMarshaller.EventMessage;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import MessageMarshaller.SubscribeMessage;
import event.Event;
import sendreceiveapi.bytesendreceive.ByteSender;
import sendreceiveapi.commons.Address;
import sendreceiveapi.commons.Entry;
import subscriber.ISubscriber;

public class EventChannelAPI {
	public static Entry SUBSCRIBER_ENTRY = EventChannel.EVENT_CHANNEL_ENTRY_SUBSCRIBER;
	public static Entry PUBLISHER_ENTRY = EventChannel.EVENT_CHANNEL_ENTRY_PUBLISHER;
	
	public static void publish(Class<? extends Event> type,Event event) {
		
		ByteSender b = new ByteSender("Subscriber");
		Message msg = new EventMessage(event);
		b.deliver(EventChannelAPI.PUBLISHER_ENTRY, Marshaller.marshallMessage(msg));
	}
	public static void subscribe(String eventType,Address subscriber) {
		ByteSender b = new ByteSender("Subscriber");
		Message req = new SubscribeMessage(eventType,subscriber);
		b.deliver(EventChannelAPI.SUBSCRIBER_ENTRY, Marshaller.marshallMessage(req));
	}
	
}
