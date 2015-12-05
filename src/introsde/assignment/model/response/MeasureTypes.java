package introsde.assignment.model.response;

import introsde.assignment.model.MeasureType;

import java.util.List;

/**
 * Convenience wrapper for returning the measure types'
 * list to a client.
 */
public class MeasureTypes {
	
	private List<MeasureType> measureTypes;
	
	public MeasureTypes(){
		
	}
	
	public MeasureTypes(List<MeasureType> m){
		measureTypes = m;
	}

	public List<MeasureType> getMeasureTypes() {
		return measureTypes;
	}

	public void setMeasureTypes(List<MeasureType> measureTypes) {
		this.measureTypes = measureTypes;
	}
	
}
