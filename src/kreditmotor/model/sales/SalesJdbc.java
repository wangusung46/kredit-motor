package kreditmotor.model.sales;

import java.util.List;

public interface SalesJdbc {

    public List<Sales> selectAll();
    
    public Sales select(Long request);

    public void insert(Sales request);

    public void update(Sales request);
    
    public void delete(Long request);

}
