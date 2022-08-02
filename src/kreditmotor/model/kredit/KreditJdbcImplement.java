package kreditmotor.model.kredit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import koneksi.Conn;
import org.apache.log4j.Logger;

public class KreditJdbcImplement implements KreditJdbc {
    
    private static final Logger logger = Logger.getLogger(KreditJdbcImplement.class);
    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;

    public KreditJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Kredit> selectAll() {
        List<Kredit> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM kredit";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Kredit kredit = new Kredit();
                kredit.setId(resultSet.getLong("id"));                
                kredit.setIdPembeli(resultSet.getLong("id_pembeli"));
                kredit.setIdBarang(resultSet.getLong("id_barang"));
                kredit.setTanggalKredit(resultSet.getDate("tanggal_kredit"));
                kredit.setIdSales(resultSet.getLong("id_sales"));                           
                kredit.setJumlahBulan(resultSet.getLong("jumlah_bulan"));                           
                response.add(kredit);
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
    public Kredit select(Long request) {
        logger.debug(request.toString());
        Kredit response = new Kredit();
        try {
            sql = "select a.*, ROUND((b.harga_jual / a.jumlah_bulan), 0) tenor from kredit a left join motor b on b.id = a.id_barang where a.id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));
                response.setIdPembeli(resultSet.getLong("id_pembeli"));
                response.setIdBarang(resultSet.getLong("id_barang"));
                response.setTanggalKredit(resultSet.getDate("tanggal_kredit"));
                response.setIdSales(resultSet.getLong("id_sales"));        
                response.setJumlahBulan(resultSet.getLong("jumlah_bulan"));            
                response.setTenor(resultSet.getLong("tenor"));            
            }
            logger.debug(response.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public void insert(Kredit request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO kredit (id_pembeli, id_barang, tanggal_kredit, id_sales, jumlah_bulan) VALUES(?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request.getIdPembeli());
            preparedStatement.setLong(2, request.getIdBarang());
            preparedStatement.setDate(3, new java.sql.Date(request.getTanggalKredit().getTime()));
            preparedStatement.setLong(4, request.getIdSales());                  
            preparedStatement.setLong(5, request.getJumlahBulan());                  
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Kredit request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE kredit SET id_pembeli=?, id_barang=?, tanggal_kredit=?, id_sales=?, jumlah_bulan=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request.getIdPembeli());
            preparedStatement.setLong(2, request.getIdBarang());
            preparedStatement.setDate(3, new java.sql.Date(request.getTanggalKredit().getTime()));
            preparedStatement.setLong(4, request.getIdSales());   
            preparedStatement.setLong(5, request.getJumlahBulan());       
            preparedStatement.setLong(6, request.getId());
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
            sql = "DELETE FROM kredit WHERE id=?;";
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