package publisher;

import event.*;
import eventchannel.EventChannel;
import eventchannel.EventChannelAPI;

public class SimplePublisher implements IPublisher{
	private String name;
	private boolean en;
	public SimplePublisher(String name) {
		this.name = name;
		this.en = true;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//generate events as a thread and publish them
		while(en);
	}

	@Override
	public void publish(Event e,Class<? extends Event> type) {
		EventChannelAPI.publish(type,e);
	}
	@Override
	public void stop() {
		en = false;
	}
}
