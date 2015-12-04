package introsde.assignment.model;

import introsde.assignment.dao.PersonMeasureDao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="MeasureType")
@NamedQuery(name="MeasureType.findAll", query="SELECT m FROM MeasureType m")
public class MeasureType {

	@Id
	@GeneratedValue(generator="sqlite_measure_type")
	@TableGenerator(name="sqlite_measure_type", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="MeasureType")
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String measureType;

	@Column(name="valueType")
	private String measureValueType; // string, integer, real

	public MeasureType(){
		
	}
	
	public String getMeasureType() {
		return measureType;
	}

	public String getMeasureValueType() {
		return measureValueType;
	}
	
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public void setMeasureValueType(String measureValueType) {
		this.measureValueType = measureValueType;
	}
	
	//database operations
	public static List<MeasureType> getMeasureTypes(){
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
	    List<MeasureType> list = em.createNamedQuery("MeasureType.findAll", MeasureType.class).getResultList();
	    PersonMeasureDao.instance.closeConnections(em);
	    return list;
	}
	
	public static MeasureType getMeasureTypeByName(String name) {
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		MeasureType m = em.createQuery("Select m FROM MeasureType m WHERE m.measureType=?1", MeasureType.class)
				.setParameter(1, name)
				.getSingleResult();
		PersonMeasureDao.instance.closeConnections(em);
		return m;
	}
}
