/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author Kushtrim Hajrizi
 */
 
public class ReklamaType implements Entity {

    private int reklamaTypeId;
    private BigDecimal cmimiPerDite;    
    private String emri;
    private List<Reklama> reklamaList;

    public ReklamaType() {
    }

    public ReklamaType(String emri) {
        this.emri = emri;
    }

    public ReklamaType(String emri, int reklamaTypeId, BigDecimal cmimiPerDite) {
        this.emri = emri;
        this.reklamaTypeId = reklamaTypeId;
        this.cmimiPerDite = cmimiPerDite;
    }


    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }
    
    public List<Reklama> getReklamaList() {
        return reklamaList;
    }

    public void setReklamaList(List<Reklama> reklamaList) {
        this.reklamaList = reklamaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emri != null ? emri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReklamaType)) {
            return false;
        }
        ReklamaType other = (ReklamaType) object;
        if ((this.emri == null && other.emri != null) || (this.emri != null && !this.emri.equals(other.emri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.ReklamaType[ emri=" + emri + " ]";
    }

    public int getReklamaTypeId() {
        return reklamaTypeId;
    }

    public void setReklamaTypeId(int reklamaTypeId) {
        this.reklamaTypeId = reklamaTypeId;
    }

    public BigDecimal getCmimiPerDite() {
        return cmimiPerDite;
    }

    public void setCmimiPerDite(BigDecimal cmimiPerDite) {
        this.cmimiPerDite = cmimiPerDite;
    }
    
}
