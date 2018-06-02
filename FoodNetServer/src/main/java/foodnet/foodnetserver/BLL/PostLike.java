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
@Table(name = "PostLike")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostLike.findAll", query = "SELECT p FROM PostLike p")
    , @NamedQuery(name = "PostLike.findByPostLikeId", query = "SELECT p FROM PostLike p WHERE p.postLikeId = :postLikeId")})
public class PostLike implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PostLikeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postLikeId;
    @JoinColumn(name = "PostId", referencedColumnName = "PostId")
    @ManyToOne
    @JsonProperty(value = "postId", access = JsonProperty.Access.WRITE_ONLY)
    private Post postId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    @JsonProperty(value = "userId", access = JsonProperty.Access.WRITE_ONLY)
    private Useri userId;
    
    @Column(name = "PostId", insertable = false, updatable = false)
    @JsonProperty(value = "postId", access = JsonProperty.Access.READ_ONLY)
    private Integer postIdFk;
    @Column(name = "UserId", insertable = false, updatable = false)
    @JsonProperty(value = "userId", access = JsonProperty.Access.READ_ONLY)
    private Integer userIdFk;
    

    public PostLike() {
    }
    
    public PostLike(Integer userId, Integer postId) {
        this.userId = new Useri(userId);
        this.postId = new Post(postId);
    }

    public PostLike(Integer postLikeId) {
        this.postLikeId = postLikeId;
    }

    public Integer getPostLikeId() {
        return postLikeId;
    }

    public void setPostLikeId(Integer postLikeId) {
        this.postLikeId = postLikeId;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
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
        hash += (postLikeId != null ? postLikeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostLike)) {
            return false;
        }
        PostLike other = (PostLike) object;
        if ((this.postLikeId == null && other.postLikeId != null) || (this.postLikeId != null && !this.postLikeId.equals(other.postLikeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.PostLike[ postLikeId=" + postLikeId + " ]";
    }
    
}
