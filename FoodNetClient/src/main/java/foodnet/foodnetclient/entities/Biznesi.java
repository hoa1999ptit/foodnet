/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import foodnet.foodnetclient.entities.Entity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class Biznesi implements Entity, AuthEntity {

   
    private Integer biznesId;
    private String emri;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date oraHapes;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date oraMbylljes;
    @JsonIgnore
    private List<Favorite> favoriteList;
    //@JsonIgnore
    private List<Produkti> produktiList;
    private List<Adresa> adresaList;
    @JsonIgnoreProperties({"commentList"})
    private List<Post> postList;
    private List<Telefoni> telefoniList;
    @JsonIgnore
    private List<Reklama> reklamaList;
    @JsonIgnore
    private List<BusinessFeedback> businessFeedbackList;
    @JsonIgnore
    private List<Porosia> porosiaList;
    
    
    /**
     * Login Entity
     */
    @JsonProperty(value = "loginEntityId", access = JsonProperty.Access.READ_WRITE)
    private LoginEntity loginEntityId;
//    
//    @JsonProperty(value = "loginEntityId", access = JsonProperty.Access.READ_ONLY)
//    private Integer loginEntityFk;

    public Biznesi() {
    }

    public Biznesi(Integer biznesId) {
        this.biznesId = biznesId;
    }

    public Biznesi(Integer biznesId, String emri) {
        this.biznesId = biznesId;
        this.emri = emri;
    }

    public Integer getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Integer biznesId) {
        this.biznesId = biznesId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public Date getOraHapes() {
        return oraHapes;
    }

    public void setOraHapes(Date oraHapes) {
        this.oraHapes = oraHapes;
    }

    public Date getOraMbylljes() {
        return oraMbylljes;
    }

    public void setOraMbylljes(Date oraMbylljes) {
        this.oraMbylljes = oraMbylljes;
    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public List<Produkti> getProduktiList() {
        return produktiList;
    }

    public void setProduktiList(List<Produkti> produktiList) {
        this.produktiList = produktiList;
    }

    public List<Adresa> getAdresaList() {
        return adresaList;
    }

    public void setAdresaList(List<Adresa> adresaList) {
        this.adresaList = adresaList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Telefoni> getTelefoniList() {
        return telefoniList;
    }

    public void setTelefoniList(List<Telefoni> telefoniList) {
        this.telefoniList = telefoniList;
    }

    public List<Reklama> getReklamaList() {
        return reklamaList;
    }

    public void setReklamaList(List<Reklama> reklamaList) {
        this.reklamaList = reklamaList;
    }

    public LoginEntity getLoginEntityId() {
        return loginEntityId;
    }

    public void setLoginEntityId(LoginEntity loginEntityId) {
        this.loginEntityId = loginEntityId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (biznesId != null ? biznesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biznesi)) {
            return false;
        }
        Biznesi other = (Biznesi) object;
        if ((this.biznesId == null && other.biznesId != null) || (this.biznesId != null && !this.biznesId.equals(other.biznesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Biznesi[ biznesId=" + biznesId + " ]";
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
        return emri;
    }
    
    @Override
    @JsonIgnore
    public int getId() {
        return biznesId;
    }
    
    @Override
    @JsonIgnore
    public int getAuthenticationId() {
        return loginEntityId.getLoginId();
    }
    
    @Override
    @JsonIgnore
    public int getType() {
        return AuthEntity.BUSINESS;
    }
    
}
