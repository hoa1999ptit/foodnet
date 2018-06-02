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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Favorite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f")
    , @NamedQuery(name = "Favorite.findByFavoriteId", query = "SELECT f FROM Favorite f WHERE f.favoriteId = :favoriteId")})
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FavoriteId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteId;
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    @JsonProperty(value = "userId", access = JsonProperty.Access.WRITE_ONLY)
    private Useri userId;
    
    @Column(name = "BiznesId", insertable = false, updatable = false)
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.READ_ONLY)
    private Integer biznesIdFk;
    @Column(name = "UserId", insertable = false, updatable = false)
    @JsonProperty(value = "userId", access = JsonProperty.Access.READ_ONLY)
    private Integer userIdFk;

    public Favorite() {
    }

    public Favorite(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }
    
    public Favorite(Integer userId, Integer biznesId) {
        this.userId = new Useri(userId);
        this.biznesId = new Biznesi(biznesId);
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
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
        hash += (favoriteId != null ? favoriteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorite)) {
            return false;
        }
        Favorite other = (Favorite) object;
        if ((this.favoriteId == null && other.favoriteId != null) || (this.favoriteId != null && !this.favoriteId.equals(other.favoriteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Favorite[ favoriteId=" + favoriteId + " ]";
    }
    
}
