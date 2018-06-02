/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author Kushtrim Hajrizi
 */
 
public class Telefoni implements Serializable {

    private Integer telefoniId;
    private String numri;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Useri userId;

    public Telefoni() {
    }

    public Telefoni(Integer telefoniId) {
        this.telefoniId = telefoniId;
    }

    public Telefoni(Integer telefoniId, String numri) {
        this.telefoniId = telefoniId;
        this.numri = numri;
    }

    public Integer getTelefoniId() {
        return telefoniId;
    }

    public void setTelefoniId(Integer telefoniId) {
        this.telefoniId = telefoniId;
    }

    public String getNumri() {
        return numri;
    }

    public void setNumri(String numri) {
        this.numri = numri;
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
        hash += (telefoniId != null ? telefoniId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefoni)) {
            return false;
        }
        Telefoni other = (Telefoni) object;
        if ((this.telefoniId == null && other.telefoniId != null) || (this.telefoniId != null && !this.telefoniId.equals(other.telefoniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Telefoni[ telefoniId=" + telefoniId + " ]";
    }
    
}
