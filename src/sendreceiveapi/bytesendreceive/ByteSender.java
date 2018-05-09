package sendreceiveapi.bytesendreceive;

import java.net.*;

import sendreceiveapi.commons.Address;

import java.io.*;

public class ByteSender
{
	private Socket s;
	private OutputStream oStr;
	private String myName;
	public ByteSender(String theName) { myName = theName; }


	public void deliver(Address theDest, byte[] data)
	{
		try
		{
			s = new Socket(theDest.dest(), theDest.port());
			System.out.println("Sender: Socket" + s);
			oStr = s.getOutputStream();
			oStr.write(data.length);
			oStr.write(data.length>>8);
			oStr.write(data);
			oStr.flush();
			oStr.close();
			s.close();
		}
		catch (IOException e)
		{
			System.out.println("IOException in deliver");
			e.printStackTrace();
		}
	}
}


