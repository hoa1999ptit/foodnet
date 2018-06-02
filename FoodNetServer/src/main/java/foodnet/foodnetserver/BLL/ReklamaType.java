/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "ReklamaType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReklamaType.findAll", query = "SELECT r FROM ReklamaType r")
    , @NamedQuery(name = "ReklamaType.findByReklamaTypeId", query = "SELECT r FROM ReklamaType r WHERE r.reklamaTypeId = :reklamaTypeId")
    , @NamedQuery(name = "ReklamaType.findByEmri", query = "SELECT r FROM ReklamaType r WHERE r.emri = :emri")
    , @NamedQuery(name = "ReklamaType.findByCmimiPerDite", query = "SELECT r FROM ReklamaType r WHERE r.cmimiPerDite = :cmimiPerDite")})
public class ReklamaType implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ReklamaTypeId")
    private int reklamaTypeId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CmimiPerDite")
    private BigDecimal cmimiPerDite;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "Emri")
    private String emri;
    @OneToMany(mappedBy = "reklamaTypeEmri")
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


    @XmlTransient
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
