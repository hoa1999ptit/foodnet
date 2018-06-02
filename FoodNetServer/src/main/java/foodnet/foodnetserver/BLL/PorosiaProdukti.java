/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "PorosiaProdukti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PorosiaProdukti.findAll", query = "SELECT p FROM PorosiaProdukti p")
    , @NamedQuery(name = "PorosiaProdukti.findByPorosiaProduktiId", query = "SELECT p FROM PorosiaProdukti p WHERE p.porosiaProduktiId = :porosiaProduktiId")})
public class PorosiaProdukti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PorosiaProduktiId")
    private Integer porosiaProduktiId;
    @JoinColumn(name = "PorosiaId", referencedColumnName = "PorosiaId")
    @ManyToOne
    @JsonIgnore
    private Porosia porosiaId;
    @JoinColumn(name = "ProduktiId", referencedColumnName = "ProduktId")
    @ManyToOne
    //@JsonProperty(value = "produktiId", access = JsonProperty.Access.WRITE_ONLY)
    private Produkti produktiId;
    
    @Column(name = "PorosiaId", insertable = false, updatable = false)
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

    public Porosia getPorosiaId() {
        return porosiaId;
    }

    public void setPorosiaId(Porosia porosiaId) {
        this.porosiaId = porosiaId;
    }

    public Produkti getProduktiId() {
        return produktiId;
    }

    public void setProduktiId(Produkti produktiId) {
        this.produktiId = produktiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (porosiaProduktiId != null ? porosiaProduktiId.hashCode() : 0);
        return hash;
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
