package kreditmotor.model.angsur;

import java.util.Date;


public class Angsur {
    
    private Long id;
    private Long idKredit;
    private Date tanggal;

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

    @Override
    public String toString() {
        return "Angsur{" + "id=" + id + ", idKredit=" + idKredit + ", tanggal=" + tanggal + '}';
    }
    
}
