/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "LoginEntity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginEntity.findAll", query = "SELECT l FROM LoginEntity l")
    , @NamedQuery(name = "LoginEntity.findByLoginId", query = "SELECT l FROM LoginEntity l WHERE l.loginId = :loginId")
    , @NamedQuery(name = "LoginEntity.findByUsername", query = "SELECT l FROM LoginEntity l WHERE l.username = :username")
    , @NamedQuery(name = "LoginEntity.findByEmail", query = "SELECT l FROM LoginEntity l WHERE l.email = :email")
    , @NamedQuery(name = "LoginEntity.findByPasswordi", query = "SELECT l FROM LoginEntity l WHERE l.passwordi = :passwordi")})
public class LoginEntity implements Serializable {

    @OneToMany(mappedBy = "loginId")
    @JsonIgnore
    private List<SoftwareFeedback> softwareFeedbackList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LoginId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginId;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "Passwordi")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordi;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "loginEntityId")
    private List<Biznesi> biznesiList;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "loginEntityId")
    private List<Useri> useriList;
    @JsonIgnore
    @OneToMany(mappedBy = "loginEntityId")
    private List<LoginsRoles> loginsRolesList;

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

    @XmlTransient
    public List<Biznesi> getBiznesiList() {
        return biznesiList;
    }

    public void setBiznesiList(List<Biznesi> biznesiList) {
        this.biznesiList = biznesiList;
    }

    @XmlTransient
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

    @XmlTransient
    public List<LoginsRoles> getLoginsRolesList() {
        return loginsRolesList;
    }

    public void setLoginsRolesList(List<LoginsRoles> loginsRolesList) {
        this.loginsRolesList = loginsRolesList;
    }

    @XmlTransient
    public List<SoftwareFeedback> getSoftwareFeedbackList() {
        return softwareFeedbackList;
    }

    public void setSoftwareFeedbackList(List<SoftwareFeedback> softwareFeedbackList) {
        this.softwareFeedbackList = softwareFeedbackList;
    }
    
}
