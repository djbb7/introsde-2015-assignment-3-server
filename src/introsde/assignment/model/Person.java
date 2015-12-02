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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
@Table(name="Person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@XmlRootElement
@XmlSeeAlso(CurrentHealth.class)
public class Person {

	@Id
	@GeneratedValue(generator="sqlite_person")
	@TableGenerator(name="sqlite_person", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Person")
	@Column(name="id")
	private Long id;
	
	@Column(name="firstname")
	private  String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date birthdate;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=CurrentHealth.class)
	@JoinColumn(name="idPerson", referencedColumnName="id", nullable=false)
	private List<Measure> currentHealth; // one for each type of measure
	
	
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

	@XmlElementWrapper(name="currentHealth")
	@XmlElement(name="measure")
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
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
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
		p=em.merge(p);
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
		EntityManager em = PersonMeasureDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m = em.merge(m);
		tx.commit();
		PersonMeasureDao.instance.closeConnections(em);
		return m;
	}
	
	
	public static void updateMeasure(Person p, Measure m){
		List<Measure> history = p.getHealthHistory();
		if(history == null)
			history = new ArrayList<Measure>();
		
		for(int i=0; i<history.size(); i++){
			if(history.get(i).getMid() != m.getMid()){
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
	}
}