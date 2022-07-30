package kreditmotor.model.motor;

import java.util.List;

public interface MotorJdbc {

    public List<Motor> selectAll();
    
    public Motor select(Long request);

    public void insert(Motor request);

    public void update(Motor request);
    
    public void delete(Long request);

}
