/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.entities;

/**
 *
 * @author Kushtrim Hajrizi
 */
 
public class Kategoria implements Entity {

    public static final String USHQIM = "Ushqim";
    public static final String PIJE = "Pije";
        
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
        return emriKategoris;
    }
    
}
