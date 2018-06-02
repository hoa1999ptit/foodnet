/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author Kushtrim Hajrizi
 */


public class BusinessFeedback implements Entity {

    private Integer feedbackId;
    private String subjekti;
    private String content;
    
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Biznesi biznesId;
    
    
    @JsonProperty(value = "rate", access = JsonProperty.Access.WRITE_ONLY)
    private Rating rate;
    
    @JsonProperty(value = "rate", access = JsonProperty.Access.READ_ONLY)
    private Integer rating;
    
    
    @JsonIgnoreProperties(value = {"adresaList", "telefoniList", "favoriteList", "loginEntityId"}, allowSetters = true)
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
    
    public BusinessFeedback(String subjekti, String content, Integer rating) {
        this.subjekti = subjekti;
        this.content = content;
        this.rating = rating;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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
