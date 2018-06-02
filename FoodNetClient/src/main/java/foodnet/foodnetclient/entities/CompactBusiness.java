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
public class CompactBusiness implements Entity {
    private Integer biznesiId;
    private Integer userId;
    private String emri;
    private String qyteti;
    
    public CompactBusiness() {}

    public CompactBusiness(Integer biznesiId, Integer userId, String emri, String qyteti) {
        this.biznesiId = biznesiId;
        this.userId = userId;
        this.emri = emri;
        this.qyteti = qyteti;
    }

    public Integer getBiznesiId() {
        return biznesiId;
    }

    public void setBiznesiId(Integer biznesiId) {
        this.biznesiId = biznesiId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
