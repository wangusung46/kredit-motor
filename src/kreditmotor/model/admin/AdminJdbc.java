package kreditmotor.model.admin;

public interface AdminJdbc {
    
    public Boolean login(String user, String password);

    public void insert(Admin request);

    public String selectRole(String text);
    
}
