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
public class Favorite implements Entity {

    private Integer favoriteId;
    private Integer biznesId;
    private Integer userId;
    public Favorite() {
    }

    public Favorite(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }
    
    public Favorite(Integer userId, Integer biznesId) {
        this.userId = userId;
        this.biznesId = biznesId;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getBiznesId() {
        return biznesId;
    }

    public void setBiznesId(Integer biznesId) {
        this.biznesId = biznesId;
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
