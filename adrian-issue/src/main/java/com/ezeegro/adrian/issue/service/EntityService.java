
package com.ezeegro.adrian.issue.service;

import com.ezeegro.adrian.issue.model.SalesOrder;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author DeependraTewari
 */
@Stateless
@Path("so")
public class EntityService {

    @PersistenceContext(unitName = "testpool")
    EntityManager em;
    
    @GET
    @Path("{id}")
    public String findSalesOrder(@PathParam("id") int id) {
       SalesOrder so = em.find(SalesOrder.class, new BigDecimal(id));
       return so == null ? "NOT _FOUND" : so.toString();
    } 
    
}
