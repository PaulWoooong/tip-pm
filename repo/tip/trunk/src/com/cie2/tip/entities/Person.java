package com.cie2.tip.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * The Person entity.
 */
@SuppressWarnings("serial")
public class Person implements Serializable {

	private Long id;

	private Integer version;

	private String firstName;

	private String lastName;

	private Date startDate;

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Person: [");
		buf.append("id=" + id + ", ");
		buf.append("version=" + version + ", ");
		buf.append("firstName=" + firstName + ", ");
		buf.append("lastName=" + lastName + ", ");
		buf.append("startDate=" + startDate);
		buf.append("]");
		return buf.toString();
	}



	// The need for a hashCode() method is discussed at http://www.hibernate.org/109.html

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
