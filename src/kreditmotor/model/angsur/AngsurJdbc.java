package kreditmotor.model.angsur;

import java.util.List;

public interface AngsurJdbc {

    public List<Angsur> selectAll();
    
    public Angsur select(Long request);

    public void insert(Angsur request);

    public void update(Angsur request);
    
    public void delete(Long request);

}
