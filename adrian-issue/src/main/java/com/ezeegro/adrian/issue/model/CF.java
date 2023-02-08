package com.ezeegro.adrian.issue.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

//MappedSuperlass used to hold the fields of the CF file.
//The Entities that extend this class will make visible the fields it needs and does not need. 
/**
 * TEXT:        CF
 * PGM ID:      E_CF<br>
 * <br><br>
 * 
 * @author Cindy Cunningham
 * 
 * REVISIONS:<br>
 * /A -10/06/22, Cindy Cunningham, Prj 1733.31, Support 'P'reliminary status.
 */
@MappedSuperclass
public abstract class CF implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public final static String INACTIVE = "InActive";
    public final static String ACTIVE = "Active";
    public final static String PRELIMINARY = "Preliminary";

    public CF() {
        super(); //temp for placement
    }

//- Class Elements for JAXB and JPA

    @Id
    @Column(name = "CFCUS#", precision = 5, scale = 0, nullable = false)
    protected BigDecimal id = BigDecimal.ZERO;

    @Column(name = "CFSTAT", length = 1, nullable = false)
    protected String status = " ";

    @Column(name = "CFCNAM", length = 35, nullable = false)
    protected String name = " ";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
