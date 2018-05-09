package subscriber;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.gson.*;

import MessageMarshaller.Marshaller;
import event.ErrorEvent;
import event.Event;
import eventchannel.EventChannel;
import eventchannel.EventChannelAPI;
import sendreceiveapi.bytesendreceive.ByteReceiver;
import sendreceiveapi.commons.Address;
import sendreceiveapi.commons.Entry;

public class SimpleSubscriber implements ISubscriber{
	private boolean en;
	private Address myAddr;
	private int port;
	private ArrayList<Class<? extends Event>> events;
	public SimpleSubscriber(int port) throws UnknownHostException {
		en = true;
		this.port = port;
		myAddr = new Entry(Inet4Address.getLocalHost().getHostAddress(),port);
		events = new ArrayList<Class<? extends Event>>();
	}
	public void doSubscribe(Class<? extends Event> typeEvent) {
		events.add(typeEvent);
		EventChannelAPI.subscribe(typeEvent.getSimpleName(), myAddr);
	}
	public void run() {
		new Thread(this::proxyRun).start();
		//work and wait for inform to be called
		while(en);
	}
	@Override
	public void stop() {
		en = false;		
	}
	@Override
	public void inform(Event e) {
		//just print event
		if(events.contains(e.getClass()))
			System.out.println("Recived: " +e);
		else {
			System.out.println("Wrong Event Type");
		}
	}
	@Override
	public void proxyRun() {
		ByteReceiver r = new ByteReceiver("Subscriber",myAddr);
		while(en) {
			byte[] rec = r.receive();
			Event e = Marshaller.unmarshallEvent(rec, events);
			if(e == null) {
				System.out.println("Error on unmarshalling the event");
				continue;
			}
			this.inform(e);
		}
	}
	protected void finalize() throws Throwable {
		this.stop();
	}
}
