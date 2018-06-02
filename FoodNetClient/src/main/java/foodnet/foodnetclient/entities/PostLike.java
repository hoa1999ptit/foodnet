/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Kushtrim Hajrizi
 */
 
public class PostLike implements Entity {    

    private Integer postLikeId;
    private Integer postId;
    private Integer userId;

    
//    private Integer postLikeId;
//    @JsonProperty(value = "postId", access = JsonProperty.Access.WRITE_ONLY)
//    private Post postId;
//    @JsonProperty(value = "userId", access = JsonProperty.Access.WRITE_ONLY)
//    private Useri userId;
//    @JsonProperty(value = "postId", access = JsonProperty.Access.READ_ONLY)
//    private Integer postIdFk;
//    @JsonProperty(value = "userId", access = JsonProperty.Access.READ_ONLY)
//    private Integer userIdFk;
    
    public PostLike() {
    }
    
    public PostLike(Integer userId, Integer postId) {
        this.userId = userId;
        this.postId = postId;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
