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

public class PorosiaProdukti implements Entity {
    
    private Integer porosiaProduktiId;
    @JsonIgnore
    private Integer porosiaId;
    @JsonProperty(value = "produktiId", access = JsonProperty.Access.READ_ONLY)
    private Integer produktiId;
    @JsonProperty(value = "produktiId", access = JsonProperty.Access.WRITE_ONLY)
    private Produkti produkti;
    @JsonProperty(value = "porosiaId", access = JsonProperty.Access.READ_ONLY)
    private Integer porosiaIdFk;

    
    public PorosiaProdukti() {
    }

    public PorosiaProdukti(Integer porosiaProduktiId) {
        this.porosiaProduktiId = porosiaProduktiId;
    }

    public Integer getPorosiaProduktiId() {
        return porosiaProduktiId;
    }

    public void setPorosiaProduktiId(Integer porosiaProduktiId) {
        this.porosiaProduktiId = porosiaProduktiId;
    }

    public Integer getPorosiaId() {
        return porosiaId;
    }

    public void setPorosiaId(Integer porosiaId) {
        this.porosiaId = porosiaId;
    }

    public Integer getProduktiId() {
        return produktiId;
    }

    public void setProduktiId(Integer produktiId) {
        this.produktiId = produktiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (porosiaProduktiId != null ? porosiaProduktiId.hashCode() : 0);
        return hash;
    }

    public Produkti getProdukti() {
        return produkti;
    }

    public void setProdukti(Produkti produkti) {
        this.produkti = produkti;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorosiaProdukti)) {
            return false;
        }
        PorosiaProdukti other = (PorosiaProdukti) object;
        if ((this.porosiaProduktiId == null && other.porosiaProduktiId != null) || (this.porosiaProduktiId != null && !this.porosiaProduktiId.equals(other.porosiaProduktiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.PorosiaProdukti[ porosiaProduktiId=" + porosiaProduktiId + " ]";
    }
    
}
