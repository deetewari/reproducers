package com.ezeegro.adrian.issue.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;


@Entity
@Table(name = "SOHD")//, schema = "OEF")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@SecondaryTable(name = "SOHC", pkJoinColumns = @PrimaryKeyJoinColumn(name = "HCSO#", referencedColumnName = "HDSO#"))
@XmlRootElement
public class SalesOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static SimpleDateFormat MMDD = new SimpleDateFormat("MMdd");
    private static SimpleDateFormat YY = new SimpleDateFormat("yy");
    
    @Transient
    private int expandLevel = 1;
    public static final int EXPAND_INFO = 1;
    public static final int EXPAND_SHIP = 2;
    public static final int EXPAND_ACCT = 3;
    public static final int EXPAND_INSTALLER = 3;
    public static final int EXPAND_CUSTOMER = 3;
    public static final int EXPAND_ITEMS = 4;

    
    @Column(name="HCSO#", table = "SOHC")
    private String hcso;
    
    @Column(name = "HCUSER", table = "SOHC")
    private String user = " ";
    @Column(name = "HCHCM2", table = "SOHC")
    private String attn = " ";

    public SalesOrder() {
        super(); //temp for placement
    }
    
    public SalesOrder(BigDecimal salesOrderNum) {
        this.salesOrderNum = salesOrderNum;
    }

//- Class Elements for JAXB and JPA

    @Id
    @Column(name = "HDSO#", precision = 6, scale = 0, nullable = false)
    private BigDecimal salesOrderNum = BigDecimal.ZERO;

    @Column(name = "HDCUST", precision = 5, scale = 0, nullable = false, insertable=false, updatable=false)
    private BigDecimal customerNum = BigDecimal.ZERO;

    @Column(name = "HDYEAR", precision = 4, scale = 0, nullable = false)
    private BigDecimal pricingYear = BigDecimal.ZERO;

    @Column(name = "HDRLSO", precision = 6, scale = 0, nullable = false)
    private BigDecimal relatedSalesOrderNum = BigDecimal.ZERO;

//Associations
//    @OneToOne(fetch= FetchType.LAZY) //only get this when requested
//    @JoinColumn(name="HDCUST", referencedColumnName="CFCUS#")
//    @JoinFetch(JoinFetchType.OUTER)
//    private Customer customer;
    
    @OneToMany(mappedBy="order", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    //@JoinFetch
    @BatchFetch(BatchFetchType.JOIN)
    private List<AdrianPart> adrianParts;
        
    @XmlAttribute
    public BigDecimal getSalesOrderNum() {
        return salesOrderNum;
    }

    public void setSalesOrderNum(BigDecimal salesOrderNum) {
        this.salesOrderNum = salesOrderNum;
    }
    
    //TODO Customer Object
    public BigDecimal getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(BigDecimal customerNum) {
        this.customerNum = customerNum;
    }

    public BigDecimal getPricingYear() {
        if (expandLevel < EXPAND_ACCT) return null;
        return pricingYear;
    }

    public void setPricingYear(BigDecimal pricingYear) {
        this.pricingYear = pricingYear;
    }

//Associations
    
        //TODO: Needs to be an Order Object
    public BigDecimal getRelatedSalesOrderNum() {
        if (expandLevel < EXPAND_ITEMS) return null;
        return relatedSalesOrderNum;
    }
    
    public void setRelatedSalesOrderNum(BigDecimal relatedSalesOrderNum) {
        this.relatedSalesOrderNum = relatedSalesOrderNum;
    }
    
     //-Sold To Customer
//    public Customer getCustomer() {
//        if (getExpandLevel() < EXPAND_CUSTOMER) return null;
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
    
    public List<AdrianPart> getAdrianParts() {
        if (expandLevel < EXPAND_ITEMS) return null;
        return adrianParts;
    }

    public void setAdrianParts(List<AdrianPart> adrianParts) {
        this.adrianParts = adrianParts;
    }

    public int getExpandLevel() {
        return expandLevel;
    }


    public void setExpandLevel(int level) {
        this.expandLevel = level;
    }
    
}
