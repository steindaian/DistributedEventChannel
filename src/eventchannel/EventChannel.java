package eventchannel;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import MessageMarshaller.EventMessage;
import MessageMarshaller.Marshaller;
import MessageMarshaller.SubscribeMessage;
import publisher.IPublisher;
import sendreceiveapi.bytesendreceive.ByteReceiver;
import sendreceiveapi.bytesendreceive.ByteSender;
import sendreceiveapi.commons.Address;
import sendreceiveapi.commons.Entry;
import subscriber.ISubscriber;
import subscriber.SimpleSubscriber;
import event.*;

public class EventChannel implements IEventPublisherChannel,IEventSubscriberChannel{
	private static EventChannel e;
	private boolean en;
	public static Entry EVENT_CHANNEL_ENTRY_SUBSCRIBER;
	public static Entry EVENT_CHANNEL_ENTRY_PUBLISHER;
	
	private HashMap<Class<? extends Event>,ConcurrentLinkedQueue<Event>> generalQueue;
	private HashMap<String,ArrayList<Address>> subscribers;
	static {
        try {
        	EVENT_CHANNEL_ENTRY_PUBLISHER = new Entry(Inet4Address.getLocalHost().getHostAddress(), 1111);
        	EVENT_CHANNEL_ENTRY_SUBSCRIBER = new Entry(Inet4Address.getLocalHost().getHostAddress(), 1112);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            EVENT_CHANNEL_ENTRY_PUBLISHER = null;
            EVENT_CHANNEL_ENTRY_SUBSCRIBER = null;
        }
    }
	private EventChannel() {
		en = true;	
		generalQueue = new HashMap<Class<? extends Event>,ConcurrentLinkedQueue<Event>>();
		subscribers = new HashMap<String,ArrayList<Address>>();
	}
	
	public static EventChannel instance() {
		if(e == null) return new EventChannel();
		return e;
	}
	public void run() {
		new Thread(this::publish).start();
		new Thread(this::subscribe).start();
		while(en) {
			//for()
		}
	}
	private void updateSubscriberByEventType(String type, Event e2) {
		if(this.subscribers.get(type) == null) return;
		for(Address a:this.subscribers.get(type)) {
			ByteSender b = new ByteSender("EventChannelProxySubscriber");
			byte[] data = Marshaller.marshallEvent(type, e2);
			b.deliver(a, data);
		}
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribe() {
		ByteReceiver r = new ByteReceiver("EventChannelProxyPublisher",this.EVENT_CHANNEL_ENTRY_SUBSCRIBER);
		while(en) {
			byte[] reply = r.receive();
			if(reply != null) {
				SubscribeMessage msg = (SubscribeMessage) Marshaller.unmarshallMessage(reply,SubscribeMessage.class);
				String type  = msg.getEventType();
				Address subscriberAddress = msg.getSubscriber();
				if(msg.getSub_unsub()) {
					if(!this.subscribers.containsKey(type)) {
						this.subscribers.put(type, new ArrayList<Address>());

					}
					this.subscribers.get(type).add(subscriberAddress);
					
				}
				else {
					this.subscribers.remove(type);
				}
			}
			
			
			
		}
	}

	@Override
	public void publish() {
		ByteReceiver r = new ByteReceiver("EventChannelProxyPublisher",this.EVENT_CHANNEL_ENTRY_PUBLISHER);
		while(en) {
			byte[] reply = r.receive();
			if(reply != null) {
				EventMessage msg = (EventMessage) Marshaller.unmarshallMessage(reply,EventMessage.class);
				Class type  = msg.getEvent().getClass();
				Event event = msg.getEvent();
				if(!this.generalQueue.containsKey(type)) {
					this.generalQueue.put(type, new ConcurrentLinkedQueue<Event>());
				}
				this.generalQueue.get(type).offer(event);
				this.updateSubscriberByEventType(event.getName(), event);
			}
			
			
			
		}
		
	}
	protected void finalize() throws Throwable {
		en = false;
	}
	

}
