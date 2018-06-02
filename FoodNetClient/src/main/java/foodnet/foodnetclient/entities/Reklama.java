/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import java.util.Date;

/**
 *
 * @author Kushtrim Hajrizi
 */

public class Reklama implements Entity {

    private Integer reklamaId;
    private Date createAt;
    private Date endsAt;
    private Biznesi biznesId;
    private ReklamaType reklamaTypeEmri;

    public Reklama() {
    }

    public Reklama(Integer reklamaId) {
        this.reklamaId = reklamaId;
    }

    public Integer getReklamaId() {
        return reklamaId;
    }

    public void setReklamaId(Integer reklamaId) {
        this.reklamaId = reklamaId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Biznesi getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Biznesi biznesId) {
        this.biznesId = biznesId;
    }

    public ReklamaType getReklamaTypeEmri() {
        return reklamaTypeEmri;
    }

    public void setReklamaTypeEmri(ReklamaType reklamaTypeEmri) {
        this.reklamaTypeEmri = reklamaTypeEmri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reklamaId != null ? reklamaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reklama)) {
            return false;
        }
        Reklama other = (Reklama) object;
        if ((this.reklamaId == null && other.reklamaId != null) || (this.reklamaId != null && !this.reklamaId.equals(other.reklamaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Reklama[ reklamaId=" + reklamaId + " ]";
    }
    
}
