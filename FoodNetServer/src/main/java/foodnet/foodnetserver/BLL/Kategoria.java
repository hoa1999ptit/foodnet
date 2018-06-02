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
import javax.persistence.Id;
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
@Table(name = "Kategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategoria.findAll", query = "SELECT k FROM Kategoria k")
    , @NamedQuery(name = "Kategoria.findByEmriKategoris", query = "SELECT k FROM Kategoria k WHERE k.emriKategoris = :emriKategoris")})
public class Kategoria implements Serializable {

    public static final String USHQIM = "Ushqim";
    public static final String PIJE = "Pije";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EmriKategoris")
    private String emriKategoris;

    public Kategoria() {
    }

    public Kategoria(String emriKategoris) {
        this.emriKategoris = emriKategoris;
    }

    public String getEmriKategoris() {
        return emriKategoris;
    }

    public void setEmriKategoris(String emriKategoris) {
        this.emriKategoris = emriKategoris;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emriKategoris != null ? emriKategoris.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategoria)) {
            return false;
        }
        Kategoria other = (Kategoria) object;
        if ((this.emriKategoris == null && other.emriKategoris != null) || (this.emriKategoris != null && !this.emriKategoris.equals(other.emriKategoris))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "foodnet.foodnetserver.BLL.Kategoria[ emriKategoris=" + emriKategoris + " ]";
    }
    
}
