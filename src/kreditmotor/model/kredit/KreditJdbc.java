package kreditmotor.model.kredit;

import java.util.List;

public interface KreditJdbc {

    public List<Kredit> selectAll();
    
    public Kredit select(Long request);

    public void insert(Kredit request);

    public void update(Kredit request);
    
    public void delete(Long request);

}
