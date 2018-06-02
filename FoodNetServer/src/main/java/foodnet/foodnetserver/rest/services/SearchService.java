/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.rest.services;

import foodnet.foodnetserver.BLL.Search;
import foodnet.foodnetserver.DAL.BiznesiRepository;
import foodnet.foodnetserver.DAL.SearchRepository;
import foodnet.foodnetserver.rest.entities.CompactBusiness;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Service
public class SearchService {
    
    @Autowired
    private SearchRepository searchRepo;
    
    @Autowired
    private BiznesiRepository biznesiRepo;
    
    public List<CompactBusiness> get(String content) {
        if (!content.isEmpty()) {
            Search search = new Search(content);
            searchRepo.saveAndFlush(search);
        }
        
        return CompactBusiness.fromBusinessesList(biznesiRepo.findByNamePattern(content));
    }
}
