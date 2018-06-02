/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Comment implements Entity {
    
    private Integer commentId;
    private String content;
    private Date createAt;
    @JsonIgnoreProperties(value = {"adresaList", "telefoniList", "loginEntityId", "favoriteList"}, allowSetters = true)
    @JsonProperty(value = "userId", access = JsonProperty.Access.WRITE_ONLY)
    private Useri userId;
    @JsonProperty(value = "userId", access = JsonProperty.Access.READ_ONLY)
    private Integer userIdInteger;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Post postId;
    
    public Comment() {
    }
    
    public Comment(String content) {
        this.content = content;
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

    public Integer getUserIdInteger() {
        return userIdInteger;
    }

    public void setUserIdInteger(Integer userIdInteger) {
        this.userIdInteger = userIdInteger;
    }
    
}
