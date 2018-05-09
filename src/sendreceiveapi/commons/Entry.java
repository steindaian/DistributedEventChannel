package sendreceiveapi.commons;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("entry")
public class Entry implements Address
{
	private String destinationId;
	private int portNr;
	public Entry() {
		
	}
	public Entry(String theDest, int thePort)
	{
		destinationId = theDest;
		portNr = thePort;
	}
	public String dest()
	{
		return destinationId;
	}
	public int port()
	{
		return portNr;
	}
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	public int getPortNr() {
		return portNr;
	}
	public void setPortNr(int portNr) {
		this.portNr = portNr;
	}
}