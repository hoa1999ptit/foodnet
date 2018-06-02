/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class Useri implements Entity, AuthEntity {    
    
    @JsonProperty(value = "userId")
    private Integer userId;
    @JsonProperty(value = "emri")
    private String emri;
    @JsonProperty(value = "mbiemri")
    private String mbiemri;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Favorite> favoriteList;
    @JsonIgnore
    private List<Comment> commentList;
    @JsonIgnore
    private List<PostLike> postLikeList;
    private List<Adresa> adresaList;
    private List<Telefoni> telefoniList;
    @JsonIgnore
    private List<BusinessFeedback> businessFeedbackList;
    @JsonIgnore
    private List<Porosia> porosiaList;
       
    /**
     * Login Entity
     */
    
    @JsonProperty(value = "loginEntityId", access = JsonProperty.Access.READ_WRITE)
    @JsonIgnoreProperties({"biznesiList", "useriList", "softwareFeedbackList"})
    private LoginEntity loginEntityId;
//    
//    @JsonProperty(value = "loginEntityId", access = JsonProperty.Access.READ_ONLY)
//    private Integer loginEntityFk;

    public Useri() {
    }

    public Useri(Integer userId) {
        this.userId = userId;
    }

    public Useri(Integer userId, String emri, String mbiemri) {
        this.userId = userId;
        this.emri = emri;
        this.mbiemri = mbiemri;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    
    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    
    public List<PostLike> getPostLikeList() {
        return postLikeList;
    }

    public void setPostLikeList(List<PostLike> postLikeList) {
        this.postLikeList = postLikeList;
    }

    
    public List<Adresa> getAdresaList() {
        return adresaList;
    }

    public void setAdresaList(List<Adresa> adresaList) {
        this.adresaList = adresaList;
    }

    
    public List<Telefoni> getTelefoniList() {
        return telefoniList;
    }

    public void setTelefoniList(List<Telefoni> telefoniList) {
        this.telefoniList = telefoniList;
    }

    
    public List<BusinessFeedback> getBusinessFeedbackList() {
        return businessFeedbackList;
    }

    public void setBusinessFeedbackList(List<BusinessFeedback> businessFeedbackList) {
        this.businessFeedbackList = businessFeedbackList;
    }

    
    public List<Porosia> getPorosiaList() {
        return porosiaList;
    }

    public void setPorosiaList(List<Porosia> porosiaList) {
        this.porosiaList = porosiaList;
    }

    public LoginEntity getLoginEntityId() {
        return loginEntityId;
    }

    public void setLoginEntityId(LoginEntity loginEntityId) {
        this.loginEntityId = loginEntityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useri)) {
            return false;
        }
        Useri other = (Useri) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return emri + " " + mbiemri;
    }
    
    /////////////////////////
    // Auth Entity Methods //
    /////////////////////////
    
    @Override
    @JsonIgnore
    public String getUsername() {
        return loginEntityId.getUsername();
    }
    
    @Override
    @JsonIgnore
    public String getEmail() {
        return loginEntityId.getEmail();
    }
    
    @Override
    @JsonIgnore
    public String getName() {
        return emri + " " + mbiemri;
    }
    
    @Override
    @JsonIgnore
    public int getId() {
        return userId;
    }
    
    @Override
    @JsonIgnore
    public int getAuthenticationId() {
        return loginEntityId.getLoginId();
    }
    
    @Override
    @JsonIgnore
    public int getType() {
        return AuthEntity.USER;
    }
}
