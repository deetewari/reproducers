/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ezeegro.adrian.issue.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author DarylD
 */
public class SalesOrderItemId implements Serializable {
    
    private BigDecimal salesOrderNum;
    private String itemNum;

    public SalesOrderItemId() {}

    public SalesOrderItemId(BigDecimal salesOrderNum, String itemNum) {
        this.salesOrderNum = salesOrderNum;
        this.itemNum = itemNum;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public BigDecimal getSalesOrderNum() {
        return salesOrderNum;
    }

    public void setSalesOrderNum(BigDecimal salesOrderNum) {
        this.salesOrderNum = salesOrderNum;
    }

    @Override
    public String toString() {
        return "SalesOrderRecordId{" + "salesOrderNum=" + salesOrderNum + ", itemNum=" + itemNum + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SalesOrderItemId other = (SalesOrderItemId) obj;
        if (this.salesOrderNum != other.salesOrderNum && (this.salesOrderNum == null || !this.salesOrderNum.equals(other.salesOrderNum))) {
            return false;
        }
        if ((this.itemNum == null) ? (other.itemNum != null) : !this.itemNum.equals(other.itemNum)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.salesOrderNum != null ? this.salesOrderNum.hashCode() : 0);
        hash = 97 * hash + (this.itemNum != null ? this.itemNum.hashCode() : 0);
        return hash;
    }
    
}
