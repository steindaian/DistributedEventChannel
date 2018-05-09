package MessageMarshaller;

import event.Event;
import sendreceiveapi.commons.Address;
import sendreceiveapi.commons.Entry;

public class SubscribeMessage extends Message {
	private String eventType;
	private Address subscriber;
	private boolean sub_unsub;
	public SubscribeMessage() {
		this.sub_unsub = true;
	}
	public SubscribeMessage(String eventType,Address subscriber2) {
		super("subscribe");
		this.eventType = eventType;
		this.subscriber = subscriber2;
		this.sub_unsub = true;
		// TODO Auto-generated constructor stub
	}
	public SubscribeMessage(boolean s) {
		super("subscribe");
		this.sub_unsub = s;
		// TODO Auto-generated constructor stub
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Address getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(Address subscriber) {
		this.subscriber = subscriber;
	}
	public boolean getSub_unsub() {
		return sub_unsub;
	}
	public void setSub_unsub(boolean sub_unsub) {
		this.sub_unsub = sub_unsub;
	}

}
