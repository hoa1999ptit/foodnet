/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 *
 * @author Kushtrim Hajrizi
 */ 
public class Produkti implements Entity {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer produktId;
    private String emri;
    private String pershkrimi;
    private BigDecimal cmimi;
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    private Kategoria emriKategoris;
    //@JsonProperty(value = "biznesId", access = JsonProperty.Access.READ_ONLY)
    private Integer biznesIdFk;
    
    public Produkti() {
    }

    public Produkti(String emri, String pershkrimi, BigDecimal cmimi, Kategoria emriKategoris) {
        this.emri = emri;
        this.pershkrimi = pershkrimi;
        this.cmimi = cmimi;
        this.emriKategoris = emriKategoris;
    }
    
    

    public Produkti(Integer produktId) {
        this.produktId = produktId;
    }

    public Produkti(Integer produktId, String emri, BigDecimal cmimi) {
        this.produktId = produktId;
        this.emri = emri;
        this.cmimi = cmimi;
    }

    public Integer getProduktId() {
        return produktId;
    }

    public void setProduktId(Integer produktId) {
        this.produktId = produktId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public BigDecimal getCmimi() {
        return cmimi;
    }

    public void setCmimi(BigDecimal cmimi) {
        this.cmimi = cmimi;
    }

    public Biznesi getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Biznesi biznesId) {
        this.biznesId = biznesId;
    }

    public Kategoria getEmriKategoris() {
        return emriKategoris;
    }

    public void setEmriKategoris(Kategoria emriKategoris) {
        this.emriKategoris = emriKategoris;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produktId != null ? produktId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produkti)) {
            return false;
        }
        Produkti other = (Produkti) object;
        if ((this.produktId == null && other.produktId != null) || (this.produktId != null && !this.produktId.equals(other.produktId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Produkti[ produktId=" + produktId + " ]";
    }
    
}
