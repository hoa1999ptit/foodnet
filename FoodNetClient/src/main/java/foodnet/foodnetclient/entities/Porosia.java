/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Porosia implements Entity {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer porosiaId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biznesi biznesId;
    @JsonIgnoreProperties(
            {"favoriteList", "commentList", "postLikeList", "loginEntityId",
            "softwareFeedbackList", "businessFeedbackList", "porosiaList"})
    private Useri userId;
    private List<PorosiaProdukti> porosiaProduktiList;

    public Porosia() {
        porosiaProduktiList = new ArrayList<>();
    }

    public Porosia(Integer porosiaId) {
        this.porosiaId = porosiaId;
    }

    public Integer getPorosiaId() {
        return porosiaId;
    }

    public void setPorosiaId(Integer porosiaId) {
        this.porosiaId = porosiaId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    
    public List<PorosiaProdukti> getPorosiaProduktiList() {
        return porosiaProduktiList;
    }

    public void setPorosiaProduktiList(List<PorosiaProdukti> porosiaProduktiList) {
        this.porosiaProduktiList = porosiaProduktiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (porosiaId != null ? porosiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porosia)) {
            return false;
        }
        Porosia other = (Porosia) object;
        if ((this.porosiaId == null && other.porosiaId != null) || (this.porosiaId != null && !this.porosiaId.equals(other.porosiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Porosia[ porosiaId=" + porosiaId + " ]";
    }
    
}
