package MessageMarshaller;

public class Message
{
	public String name;
	public Message() {
		
	}
	public Message(String type)
	{
		this.name = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}