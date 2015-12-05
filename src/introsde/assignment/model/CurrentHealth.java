package introsde.assignment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a person's latest measurements.
 * For consistency, this is implemented using the table 'CurrentHealth'
 * which is in reality a stored view.
 */
@Entity
@Table(name="CurrentHealth")
public class CurrentHealth {
	@Id
	@GeneratedValue(generator="sqlite_current")
	@TableGenerator(name="sqlite_current", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="CurrentHealth")
	@Column(name="id")
	private Long mid;
	 
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private Date dateRegistered;
	
	@Column(name="value")
	private String measureValue;
	
	@OneToOne
	@JoinColumn(name="idMeasureType",referencedColumnName="id")
	private MeasureType measureType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPerson")
	private Person person;
	
	public CurrentHealth(){
		
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

	@XmlTransient
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
	

}
