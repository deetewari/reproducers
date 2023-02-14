
package com.ezeegro.adrian.issue.service;

import com.ezeegro.adrian.issue.model.SalesOrder;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.math.BigDecimal;



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
