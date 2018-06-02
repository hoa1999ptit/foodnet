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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Entity
@Table(name = "LoginsRoles", catalog = "FoodNet", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginsRoles.findAll", query = "SELECT l FROM LoginsRoles l")
    , @NamedQuery(name = "LoginsRoles.findByLoginsRolesId", query = "SELECT l FROM LoginsRoles l WHERE l.loginsRolesId = :loginsRolesId")})
public class LoginsRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LoginsRolesId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginsRolesId;
    @JoinColumn(name = "LoginEntityId", referencedColumnName = "LoginId")
    @ManyToOne
    private LoginEntity loginEntityId;
    @JoinColumn(name = "RoleId", referencedColumnName = "RoleId")
    @ManyToOne
    private Roles roleId;

    public LoginsRoles() {
    }

    public LoginsRoles(Integer loginsRolesId) {
        this.loginsRolesId = loginsRolesId;
    }

    public Integer getLoginsRolesId() {
        return loginsRolesId;
    }

    public void setLoginsRolesId(Integer loginsRolesId) {
        this.loginsRolesId = loginsRolesId;
    }

    public LoginEntity getLoginEntityId() {
        return loginEntityId;
    }

    public void setLoginEntityId(LoginEntity loginEntityId) {
        this.loginEntityId = loginEntityId;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginsRolesId != null ? loginsRolesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginsRoles)) {
            return false;
        }
        LoginsRoles other = (LoginsRoles) object;
        if ((this.loginsRolesId == null && other.loginsRolesId != null) || (this.loginsRolesId != null && !this.loginsRolesId.equals(other.loginsRolesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.LoginsRoles[ loginsRolesId=" + loginsRolesId + " ]";
    }
    
}
