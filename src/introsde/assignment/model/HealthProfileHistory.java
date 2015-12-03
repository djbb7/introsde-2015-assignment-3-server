package introsde.assignment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HealthProfileHistory {

	private List<Measure> healthProfile_history;
	
	public HealthProfileHistory(){
		
	}
	
	@XmlElement(name="measure")
	public List<Measure> getHealthProfile_history() {
		return healthProfile_history;
	}

	public void setHealthProfile_history(List<Measure> healthProfile_history) {
		this.healthProfile_history = healthProfile_history;
	}
	
	
	
}
