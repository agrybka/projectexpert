/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agnieszka.projectexpert.core.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aga
 */
@Entity
@Table(name = "memeber")
@NamedQueries({
    @NamedQuery(name = "Memeber.findAll", query = "SELECT m FROM Memeber m"),
    @NamedQuery(name = "Memeber.findById", query = "SELECT m FROM Memeber m WHERE m.id = :id"),
    @NamedQuery(name = "Memeber.findByRole", query = "SELECT m FROM Memeber m WHERE m.role = :role")})
public class Memeber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "member_mail", referencedColumnName = "mail")
    @ManyToOne(optional = false)
    private User memberMail;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne
    private Project projectId;

    public Memeber() {
    }

    public Memeber(Integer id) {
        this.id = id;
    }

    public Memeber(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getMemberMail() {
        return memberMail;
    }

    public void setMemberMail(User memberMail) {
        this.memberMail = memberMail;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memeber)) {
            return false;
        }
        Memeber other = (Memeber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agnieszka.projekty.model.Memeber[ id=" + id + " ]";
    }
    
}
