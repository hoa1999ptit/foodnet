/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Kushtrim Hajrizi
 */

public class SoftwareFeedback implements Entity {

    private Integer feedbackId;
    private String subjekti;
    private String content;
    @JsonIgnore
    private LoginEntity loginId;
    @JsonProperty(value = "rate", access = JsonProperty.Access.WRITE_ONLY)
    private Rating rate;
    @JsonProperty(value = "rate", access = JsonProperty.Access.READ_ONLY)
    private Integer rating;
    

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

    public SoftwareFeedback(String subjekti, String content, Integer rating) {
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
