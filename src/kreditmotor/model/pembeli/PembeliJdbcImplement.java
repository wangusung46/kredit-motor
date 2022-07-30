package kreditmotor.model.pembeli;

import koneksi.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class PembeliJdbcImplement implements PembeliJdbc {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(PembeliJdbcImplement.class);

    public PembeliJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Pembeli> selectAll() {
        List<Pembeli> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM pembeli";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pembeli pembeli = new Pembeli();
                pembeli.setId(resultSet.getLong("id"));                
                pembeli.setNama(resultSet.getString("nama"));
                pembeli.setJenisKelamin(resultSet.getString("jenis_kelamin"));               
                pembeli.setHp(resultSet.getString("hp"));               
                pembeli.setAlamat(resultSet.getString("alamat"));               
                pembeli.setRt(resultSet.getString("rt"));               
                pembeli.setRw(resultSet.getString("rw"));               
                pembeli.setDesa(resultSet.getString("desa"));               
                pembeli.setKecamatan(resultSet.getString("kecamatan"));               
                pembeli.setKabupaten(resultSet.getString("kabupaten"));               
                pembeli.setProvinsi(resultSet.getString("provinsi"));               
                response.add(pembeli);
            }
            resultSet.close();
            preparedStatement.close();
            logger.debug(response.toString());
            return response;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Pembeli select(Long request) {
        logger.debug(request.toString());
        Pembeli response = new Pembeli();
        try {
            sql = "select * from pembeli where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));                
                response.setNama(resultSet.getString("nama"));
                response.setJenisKelamin(resultSet.getString("jenis_kelamin"));               
                response.setHp(resultSet.getString("hp"));               
                response.setAlamat(resultSet.getString("alamat"));               
                response.setRt(resultSet.getString("rt"));               
                response.setRw(resultSet.getString("rw"));               
                response.setDesa(resultSet.getString("desa"));               
                response.setKecamatan(resultSet.getString("kecamatan"));               
                response.setKabupaten(resultSet.getString("kabupaten"));               
                response.setProvinsi(resultSet.getString("provinsi"));                  
            }
            logger.debug(response.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public void insert(Pembeli request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO pembeli (nama, jenis_kelamin, hp, alamat, rt, rw, desa, kecamatan, kabupaten, provinsi) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getNama());
            preparedStatement.setString(2, request.getJenisKelamin());         
            preparedStatement.setString(3, request.getHp());         
            preparedStatement.setString(4, request.getAlamat());         
            preparedStatement.setString(5, request.getRt());         
            preparedStatement.setString(6, request.getRw());         
            preparedStatement.setString(7, request.getDesa());         
            preparedStatement.setString(8, request.getKecamatan());         
            preparedStatement.setString(9, request.getKabupaten());         
            preparedStatement.setString(10, request.getProvinsi());         
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Pembeli request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE pembeli SET nama=?, jenis_kelamin=?, hp=?, alamat=?, rt=?, rw=?, desa=?, kecamatan=?, kabupaten=?, provinsi=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getNama());
            preparedStatement.setString(2, request.getJenisKelamin());                      
            preparedStatement.setString(3, request.getHp());                      
            preparedStatement.setString(4, request.getAlamat());                      
            preparedStatement.setString(5, request.getRt());                      
            preparedStatement.setString(6, request.getRw());                      
            preparedStatement.setString(7, request.getDesa());                      
            preparedStatement.setString(8, request.getKecamatan());                      
            preparedStatement.setString(9, request.getKabupaten());                      
            preparedStatement.setString(10, request.getProvinsi());                      
            preparedStatement.setLong(11, request.getId());
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long request) {
        logger.debug(request.toString());
        try {
            sql = "DELETE FROM pembeli WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
}