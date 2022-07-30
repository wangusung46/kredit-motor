package kreditmotor.model.motor;

public class Motor {
    
    private Long id;
    private String merek;
    private String hargaJual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(String hargaJual) {
        this.hargaJual = hargaJual;
    }

    @Override
    public String toString() {
        return "Motor{" + "id=" + id + ", merek=" + merek + ", hargaJual=" + hargaJual + '}';
    }
    
}
