package com.example.Library.Model;
import jakarta.persistence.Column;
import lombok.Data;
import java.util.Date;

@Data
public class CustomerModel {
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    @Column(nullable=false)
    private Date fromDate;
    @Column(nullable=false)
    private Date toDate;
    @Column(nullable = false)
    private int CustomerId;
}
