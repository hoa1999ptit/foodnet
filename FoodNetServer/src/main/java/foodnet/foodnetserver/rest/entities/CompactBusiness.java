/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.entities;

import foodnet.foodnetserver.BLL.Biznesi;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class CompactBusiness {
    
    private int biznesiId;
    private String emri;
    private String qyteti;
    
    public static List<CompactBusiness> fromBusinessesList(List<Biznesi> bizneset) {
        List<CompactBusiness> compactList = new ArrayList<>();
        for (Biznesi b: bizneset) {
            if (b.getLoginEntityId() != null)
                compactList.add(new CompactBusiness(
                        b.getBiznesId(), 
                        b.getEmri(),
                        b.getAdresaList().get(0).getQyteti()));
        }
        
        return compactList;
    }
    
    private CompactBusiness() {}

    private CompactBusiness(int biznesiId, String emri, String qyteti) {
        this.biznesiId = biznesiId;
        this.emri = emri;
        this.qyteti = qyteti;
    }

    public int getBiznesiId() {
        return biznesiId;
    }

    public void setBiznesiId(int biznesiId) {
        this.biznesiId = biznesiId;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getQyteti() {
        return qyteti;
    }

    public void setQyteti(String qyteti) {
        this.qyteti = qyteti;
    }
    
    
    
}
