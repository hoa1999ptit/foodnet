/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Post implements Entity {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer postId;
    private String title;
    private String content;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PostLike> postLikeList;
    @JsonProperty(value = "biznesId", access = JsonProperty.Access.WRITE_ONLY)
    private Integer biznesId;
//    @JsonProperty(value = "biznesId", access = JsonProperty.Access.WRITE_ONLY)
//    private Integer biznesIdFk;
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
    
    public Post(String title, String content) {
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

    
    public List<PostLike> getPostLikeList() {
        return postLikeList;
    }

    public void setPostLikeList(List<PostLike> postLikeList) {
        this.postLikeList = postLikeList;
    }

    public Integer getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Integer biznesId) {
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

    
    public Collection<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    
}
