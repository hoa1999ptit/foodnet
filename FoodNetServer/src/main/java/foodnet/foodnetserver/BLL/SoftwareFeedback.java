/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

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
@Table(name = "SoftwareFeedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoftwareFeedback.findAll", query = "SELECT s FROM SoftwareFeedback s")
    , @NamedQuery(name = "SoftwareFeedback.findByFeedbackId", query = "SELECT s FROM SoftwareFeedback s WHERE s.feedbackId = :feedbackId")
    , @NamedQuery(name = "SoftwareFeedback.findBySubjekti", query = "SELECT s FROM SoftwareFeedback s WHERE s.subjekti = :subjekti")})
public class SoftwareFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FeedbackId")
    private Integer feedbackId;
    @Basic(optional = false)
    @Column(name = "Subjekti")
    private String subjekti;
    @Basic(optional = false)
    @Lob
    @Column(name = "Content")
    private String content;
    @JoinColumn(name = "LoginId", referencedColumnName = "LoginId")
    @ManyToOne
    private LoginEntity loginId;
    @JoinColumn(name = "Rate", referencedColumnName = "Rate")
    @ManyToOne
    private Rating rate;

    public SoftwareFeedback() {
    }

    public SoftwareFeedback(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public SoftwareFeedback(Integer feedbackId, String subjekti, String content) {
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

    public LoginEntity getLoginId() {
        return loginId;
    }

    public void setLoginId(LoginEntity loginId) {
        this.loginId = loginId;
    }

    public Rating getRate() {
        return rate;
    }

    public void setRate(Rating rate) {
        this.rate = rate;
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
        if (!(object instanceof SoftwareFeedback)) {
            return false;
        }
        SoftwareFeedback other = (SoftwareFeedback) object;
        if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.SoftwareFeedback[ feedbackId=" + feedbackId + " ]";
    }
    
}
