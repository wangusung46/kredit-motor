package kreditmotor.model.angsur;

import java.sql.Date;

public class Angsur {
    
    private Long id;
    private Long idKredit;
    private Date tanggal;
    private Long idSales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdKredit() {
        return idKredit;
    }

    public void setIdKredit(Long idKredit) {
        this.idKredit = idKredit;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Long getIdSales() {
        return idSales;
    }

    public void setIdSales(Long idSales) {
        this.idSales = idSales;
    }

    @Override
    public String toString() {
        return "Angsur{" + "id=" + id + ", idKredit=" + idKredit + ", tanggal=" + tanggal + ", idSales=" + idSales + '}';
    }
    
}
