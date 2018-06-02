/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "Search")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Search.findAll", query = "SELECT s FROM Search s")
    , @NamedQuery(name = "Search.findBySearchId", query = "SELECT s FROM Search s WHERE s.searchId = :searchId")})
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SearchId")
    private Integer searchId;
    
    @Basic(optional = false)
    @Lob
    @Column(name = "Content")
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
