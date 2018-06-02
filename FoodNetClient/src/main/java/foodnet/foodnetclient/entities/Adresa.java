/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import foodnet.foodnetclient.entities.Entity;


/**
 *
 * @author Kushtrim Hajrizi
 */

public class Adresa implements Entity {

    private Integer adresaId;
    private String qyteti;
    private String rruga;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Useri userId;

    public Adresa() {
    }

    public Adresa(Integer adresaId) {
        this.adresaId = adresaId;
    }

    public Adresa(Integer adresaId, String qyteti) {
        this.adresaId = adresaId;
        this.qyteti = qyteti;
    }

    public Integer getAdresaId() {
        return adresaId;
    }

    public void setAdresaId(Integer adresaId) {
        this.adresaId = adresaId;
    }

    public String getQyteti() {
        return qyteti;
    }

    public void setQyteti(String qyteti) {
        this.qyteti = qyteti;
    }

    public String getRruga() {
        return rruga;
    }

    public void setRruga(String rruga) {
        this.rruga = rruga;
    }

    public Biznesi getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Biznesi biznesId) {
        this.biznesId = biznesId;
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
        hash += (adresaId != null ? adresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresa)) {
            return false;
        }
        Adresa other = (Adresa) object;
        if ((this.adresaId == null && other.adresaId != null) || (this.adresaId != null && !this.adresaId.equals(other.adresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Adresa[ adresaId=" + adresaId + " ]";
    }
    
}
