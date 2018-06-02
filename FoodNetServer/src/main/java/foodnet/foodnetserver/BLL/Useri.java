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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Useri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useri.findAll", query = "SELECT u FROM Useri u")
    , @NamedQuery(name = "Useri.findByUserId", query = "SELECT u FROM Useri u WHERE u.userId = :userId")
    , @NamedQuery(name = "Useri.findByEmri", query = "SELECT u FROM Useri u WHERE u.emri = :emri")
    , @NamedQuery(name = "Useri.findByMbiemri", query = "SELECT u FROM Useri u WHERE u.mbiemri = :mbiemri")})
@FilterDef(name = "compactUser", parameters = {
    @ParamDef(name = "userId", type = "integer"),
    @ParamDef(name = "emri", type = "string"),
    @ParamDef(name = "mbiemri", type = "string")
})
public class Useri implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "UserId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Basic(optional = false)
    @Column(name = "Mbiemri")
    private String mbiemri;
    @OneToMany(mappedBy = "userId")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Favorite> favoriteList;
    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private List<Comment> commentList;
    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private List<PostLike> postLikeList;
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "userId")
    private List<Adresa> adresaList;
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToMany(mappedBy = "userId")
    private List<Telefoni> telefoniList;
    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private List<BusinessFeedback> businessFeedbackList;
    @OneToMany(mappedBy = "userId")
    @JsonIgnore
    private List<Porosia> porosiaList;
       
    /**
     * Login Entity
     */
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JsonProperty(value = "loginEntityId", access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name = "LoginEntityId", referencedColumnName = "LoginId")
    @ManyToOne
    @JsonIgnoreProperties({"biznesiList", "useriList", "softwareFeedbackList"})
    private LoginEntity loginEntityId;
//    @Column(name = "LoginEntityId", insertable = false, updatable = false)
//    @JsonProperty(value = "loginEntityId", access = JsonProperty.Access.READ_ONLY)
//    private Integer loginEntityFk;

    public Useri() {
    }

    public Useri(Integer userId) {
        this.userId = userId;
    }

    public Useri(Integer userId, String emri, String mbiemri) {
        this.userId = userId;
        this.emri = emri;
        this.mbiemri = mbiemri;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    @XmlTransient
    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @XmlTransient
    public List<PostLike> getPostLikeList() {
        return postLikeList;
    }

    public void setPostLikeList(List<PostLike> postLikeList) {
        this.postLikeList = postLikeList;
    }

    @XmlTransient
    public List<Adresa> getAdresaList() {
        return adresaList;
    }

    public void setAdresaList(List<Adresa> adresaList) {
        this.adresaList = adresaList;
    }

    @XmlTransient
    public List<Telefoni> getTelefoniList() {
        return telefoniList;
    }

    public void setTelefoniList(List<Telefoni> telefoniList) {
        this.telefoniList = telefoniList;
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

    public LoginEntity getLoginEntityId() {
        return loginEntityId;
    }

    public void setLoginEntityId(LoginEntity loginEntityId) {
        this.loginEntityId = loginEntityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useri)) {
            return false;
        }
        Useri other = (Useri) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Useri[ userId=" + userId + " ]";
    }
    
}
