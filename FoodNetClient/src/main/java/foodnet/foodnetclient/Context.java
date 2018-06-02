/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient;

import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.Biznesi;
import foodnet.foodnetclient.entities.CompactBusiness;
import foodnet.foodnetclient.entities.Porosia;
import foodnet.foodnetclient.entities.Post;
import foodnet.foodnetclient.entities.Produkti;

/**
 *
 * @author Kushtrim Hajrizi
 */
public class Context {
    
    private AuthEntity authenticatedEntity;
    private Biznesi business;
    private Produkti currentProduct;
    private Porosia order;
    private Post post;
    
    public Context() {
    }
    
    public Context(AuthEntity ae) {
        authenticatedEntity = ae;
    }
    
    public AuthEntity getAuthEntity() {
        return authenticatedEntity;
    }
    
    public void setAuthEntity(AuthEntity ae) {
        authenticatedEntity = ae;
    }
    
    public int getId() {
        if (authenticatedEntity == null)
            return -1;
        return authenticatedEntity.getId();
    }
    
    public void clear() {
        authenticatedEntity = null;
        business = null;
        currentProduct = null;
        order = null;
        post = null;
    }
    
    public void setBusiness(Biznesi business) {
        this.business = business;
    }
    
    public void setBusiness(CompactBusiness cpbusiness) {
        Biznesi b = new Biznesi();
        b.setBiznesId(cpbusiness.getBiznesiId());
        b.setEmri(cpbusiness.getEmri());
        
        this.business = b;
    }
    
    public Biznesi getBusiness() {
        return business;
    }
    
    public int getBusinessId() {
        if (business == null)
            return -1;
        return business.getBiznesId();
    }
    
    
    public void setProduct(Produkti product) {
        this.currentProduct = product;
    }
    
    public Produkti getProduct() {
        return currentProduct;
    }

    public Porosia getOrder() {
        return order;
    }

    public void setOrder(Porosia order) {
        this.order = order;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
}