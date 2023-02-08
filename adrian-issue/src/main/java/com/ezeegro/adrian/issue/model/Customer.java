package com.ezeegro.adrian.issue.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.text.WordUtils;

/*************************************************************
 * TEXT:        Customer
 * PGM ID:      E_CF<br>
 * <br><br>
 * 
 * @author Cindy
 * 
 * REVISIONS:<br>
 * /A -10/06/22, Cindy Cunningham, Prj 1733.31, Support 'P'reliminary status.
 **************************************************************/
@Entity
@Table(name = "CF")//, schema = "OEF")
@DiscriminatorColumn(name="CFSLS1", discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue("00") //holder as there isn't any 00 customer
@XmlRootElement
public class Customer extends CF implements Serializable {
    
    @Transient //do not send to the database
    protected int expandLevel = 1; //default to return everything
    //Custome expansion levels
    public static final int EXPAND_INFO = 1; //just beyond basic info
    public static final int EXPAND_SALES = 2; //include basic info and sales info
    public static final int EXPAND_ACCT = 3; //include extra and acct info
    public static final int EXPAND_MISC = 4; //include extra, acct, and misc fields. 
    
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="CFPNT", referencedColumnName="CFCUS#")
    private Customer parent;

/****************************************************
 * Expand Level 0
 ****************************************************/    
    @XmlAttribute
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getName() {
        return WordUtils.capitalize(name.toLowerCase(), new char[]{' '});
        //return customerName;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }
/****************************************************
 * Expand Level INFO
 ****************************************************/  
    public String getStatus() {
        if (expandLevel < EXPAND_INFO) return null;
        if (null == status) 
            return INACTIVE;
        else switch (status) {
            case "":
                return ACTIVE;
            case "P":
                return PRELIMINARY;
            default:
                return INACTIVE;
        }
    }

    public void setStatus(String status) {
        switch (status) {
            case ACTIVE:
                this.status = "";
                break;
            case PRELIMINARY:
                this.status = "P";
                break;
            case INACTIVE:
                this.status = "X";
                break;
            default:
                this.status = "X";
        }
    }

//Associations
    @XmlTransient
    public Customer getParent() {
        if (expandLevel < 1) return null;
        return parent;
    }

    public void setParent(Customer parent) {
        this.parent = parent;
    }
    
    public int getExpandLevel() {
        return expandLevel;
    }

    public void setExpandLevel(int level) {
        this.expandLevel = level;
    }

    
}
