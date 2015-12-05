package introsde.assignment.soap;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
	public static void main(String[] args){
		System.out.println("Starting SOAP server...");
		Endpoint.publish("http://localhost:6903/ws/people", new PeopleImpl());
		System.out.println("SOAP server started.");
	}
}
