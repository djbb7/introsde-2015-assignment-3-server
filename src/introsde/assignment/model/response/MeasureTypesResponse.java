package introsde.assignment.model.response;

import introsde.assignment.model.MeasureType;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeasureTypesResponse {
	
	private List<MeasureType> measureTypes;
	
	public MeasureTypesResponse(){
		
	}
	
	public MeasureTypesResponse(List<MeasureType> m){
		measureTypes = m;
	}

	@XmlElement(name="measureType")
	public List<MeasureType> getMeasureTypes() {
		return measureTypes;
	}

	public void setMeasureTypes(List<MeasureType> measureTypes) {
		this.measureTypes = measureTypes;
	}
	
}
