package com.ezeegro.adrian.issue.model;

import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;


//@Entity
//@Table(name = "SOPart")//, schema = "OEF")
//@XmlRootElement
@MappedSuperclass
//@IdClass(value=SalesOrderRecordId.class)
@IdClass(value=SalesOrderItemId.class)
public class SalesOrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static SimpleDateFormat MMDD = new SimpleDateFormat("MMdd");
    private static SimpleDateFormat YY = new SimpleDateFormat("yy");
    
    @Transient
    private int expandLevel = 2;

    public SalesOrderItem() {
        expandLevel = Integer.MAX_VALUE;
    }
    
    public SalesOrderItem(BigDecimal sorderNum, String id) {
        this.salesOrderNum = sorderNum;
        this.id = id;
    }
    
//- Class Elements for JAXB and JPA

    @Id
    //@Column(name = "APSO#", precision = 6, scale = 0, nullable = false)
    protected BigDecimal salesOrderNum = BigDecimal.ZERO;

//    @Id
    //@Column(name = "APSOID", length = 3, nullable = false)
    protected String id = " ";

    @Id
    //@Column(name = "APPART", length = 10, nullable = false)
    protected String itemNum = " ";


    public int getExpandLevel() {
        return expandLevel;
    }


    public void setExpandLevel(int level) {
        this.expandLevel = level;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.salesOrderNum != null ? this.salesOrderNum.hashCode() : 0);
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SalesOrderItem other = (SalesOrderItem) obj;
        if (this.salesOrderNum != other.salesOrderNum && (this.salesOrderNum == null || !this.salesOrderNum.equals(other.salesOrderNum))) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "SalesOrderItem{" + "expandLevel=" + expandLevel + ", salesOrderNum=" + salesOrderNum + ", id=" + id + ", dltCde=" + dltCde + ", shipCode=" + shipCode + ", typeCode=" + typeCode + ", canceled=" + canceled + ", posted=" + posted + ", holdCode=" + holdCode + ", itemNum=" + itemNum + ", itemExt=" + itemExt + ", quantity=" + quantity + ", quantityShipped=" + quantityShipped + ", balance=" + balance + ", unit=" + unit + ", unitOfMeasure=" + unitOfMeasure + ", shipDateMMDD=" + shipDateMMDD + ", shipDateYY=" + shipDateYY + ", invoiceNum=" + invoiceNum + ", invoiceDateMMDD=" + invoiceDateMMDD + ", invoiceDateYY=" + invoiceDateYY + ", invoiceQuantity=" + invoiceQuantity + ", salesOrderMaterialAmount=" + salesOrderMaterialAmount + ", salesOrderLaborAmount=" + salesOrderLaborAmount + ", shippedDate=" + shippedDate + ", invoiceDate=" + invoiceDate + '}';
//    }
    
}
