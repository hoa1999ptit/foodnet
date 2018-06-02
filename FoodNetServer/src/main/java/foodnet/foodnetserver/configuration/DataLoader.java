/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.configuration;

import foodnet.foodnetserver.BLL.Kategoria;
import foodnet.foodnetserver.BLL.Rating;
import foodnet.foodnetserver.BLL.Roles;
import foodnet.foodnetserver.DAL.KategoriaRepository;
import foodnet.foodnetserver.DAL.RatingRepository;
import foodnet.foodnetserver.DAL.RolesRepository;
import foodnet.foodnetserver.rest.services.RaportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Configuration
public class DataLoader {
    
    @Autowired
    private RolesRepository rolesRepo;
    
    @Autowired
    private KategoriaRepository kategoriaRepo;
    
    @Autowired
    private RatingRepository ratingRepo;
    
    @EventListener(ApplicationReadyEvent.class)
    public void config() {
        createRole(Roles.BUSINESS);
        createRole(Roles.USER);
        
        createCategory(Kategoria.PIJE);
        createCategory(Kategoria.USHQIM);
        
        List<String> meanings = Rating.getRatingMeanings();
        for (int i = 0; i < meanings.size(); i++) {
            createRating(i+1, meanings.get(i));
        }
        
//        createRating(1, Rating.ONE);
//        createRating(2, Rating.TWO);
//        createRating(3, Rating.THREE);
//        createRating(4, Rating.FOUR);
//        createRating(5, Rating.FIVE);
        testing();
    }
    
    private void createRole(String role) {
        Roles r = rolesRepo.findByEmri(role);
        if (r == null)
            rolesRepo.saveAndFlush(new Roles(role));
    }
    
    private void createCategory(String category) {
        Kategoria k = kategoriaRepo.findByEmriKategoris(category);
        if (k == null)
            kategoriaRepo.saveAndFlush(new Kategoria(category));
    }
    
    public void createRating(int id, String meaning) {
        Rating r = ratingRepo.findByRate(id);
        if (r == null)
            ratingRepo.saveAndFlush(new Rating(id, meaning));
    }
    
    @Autowired
    private RaportsService raportsService;
    
    public void testing() {
        
        raportsService.getRaportFile("AllBusinesses");
    }
}
