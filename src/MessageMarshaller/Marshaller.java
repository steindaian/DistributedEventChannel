package MessageMarshaller;

import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.annotate.*;
import event.Event;



public class Marshaller
{
	public static ObjectMapper mapper = new ObjectMapper();

	public static byte[] marshallEvent(String type,Event obj){
        String eventData;
		try {
			//mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
			eventData = mapper.writeValueAsString(obj);
			byte[] res = new String(type+":"+eventData).getBytes();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
    }

    public static Event unmarshallEvent(byte[] reply, ArrayList<Class<? extends Event>> events){
        String response = new String(reply);
        String type = response.substring(0, response.indexOf(":"));
        Class<? extends Event> mClass = null;
        for(Class<? extends Event> c:events) {
        	if(c.getSimpleName().equals(type)) {
        		mClass = c;
        		break;
        	}
        }
        if(mClass != null) {
        	Event e;
			try {
	 			e = (Event) mapper.readValue(new String(response.substring(response.indexOf(":")+1,response.length()-1)), mClass);
				return e;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
        	
        }
        return null;
    }
    public static byte[] marshallMessage(Message msg) {
    	try {
			String res = mapper.writeValueAsString(msg);
			return res.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    public static Message unmarshallMessage(byte[] reply,Class<? extends Message> mClass) {
    	try {
			Message m = (Message)mapper.readValue(new String(reply), mClass);
			return m;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

}





