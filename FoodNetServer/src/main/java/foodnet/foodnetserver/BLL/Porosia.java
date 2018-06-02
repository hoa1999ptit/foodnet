/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Porosia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porosia.findAll", query = "SELECT p FROM Porosia p")
    , @NamedQuery(name = "Porosia.findByPorosiaId", query = "SELECT p FROM Porosia p WHERE p.porosiaId = :porosiaId")
    , @NamedQuery(name = "Porosia.findByCreatedAt", query = "SELECT p FROM Porosia p WHERE p.createdAt = :createdAt")})
public class Porosia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PorosiaId")
    private Integer porosiaId;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    @JsonIgnoreProperties(
            {"favoriteList", "commentList", "postLikeList", "loginEntityId",
            "softwareFeedbackList", "businessFeedbackList", "porosiaList"})
    private Useri userId;
    @OneToMany(mappedBy = "porosiaId")
    @Cascade({CascadeType.ALL})
    private List<PorosiaProdukti> porosiaProduktiList;

    public Porosia() {
    }

    public Porosia(Integer porosiaId) {
        this.porosiaId = porosiaId;
    }

    public Integer getPorosiaId() {
        return porosiaId;
    }

    public void setPorosiaId(Integer porosiaId) {
        this.porosiaId = porosiaId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @XmlTransient
    public List<PorosiaProdukti> getPorosiaProduktiList() {
        return porosiaProduktiList;
    }

    public void setPorosiaProduktiList(List<PorosiaProdukti> porosiaProduktiList) {
        this.porosiaProduktiList = porosiaProduktiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (porosiaId != null ? porosiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porosia)) {
            return false;
        }
        Porosia other = (Porosia) object;
        if ((this.porosiaId == null && other.porosiaId != null) || (this.porosiaId != null && !this.porosiaId.equals(other.porosiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Porosia[ porosiaId=" + porosiaId + " ]";
    }
    
}
