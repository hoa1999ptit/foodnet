/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "BusinessFeedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessFeedback.findAll", query = "SELECT b FROM BusinessFeedback b")
    , @NamedQuery(name = "BusinessFeedback.findByFeedbackId", query = "SELECT b FROM BusinessFeedback b WHERE b.feedbackId = :feedbackId")
    , @NamedQuery(name = "BusinessFeedback.findBySubjekti", query = "SELECT b FROM BusinessFeedback b WHERE b.subjekti = :subjekti")})
public class BusinessFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FeedbackId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    @Basic(optional = false)
    @Column(name = "Subjekti")
    private String subjekti;
    @Basic(optional = false)
    @Lob
    @Column(name = "Content")
    private String content;
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JoinColumn(name = "Rate", referencedColumnName = "Rate")
    @ManyToOne
    private Rating rate;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    @JsonIgnoreProperties({"adresaList", "telefoniList", "favoriteList"})
    private Useri userId;

    public BusinessFeedback() {
    }

    public BusinessFeedback(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public BusinessFeedback(Integer feedbackId, String subjekti, String content) {
        this.feedbackId = feedbackId;
        this.subjekti = subjekti;
        this.content = content;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getSubjekti() {
        return subjekti;
    }

    public void setSubjekti(String subjekti) {
        this.subjekti = subjekti;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Biznesi getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Biznesi biznesId) {
        this.biznesId = biznesId;
    }

    public Rating getRate() {
        return rate;
    }

    public void setRate(Rating rate) {
        this.rate = rate;
    }

    public Useri getUserId() {
        return userId;
    }

    public void setUserId(Useri userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackId != null ? feedbackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusinessFeedback)) {
            return false;
        }
        BusinessFeedback other = (BusinessFeedback) object;
        if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.BusinessFeedback[ feedbackId=" + feedbackId + " ]";
    }
    
}
