package introsde.assignment.model;

import introsde.assignment.dao.PersonMeasureDao;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Measure")
@XmlRootElement
@NamedQueries(value={
		@NamedQuery(name="Measure.findMeasure",
				query="SELECT m "
					+ "FROM Measure m "
					+ "WHERE m.person.id=:person "
					+ "AND m.measureType.measureType=:measureType "
					+ "AND m.mid=:mid"
			)
})
public class Measure {
	
	@Id
	@GeneratedValue(generator="sqlite_measure")
	@TableGenerator(name="sqlite_measure", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Measure")
	@Column(name="id")
	private Long mid;
	 
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private Date dateRegistered = new Date();
	
	@Column(name="value")
	private String measureValue;
	
	@OneToOne
	@JoinColumn(name="idMeasureType",referencedColumnName="id")
	private MeasureType measureType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPerson")
	private Person person;
	
	public Measure(){
		
	}

	public Long getMid() {
		return mid;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public String getMeasureValue() {
		return measureValue;
	}

	public MeasureType getMeasureType() {
		return measureType;
	}

	public Person getPerson() {
		return person;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	

	public void setMeasureValue(String measureValue) {
		this.measureValue = measureValue;
	}

	public void setMeasureType(MeasureType measureType) {
		this.measureType = measureType;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	//database operations
	public static Measure getMeasureById(long personId, String measureType, long mid) {
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		Measure m = null;
		try {
			m = em.createNamedQuery("Measure.findMeasure", Measure.class)
		    		.setParameter("person", personId)
		    		.setParameter("mid", mid)
		    		.setParameter("measureType", measureType)
		    		.getSingleResult();
		} catch (NoResultException e){
		
		}
		PersonMeasureDao.instance.closeConnections(em);
		return m;
	}
	
}