package kreditmotor.model.sales;

public class Sales {
    
    private Long id;
    private String nama;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "Sales{" + "id=" + id + ", nama=" + nama + '}';
    }
    
}
