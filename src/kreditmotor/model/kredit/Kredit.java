package kreditmotor.model.kredit;

import java.sql.Date;

public class Kredit {
    
    private Long id;
    private Long idPembeli;
    private Long idBarang;
    private Date tanggalKredit;
    private Long idSales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(Long idPembeli) {
        this.idPembeli = idPembeli;
    }

    public Long getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Long idBarang) {
        this.idBarang = idBarang;
    }

    public Date getTanggalKredit() {
        return tanggalKredit;
    }

    public void setTanggalKredit(Date tanggalKredit) {
        this.tanggalKredit = tanggalKredit;
    }

    public Long getIdSales() {
        return idSales;
    }

    public void setIdSales(Long idSales) {
        this.idSales = idSales;
    }

    @Override
    public String toString() {
        return "Kredit{" + "id=" + id + ", idPembeli=" + idPembeli + ", idBarang=" + idBarang + ", tanggalKredit=" + tanggalKredit + ", idSales=" + idSales + '}';
    }
    
}
