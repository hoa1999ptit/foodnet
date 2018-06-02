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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
    , @NamedQuery(name = "Post.findByPostId", query = "SELECT p FROM Post p WHERE p.postId = :postId")
    , @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title")
    , @NamedQuery(name = "Post.findByCreateAt", query = "SELECT p FROM Post p WHERE p.createAt = :createAt")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PostId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(name = "Content")
    private String content;
    @Column(name = "CreateAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @OneToMany(mappedBy = "postId")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<PostLike> postLikeList;
    @JoinColumn(name = "BiznesId", referencedColumnName = "BiznesId")
    @ManyToOne
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @Column(name = "BiznesId", insertable = false, updatable = false)
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.READ_ONLY)
    private Integer biznesIdFk;
    @OneToMany(mappedBy = "postId")
    private List<Comment> commentList;
    

    public Post() {
    }

    public Post(Integer postId) {
        this.postId = postId;
    }

    public Post(Integer postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @XmlTransient
    public List<PostLike> getPostLikeList() {
        return postLikeList;
    }

    public void setPostLikeList(List<PostLike> postLikeList) {
        this.postLikeList = postLikeList;
    }

    public Biznesi getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Biznesi biznesId) {
        this.biznesId = biznesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Post[ postId=" + postId + " ]";
    }

    @XmlTransient
    public Collection<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    
}
