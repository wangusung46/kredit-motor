package kreditmotor.model.angsur;

import koneksi.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class AngsurJdbcImplement implements AngsurJdbc {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(AngsurJdbcImplement.class);

    public AngsurJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Angsur> selectAll() {
        List<Angsur> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM angsur";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Angsur angsur = new Angsur();
                angsur.setId(resultSet.getLong("id"));                
                angsur.setIdKredit(resultSet.getLong("id_kredit"));
                angsur.setTanggal(resultSet.getDate("tanggal"));                         
                response.add(angsur);
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
    public Angsur select(Long request) {
        logger.debug(request.toString());
        Angsur response = new Angsur();
        try {
            sql = "select * from angsur where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));
                response.setIdKredit(resultSet.getLong("id_kredit"));
                response.setTanggal(resultSet.getDate("tanggal"));              
            }
            logger.debug(response.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public void insert(Angsur request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO angsur (id_kredit, tanggal) VALUES(?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request.getIdKredit());
            preparedStatement.setDate(2, new java.sql.Date(request.getTanggal().getTime()));    
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Angsur request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE angsur SET id_kredit=?, tanggal=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request.getIdKredit());
            preparedStatement.setDate(2, new java.sql.Date(request.getTanggal().getTime()));       
            preparedStatement.setLong(4, request.getId());
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
            sql = "DELETE FROM angsur WHERE id=?;";
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