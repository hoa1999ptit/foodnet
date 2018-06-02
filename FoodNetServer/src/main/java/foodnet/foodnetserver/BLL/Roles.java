/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.BLL;

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
@Table(name = "Roles", catalog = "FoodNet", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByRoleId", query = "SELECT r FROM Roles r WHERE r.roleId = :roleId")
    , @NamedQuery(name = "Roles.findByEmri", query = "SELECT r FROM Roles r WHERE r.emri = :emri")})
public class Roles implements Serializable {

    public static final String BUSINESS = "BUSINESS";
    public static final String USER = "USER";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RoleId")
    private Integer roleId;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @OneToMany(mappedBy = "roleId")
    private List<LoginsRoles> loginsRolesList;

    public Roles() {
    }
    
    public Roles(String emri) {
        this.emri = emri;
    }

    public Roles(Integer roleId) {
        this.roleId = roleId;
    }

    public Roles(Integer roleId, String emri) {
        this.roleId = roleId;
        this.emri = emri;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    @XmlTransient
    public List<LoginsRoles> getLoginsRolesList() {
        return loginsRolesList;
    }

    public void setLoginsRolesList(List<LoginsRoles> loginsRolesList) {
        this.loginsRolesList = loginsRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Roles[ roleId=" + roleId + " ]";
    }
    
}
