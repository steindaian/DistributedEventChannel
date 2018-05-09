package event;

public class BookEvent extends Event{
	private String type;
	public BookEvent(String name,String type) {
		super(name);
		this.type = type;
	}
	public String toString() {
		return super.toString() + "topic: "+ type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
