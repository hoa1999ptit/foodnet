/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

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
@Table(name = "Telefoni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefoni.findAll", query = "SELECT t FROM Telefoni t")
    , @NamedQuery(name = "Telefoni.findByTelefoniId", query = "SELECT t FROM Telefoni t WHERE t.telefoniId = :telefoniId")
    , @NamedQuery(name = "Telefoni.findByNumri", query = "SELECT t FROM Telefoni t WHERE t.numri = :numri")})
public class Telefoni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TelefoniId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer telefoniId;
    @Basic(optional = false)
    @Column(name = "Numri")
    private String numri;
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
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
