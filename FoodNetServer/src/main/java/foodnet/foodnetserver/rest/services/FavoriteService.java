/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Favorite;
import foodnet.foodnetserver.DAL.FavoriteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class FavoriteService {
    
    @Autowired
    private FavoriteRepository favoriteRepo;
    
    public void create(int userId, int businessId) {
        favoriteRepo.saveAndFlush(new Favorite(userId, businessId));
    }
    
    public void delete(int userId, int businessId) {
        Favorite f = favoriteRepo.findFavorite(userId, businessId);
        if (f != null)
            favoriteRepo.deleteById(f.getFavoriteId());
    }
    
    public Favorite getFavorite(int userId, int businessId) {
        return favoriteRepo.findFavorite(userId, businessId);
    }
}
