/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetserver.DAL;

import foodnet.foodnetserver.BLL.BusinessFeedback;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Kushtrim Hajrizi
 */
@Repository
public interface BusinessFeedbackRepository extends JpaRepository<BusinessFeedback, Integer> {
 
    @Query("SELECT b FROM BusinessFeedback b WHERE b.biznesId.biznesId = :biznesId")
    public List<BusinessFeedback> findAllByBusinessId(@Param("biznesId") int biznesId);
    
    public BusinessFeedback findByFeedbackId(@Param("feedbackId") int feedbackId);
}