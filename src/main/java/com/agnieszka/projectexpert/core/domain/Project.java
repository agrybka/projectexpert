/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agnieszka.projectexpert.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aga
 */
@Entity
@Table(name = "project")
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"),
    @NamedQuery(name = "Project.findByTitle", query = "SELECT p FROM Project p WHERE p.title = :title"),
    @NamedQuery(name = "Project.findByDescription", query = "SELECT p FROM Project p WHERE p.description = :description"),
    @NamedQuery(name = "Project.findByStartDate", query = "SELECT p FROM Project p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Project.findByEndDate", query = "SELECT p FROM Project p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Project.findByStatus", query = "SELECT p FROM Project p WHERE p.status = :status"),
    @NamedQuery(name = "Project.findByPlanStartDate", query = "SELECT p FROM Project p WHERE p.planStartDate = :planStartDate"),
    @NamedQuery(name = "Project.findByPlanEndDate", query = "SELECT p FROM Project p WHERE p.planEndDate = :planEndDate"),
    @NamedQuery(name = "Project.findByProjectcol", query = "SELECT p FROM Project p WHERE p.projectcol = :projectcol"),
    @NamedQuery(name = "Project.findByAuthorMail", query = "SELECT p FROM Project p WHERE p.authorMail = :authorMail")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plan_start_date")
    @Temporal(TemporalType.DATE)
    private Date planStartDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plan_end_date")
    @Temporal(TemporalType.DATE)
    private Date planEndDate;
    @Size(max = 45)
    @Column(name = "projectcol")
    private String projectcol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "author_mail")
    private String authorMail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private List<Document> documentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private List<Comment> commentList;
    @OneToMany(mappedBy = "projectId")
    private List<Memeber> memeberList;

    public Project() {
    }

    public Project(Integer id) {
        this.id = id;
    }

    public Project(Integer id, String title, String description, Date startDate, Date endDate, String status, Date planStartDate, Date planEndDate, String authorMail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.authorMail = authorMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getProjectcol() {
        return projectcol;
    }

    public void setProjectcol(String projectcol) {
        this.projectcol = projectcol;
    }

    public String getAuthorMail() {
        return authorMail;
    }

    public void setAuthorMail(String authorMail) {
        this.authorMail = authorMail;
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

    public List<Memeber> getMemeberList() {
        return memeberList;
    }

    public void setMemeberList(List<Memeber> memeberList) {
        this.memeberList = memeberList;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agnieszka.projekty.model.Project[ id=" + id + " ]";
    }
    
}
