package introsde.assignment.model;

import java.util.List;

/**
 * Simple wrapper for the health profile history.
 * This is mainly done to make the names in the generated
 * SOAP messages nicer.
 */
public class HealthProfileHistory {

	private List<Measure> healthProfile_history;
	
	public HealthProfileHistory(){
		
	}
	
	public List<Measure> getHealthProfile_history() {
		return healthProfile_history;
	}

	public void setHealthProfile_history(List<Measure> healthProfile_history) {
		this.healthProfile_history = healthProfile_history;
	}
	
	
	
}
