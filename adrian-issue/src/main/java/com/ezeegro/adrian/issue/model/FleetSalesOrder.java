/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ezeegro.adrian.issue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * *
 *
 * @author Cindy Cunningham
 *
 * test @SecondaryTable error
 */
@Entity
@Table(name = "SOHDS0")//, schema = "OEF")
@SecondaryTable(name = "SOHC",//, schema = "OEF",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "HCSO#", referencedColumnName = "HDSO#"))
@XmlRootElement(name = "fleetSalesOrder")
public class FleetSalesOrder extends SalesOrder {

    @Column(name="HCSO#", table = "SOHC")
    private String hcso;
    
    @Column(name = "HCUSER", table = "SOHC")
    private String user = " ";
    @Column(name = "HCHCM2", table = "SOHC")
    private String attn = " ";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAttn() {
        return attn;
    }

    public void setAttn(String attn) {
        this.attn = attn;
    }
}
