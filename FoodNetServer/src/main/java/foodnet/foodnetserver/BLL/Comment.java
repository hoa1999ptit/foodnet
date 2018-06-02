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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findByCommentId", query = "SELECT c FROM Comment c WHERE c.commentId = :commentId")
    , @NamedQuery(name = "Comment.findByCreateAt", query = "SELECT c FROM Comment c WHERE c.createAt = :createAt")})
public class Comment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @Basic(optional = false)
    @Lob
    @Column(name = "Content")
    private String content;
    @Column(name = "CreateAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    @JsonIgnoreProperties({"adresaList", "telefoniList", "loginEntityId", "favoriteList"})
    private Useri userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "PostId", referencedColumnName = "PostId")
    @ManyToOne
    private Post postId;


    public Comment() {
    }

    public Comment(Integer commentId) {
        this.commentId = commentId;
    }

    public Comment(Integer commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Comment[ commentId=" + commentId + " ]";
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }
    
}
