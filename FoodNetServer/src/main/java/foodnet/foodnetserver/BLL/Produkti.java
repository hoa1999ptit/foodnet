/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Produkti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkti.findAll", query = "SELECT p FROM Produkti p")
    , @NamedQuery(name = "Produkti.findByProduktId", query = "SELECT p FROM Produkti p WHERE p.produktId = :produktId")
    , @NamedQuery(name = "Produkti.findByEmri", query = "SELECT p FROM Produkti p WHERE p.emri = :emri")
    , @NamedQuery(name = "Produkti.findByCmimi", query = "SELECT p FROM Produkti p WHERE p.cmimi = :cmimi")})
public class Produkti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProduktId")
    private Integer produktId;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Lob
    @Column(name = "Pershkrimi")
    private String pershkrimi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Cmimi")
    private BigDecimal cmimi;
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JoinColumn(name = "EmriKategoris", referencedColumnName = "EmriKategoris")
    @ManyToOne
    //@Cascade({CascadeType.ALL})
    private Kategoria emriKategoris;

    @Column(name = "BiznesId", insertable = false, updatable = false)
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.READ_ONLY)
    private Integer biznesIdFk;
    
    public Produkti() {
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
