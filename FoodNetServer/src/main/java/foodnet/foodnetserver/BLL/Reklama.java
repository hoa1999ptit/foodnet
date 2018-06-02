/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Reklama")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reklama.findAll", query = "SELECT r FROM Reklama r")
    , @NamedQuery(name = "Reklama.findByReklamaId", query = "SELECT r FROM Reklama r WHERE r.reklamaId = :reklamaId")
    , @NamedQuery(name = "Reklama.findByCreateAt", query = "SELECT r FROM Reklama r WHERE r.createAt = :createAt")
    , @NamedQuery(name = "Reklama.findByEndsAt", query = "SELECT r FROM Reklama r WHERE r.endsAt = :endsAt")})
public class Reklama implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReklamaId")
    private Integer reklamaId;
    @Column(name = "CreateAt")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "EndsAt")
    @Temporal(TemporalType.DATE)
    private Date endsAt;
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    private Biznesi biznesId;
    @JoinColumn(name = "ReklamaTypeEmri", referencedColumnName = "Emri")
    @ManyToOne
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
