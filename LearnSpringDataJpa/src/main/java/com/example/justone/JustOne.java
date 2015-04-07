package com.example.justone;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class JustOne {
	@Id
	@SequenceGenerator(name="just_one_id_seq", sequenceName="just_one_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="just_one_id_seq")
	@Column(name="id", updatable=false)
	private Long id;
	
	@Column(name="msg", length=100)
	private String message;
	
	@Temporal(TemporalType.DATE)
	private Date dateJoin;
	
	@Enumerated
	private JustOneEnum enumOne;
	
	@Transient
	private String passwordConfirm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateJoin() {
		return dateJoin;
	}

	public void setDateJoin(Date dateJoin) {
		this.dateJoin = dateJoin;
	}

	public JustOneEnum getEnumOne() {
		return enumOne;
	}

	public void setEnumOne(JustOneEnum enumOne) {
		this.enumOne = enumOne;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
