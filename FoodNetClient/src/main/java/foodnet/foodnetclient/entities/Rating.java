/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kushtrim Hajrizi
 */ 
public class Rating implements Entity {
    
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
    
    private Integer rate;
    private String meaning;
    @JsonIgnore
    private List<SoftwareFeedback> softwareFeedbackList;
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

    
    public List<SoftwareFeedback> getSoftwareFeedbackList() {
        return softwareFeedbackList;
    }

    public void setSoftwareFeedbackList(List<SoftwareFeedback> softwareFeedbackList) {
        this.softwareFeedbackList = softwareFeedbackList;
    }
    
}
