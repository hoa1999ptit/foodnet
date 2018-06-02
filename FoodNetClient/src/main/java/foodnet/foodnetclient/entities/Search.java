/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author Kushtrim Hajrizi
 */
 
public class Search implements Entity {
        
    @JsonIgnore
    private Integer searchId;
    private String content;

    public Search() {
    }
    
    public Search(String content) {
        this.content = content;
    }

    public Search(Integer searchId) {
        this.searchId = searchId;
    }

    public Search(Integer searchId, String content) {
        this.searchId = searchId;
        this.content = content;
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (searchId != null ? searchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Search)) {
            return false;
        }
        Search other = (Search) object;
        if ((this.searchId == null && other.searchId != null) || (this.searchId != null && !this.searchId.equals(other.searchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Search[ searchId=" + searchId + " ]";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
