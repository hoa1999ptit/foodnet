/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Adresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresa.findAll", query = "SELECT a FROM Adresa a")
    , @NamedQuery(name = "Adresa.findByAdresaId", query = "SELECT a FROM Adresa a WHERE a.adresaId = :adresaId")
    , @NamedQuery(name = "Adresa.findByQyteti", query = "SELECT a FROM Adresa a WHERE a.qyteti = :qyteti")
    , @NamedQuery(name = "Adresa.findByRruga", query = "SELECT a FROM Adresa a WHERE a.rruga = :rruga")})
public class Adresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AdresaId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adresaId;
    @Basic(optional = false)
    @Column(name = "Qyteti")
    private String qyteti;
    @Column(name = "Rruga")
    private String rruga;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    private Biznesi biznesId;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
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
