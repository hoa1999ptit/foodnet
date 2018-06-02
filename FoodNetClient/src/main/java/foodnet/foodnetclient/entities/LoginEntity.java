/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author Kushtrim Hajrizi
 */
 
public class LoginEntity implements Entity {

    @JsonIgnore
    private List<SoftwareFeedback> softwareFeedbackList;    
    private Integer loginId;
    private String username;
    private String email;
    private String passwordi;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Biznesi> biznesiList;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Useri> useriList;
    //@JsonIgnore
    //private List<LoginsRoles> loginsRolesList;

    public LoginEntity() {
    }

    public LoginEntity(Integer loginId) {
        this.loginId = loginId;
    }

    public LoginEntity(Integer loginId, String username, String email, String passwordi) {
        this.loginId = loginId;
        this.username = username;
        this.email = email;
        this.passwordi = passwordi;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordi() {
        return passwordi;
    }

    public void setPasswordi(String passwordi) {
        this.passwordi = passwordi;
    }

    
    public List<Biznesi> getBiznesiList() {
        return biznesiList;
    }

    public void setBiznesiList(List<Biznesi> biznesiList) {
        this.biznesiList = biznesiList;
    }

    
    public List<Useri> getUseriList() {
        return useriList;
    }

    public void setUseriList(List<Useri> useriList) {
        this.useriList = useriList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginId != null ? loginId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginEntity)) {
            return false;
        }
        LoginEntity other = (LoginEntity) object;
        if ((this.loginId == null && other.loginId != null) || (this.loginId != null && !this.loginId.equals(other.loginId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.LoginEntity[ loginId=" + loginId + " ]";
    }

//    
//    public List<LoginsRoles> getLoginsRolesList() {
//        return loginsRolesList;
//    }
//
//    public void setLoginsRolesList(List<LoginsRoles> loginsRolesList) {
//        this.loginsRolesList = loginsRolesList;
//    }

    
    public List<SoftwareFeedback> getSoftwareFeedbackList() {
        return softwareFeedbackList;
    }

    public void setSoftwareFeedbackList(List<SoftwareFeedback> softwareFeedbackList) {
        this.softwareFeedbackList = softwareFeedbackList;
    }
    
}
