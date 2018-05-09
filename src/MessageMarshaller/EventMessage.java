package MessageMarshaller;

import event.Event;

public class EventMessage extends Message {
	private Event event;
	public EventMessage() {

	}
	public EventMessage(Event e) {
		super("event");
		this.event = e;
		// TODO Auto-generated constructor stub
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
}
