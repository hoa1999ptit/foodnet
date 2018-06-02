/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r")
    , @NamedQuery(name = "Rating.findByRate", query = "SELECT r FROM Rating r WHERE r.rate = :rate")})
public class Rating implements Serializable {
    
//    public static final String ONE = "Very Bad";
//    public static final String TWO = "Bad";
//    public static final String THREE = "Average";
//    public static final String FOUR = "Good";
//    public static final String FIVE = "Very Good";

    
    private static final List<String> ratingMeanings; 
    static {
        ratingMeanings = new ArrayList<>();
        ratingMeanings.add("Very Bad");
        ratingMeanings.add("Bad");
        ratingMeanings.add("Average");
        ratingMeanings.add("Good");
        ratingMeanings.add("Very Good");
    }
    
    public static List<String> getRatingMeanings() {
        return ratingMeanings;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Rate")
    private Integer rate;
    @Basic(optional = false)
    @Lob
    @Column(name = "Meaning")
    private String meaning;
    @OneToMany(mappedBy = "rate")
    @JsonIgnore
    private List<SoftwareFeedback> softwareFeedbackList;

    @OneToMany(mappedBy = "rate")
    @JsonIgnore
    private List<BusinessFeedback> businessFeedbackList;

    public Rating() {
    }

    public Rating(Integer rate) {
        this.rate = rate;
    }

    public Rating(Integer rate, String meaning) {
        this.rate = rate;
        this.meaning = meaning;
    }


    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @XmlTransient
    public List<BusinessFeedback> getBusinessFeedbackList() {
        return businessFeedbackList;
    }

    public void setBusinessFeedbackList(List<BusinessFeedback> businessFeedbackList) {
        this.businessFeedbackList = businessFeedbackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rate != null ? rate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.rate == null && other.rate != null) || (this.rate != null && !this.rate.equals(other.rate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Rating[ rate=" + rate + " ]";
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @XmlTransient
    public List<SoftwareFeedback> getSoftwareFeedbackList() {
        return softwareFeedbackList;
    }

    public void setSoftwareFeedbackList(List<SoftwareFeedback> softwareFeedbackList) {
        this.softwareFeedbackList = softwareFeedbackList;
    }
    
}
