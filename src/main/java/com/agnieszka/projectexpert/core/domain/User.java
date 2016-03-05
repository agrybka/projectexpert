/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agnieszka.projectexpert.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aga
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByMail", query = "SELECT u FROM User u WHERE u.mail = :mail"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByStatus", query = "SELECT u FROM User u WHERE u.status = :status"),
    @NamedQuery(name = "User.findBySex", query = "SELECT u FROM User u WHERE u.sex = :sex"),
    @NamedQuery(name = "User.findByType", query = "SELECT u FROM User u WHERE u.type = :type"),
    @NamedQuery(name = "User.findByMobile", query = "SELECT u FROM User u WHERE u.mobile = :mobile")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SEX sex;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserType type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "mobile")
    private String mobile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userMail")
    private List<Document> documentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userMail")
    private List<Comment> commentList;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberMail")
    private List<Memeber> memeberList;

    public User() {
    }

    public User(String mail) {
        this.mail = mail;
    }

   

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Memeber> getMemeberList() {
        return memeberList;
    }

    public void setMemeberList(List<Memeber> memeberList) {
        this.memeberList = memeberList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mail != null ? mail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.mail == null && other.mail != null) || (this.mail != null && !this.mail.equals(other.mail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agnieszka.projekty.model.User[ mail=" + mail + " ]";
    }
    
}
