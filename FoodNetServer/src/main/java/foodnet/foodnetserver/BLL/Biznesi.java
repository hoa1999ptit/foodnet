/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Biznesi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biznesi.findAll", query = "SELECT b FROM Biznesi b")
    , @NamedQuery(name = "Biznesi.findByBiznesId", query = "SELECT b FROM Biznesi b WHERE b.biznesId = :biznesId")
    , @NamedQuery(name = "Biznesi.findByEmri", query = "SELECT b FROM Biznesi b WHERE b.emri = :emri")
    , @NamedQuery(name = "Biznesi.findByOraHapes", query = "SELECT b FROM Biznesi b WHERE b.oraHapes = :oraHapes")
    , @NamedQuery(name = "Biznesi.findByOraMbylljes", query = "SELECT b FROM Biznesi b WHERE b.oraMbylljes = :oraMbylljes")})
public class Biznesi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BiznesId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer biznesId;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Column(name = "OraHapes")
    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date oraHapes;
    @Column(name = "OraMbylljes")
    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date oraMbylljes;
    @OneToMany(mappedBy = "biznesId")
    @JsonIgnore
    private List<Favorite> favoriteList;
    @OneToMany(mappedBy = "biznesId")
    //@JsonIgnore
    private List<Produkti> produktiList;
    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "biznesId")
    private List<Adresa> adresaList;
    @OneToMany(mappedBy = "biznesId")
    @JsonIgnoreProperties({"commentList"})
    private List<Post> postList;
    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "biznesId")
    private List<Telefoni> telefoniList;
    @OneToMany(mappedBy = "biznesId")
    @JsonIgnore
    private List<Reklama> reklamaList;
    @OneToMany(mappedBy = "biznesId")
    @JsonIgnore
    private List<BusinessFeedback> businessFeedbackList;
    @OneToMany(mappedBy = "biznesId")
    @JsonIgnore
    private List<Porosia> porosiaList;
    
    
    /**
     * Login Entity
     */
    @JoinColumn(name = "LoginEntityId", referencedColumnName = "LoginId")
    @ManyToOne
    @Cascade({CascadeType.ALL})
    private LoginEntity loginEntityId;
//    @Column(name = "LoginEntityId", insertable = false, updatable = false)
//    @JsonProperty(value = "loginEntityId", access = JsonProperty.Access.READ_ONLY)
//    private Integer loginEntityFk;

    public Biznesi() {
    }

    public Biznesi(Integer biznesId) {
        this.biznesId = biznesId;
    }

    public Biznesi(Integer biznesId, String emri) {
        this.biznesId = biznesId;
        this.emri = emri;
    }

    public Integer getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Integer biznesId) {
        this.biznesId = biznesId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public Date getOraHapes() {
        return oraHapes;
    }

    public void setOraHapes(Date oraHapes) {
        this.oraHapes = oraHapes;
    }

    public Date getOraMbylljes() {
        return oraMbylljes;
    }

    public void setOraMbylljes(Date oraMbylljes) {
        this.oraMbylljes = oraMbylljes;
    }

    @XmlTransient
    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @XmlTransient
    public List<Produkti> getProduktiList() {
        return produktiList;
    }

    public void setProduktiList(List<Produkti> produktiList) {
        this.produktiList = produktiList;
    }

    @XmlTransient
    public List<Adresa> getAdresaList() {
        return adresaList;
    }

    public void setAdresaList(List<Adresa> adresaList) {
        this.adresaList = adresaList;
    }

    @XmlTransient
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @XmlTransient
    public List<Telefoni> getTelefoniList() {
        return telefoniList;
    }

    public void setTelefoniList(List<Telefoni> telefoniList) {
        this.telefoniList = telefoniList;
    }

    @XmlTransient
    public List<Reklama> getReklamaList() {
        return reklamaList;
    }

    public void setReklamaList(List<Reklama> reklamaList) {
        this.reklamaList = reklamaList;
    }

    public LoginEntity getLoginEntityId() {
        return loginEntityId;
    }

    public void setLoginEntityId(LoginEntity loginEntityId) {
        this.loginEntityId = loginEntityId;
    }

    @XmlTransient
    public List<BusinessFeedback> getBusinessFeedbackList() {
        return businessFeedbackList;
    }

    public void setBusinessFeedbackList(List<BusinessFeedback> businessFeedbackList) {
        this.businessFeedbackList = businessFeedbackList;
    }

    @XmlTransient
    public List<Porosia> getPorosiaList() {
        return porosiaList;
    }

    public void setPorosiaList(List<Porosia> porosiaList) {
        this.porosiaList = porosiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (biznesId != null ? biznesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biznesi)) {
            return false;
        }
        Biznesi other = (Biznesi) object;
        if ((this.biznesId == null && other.biznesId != null) || (this.biznesId != null && !this.biznesId.equals(other.biznesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Biznesi[ biznesId=" + biznesId + " ]";
    }
    
}
