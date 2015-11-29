/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.myhibernate.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Transient;
import javax.persistence.Embedded;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Formula;


/**
 *
 * @author Angga
 */

@Entity
@Table(name="FINANCES_USER")
@Access(value=AccessType.FIELD)
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // @GeneratedValue <- auto strategy
    @Column(name="USER_ID")
    private Long userId;
    
    @Column(name="FIRST_NAME")
    private String firstName;
        
    @Column(name="LAST_NAME")
    private String lastName;
        
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTH_DATE", nullable=false)
    private Date birthDate;
        
    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;
    
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="addressLine1", column=@Column(name="USER_ADDRESS_LINE_1")),
        @AttributeOverride(name="addressLine2", column=@Column(name="USER_ADDRESS_LINE_2"))})
    private Address address;
        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_UPDATED_DATE")
    private Date lastUpdatedDate;
        
    @Column(name="LAST_UPDATED_BY")
    private String lastUpdatedBy;
        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE", updatable=false)
    private Date createdDate;

    @Column(name="CREATED_BY", updatable=false)
    private String createdBy;
    
    @Transient
    private boolean valid;
    
    @Formula("lower(datediff(curdate(), birth_date)/365)")
    private int age;
    
    public boolean isValid(){
        return valid;
    }
    
    public void setValid(boolean valid){
        this.valid = valid;
    }           
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdateDate) {
        this.lastUpdatedDate = lastUpdateDate;
    }
    
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdateBy) {
        this.lastUpdatedBy = lastUpdateBy;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
