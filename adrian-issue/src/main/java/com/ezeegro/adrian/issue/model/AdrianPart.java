package com.ezeegro.adrian.issue.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

import org.eclipse.persistence.annotations.JoinFetch;

/**
 *
 * @author DarylD
 */
@Entity
@Table(name = "SOAP")//, schema = "OEF")
@XmlRootElement
@AttributeOverrides({
    @AttributeOverride(name="salesOrderNum", column=@Column(name = "APSO#", precision = 6, scale = 0, nullable = false)),
    @AttributeOverride(name="id", column=@Column(name = "APSOID", length = 3, nullable = false)),
    @AttributeOverride(name="itemNum", column=@Column(name = "APPART", length = 10, nullable = false))
})
public class AdrianPart extends SalesOrderItem {
    
    @ManyToOne
    @JoinColumn(name="APSO#", referencedColumnName="HDSO#", insertable=false, updatable=false)
    @JoinFetch
    protected SalesOrder order;
    
    @Column(name = "APOPTION", length = 10, nullable = false)
    protected String option = " ";
        
    public AdrianPart() {
        super();
    }
    
    public AdrianPart(BigDecimal salesOrderNum,String id) {
        super(salesOrderNum, id);
    }
     
}
