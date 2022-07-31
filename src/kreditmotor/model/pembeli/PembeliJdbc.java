package kreditmotor.model.pembeli;

import java.util.List;

public interface PembeliJdbc {

    public List<Pembeli> selectAll();
    
    public Pembeli select(Long request);

    public void insert(Pembeli request);

    public void update(Pembeli request);
    
    public void delete(Long request);

}
