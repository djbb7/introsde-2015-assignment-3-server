package introsde.assignment.model;

import introsde.assignment.dao.PersonMeasureDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is the main entity and represents a Person
 * along with her measures
 */
@Entity
@Table(name="Person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@XmlSeeAlso(CurrentHealth.class)
public class Person {

	@Id
	@GeneratedValue(generator="sqlite_person")
	@TableGenerator(name="sqlite_person", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Person")
	@Column(name="id", updatable=false)
	private Long id;
	
	@Column(name="firstname")
	private  String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date birthdate;
	
	//The latest measure of each measure type
	@OneToMany(targetEntity=CurrentHealth.class)
	@JoinColumn(name="idPerson", referencedColumnName="id", nullable=false, updatable=false, insertable=false)
	private List<Measure> currentHealth; // one for each type of measure
	
	//All the person's measures
	@OneToMany(cascade=CascadeType.ALL ,fetch=FetchType.EAGER, targetEntity=Measure.class)
	@JoinColumn(name="idPerson", referencedColumnName="id", nullable=false)
	private List<Measure> healthHistory; // all measurements

	public Person(){
		
	}
	
	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public List<Measure> getCurrentHealth() {
		return currentHealth;
	}

	@XmlTransient
	public List<Measure> getHealthHistory() {
		return healthHistory;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public void setCurrentHealth(List<Measure> currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setHealthHistory(List<Measure> healthHistory) {
		this.healthHistory = healthHistory;
	}
	
	// Database operations

	public static Person getPersonById(long personId) {
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		PersonMeasureDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Person> getAll() {
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
	    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
	    PersonMeasureDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Person savePerson(Person p) {
		p.setId(0L);
		p.setCurrentHealth(p.getHealthHistory());

		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    PersonMeasureDao.instance.closeConnections(em);
	    
	    return p;
	}
	
	public static Person updatePerson(Person p) {
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Person storedP = em.find(Person.class, p.getId());
		if(p.getBirthdate() != null)
			storedP.setBirthdate(p.getBirthdate());
		if(p.getFirstname() != null)
			storedP.setFirstname(p.getFirstname());
		if(p.getLastname() != null)
			storedP.setLastname(p.getLastname());
		p=em.merge(storedP);
		tx.commit();
	    PersonMeasureDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removePerson(Person p) {
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    PersonMeasureDao.instance.closeConnections(em);
	}

	public static Measure addMeasure(Person p, Measure m){
		List<Measure> history = p.getHealthHistory();
		if(history == null)
			history = new ArrayList<Measure>();
		
		history.add(m);
		m.setPerson(p);
		m.setMeasureType(MeasureType.getMeasureTypeByName(m.getMeasureType().getMeasureType()));
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
		PersonMeasureDao.instance.closeConnections(em);
		return m;
	}
	
	
	public static Measure updateMeasure(Person p, Measure m){
		List<Measure> history = p.getHealthHistory();
		if(history == null){
			history = new ArrayList<Measure>();
			p.setHealthHistory(history);
			history.add(m);
		}
		for(int i=0; i<history.size(); i++){
			if(history.get(i).getMid().longValue() != m.getMid().longValue()){
				continue;
			}
			history.set(i, m);
			break;
		}
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    PersonMeasureDao.instance.closeConnections(em);
	    return m;
	}
}