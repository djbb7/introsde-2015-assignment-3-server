package introsde.assignment.soap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException{
		System.out.println("Starting SOAP server...");
		
		String protocol = "http://";
        String port_value = "6903";
        if (String.valueOf(System.getenv("PORT")) != "null"){
            port_value=String.valueOf(System.getenv("PORT"));
        }
        String port = ":"+port_value+"/";
        String hostname = InetAddress.getLocalHost().getHostAddress();
        if (hostname.equals("127.0.0.1"))
        {
            hostname = "localhost";
        }
        
        URI BASE_URI = new URI(protocol + hostname + port);

		Endpoint.publish(BASE_URI.toString()+"ws/people", new PeopleImpl());
		System.out.println("SOAP server started at: "+BASE_URI.toString()+"ws/people");
	}
}
