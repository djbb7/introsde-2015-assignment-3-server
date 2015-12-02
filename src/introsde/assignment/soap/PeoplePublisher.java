package introsde.assignment.soap;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
	public static void main(String[] args){
		Endpoint.publish("http://localhost:6903/ws/people", new PeopleImpl());
	}
}
