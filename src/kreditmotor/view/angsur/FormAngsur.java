package kreditmotor.view.angsur;

import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import kreditmotor.model.angsur.Angsur;
import kreditmotor.model.angsur.AngsurJdbc;
import kreditmotor.model.angsur.AngsurJdbcImplement;
import kreditmotor.model.kredit.Kredit;
import kreditmotor.model.kredit.KreditJdbc;
import kreditmotor.model.kredit.KreditJdbcImplement;
import kreditmotor.model.sales.Sales;
import kreditmotor.model.sales.SalesJdbc;
import kreditmotor.model.sales.SalesJdbcImplement;
import kreditmotor.view.menu.FormMenu;

public class FormAngsur extends javax.swing.JFrame {
    
    private final AngsurJdbc angsurJdbc;
    private final KreditJdbc kreditJdbc;
    private final SalesJdbc salesJdbc;
    private Boolean clickTable;
    private DefaultTableModel defaultTableModel;

    public FormAngsur() {
        initComponents();
        angsurJdbc = new AngsurJdbcImplement();
        kreditJdbc = new KreditJdbcImplement();
        salesJdbc = new SalesJdbcImplement();
        initTable();
        loadTable();
        loadComboBoxKredit();
        loadComboBoxSales();
    }
    
    private void initTable() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("Id Kredit");
        defaultTableModel.addColumn("Tanggal");        
        defaultTableModel.addColumn("Id Sales");
        tableAngsuran.setModel(defaultTableModel);
    }
    
    private void loadTable() {
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();
        List<Angsur> responses = angsurJdbc.selectAll();
        if (responses != null) {
            Object[] objects = new Object[10];
            for (Angsur response : responses) {
                objects[0] = response.getId();
                objects[1] = response.getIdKredit();                
                objects[3] = response.getTanggal();
                objects[4] = response.getIdSales();                
                defaultTableModel.addRow(objects);
            }
            clickTable = false;
        }
    }
    
    private void loadComboBoxKredit() {
        List<Kredit> responses = kreditJdbc.selectAll();
        for (Kredit response : responses) {
            cbxIdKredit.addItem(String.valueOf(response.getId()));
        }
    }

    private void loadComboBoxSales() {
        List<Sales> responses = salesJdbc.selectAll();
        for (Sales response : responses) {
            cbxIdSales.addItem(String.valueOf(response.getId()));
        }
    }
    
    private void clickTable() {
        cbxIdKredit.setSelectedItem(defaultTableModel.getValueAt(tableAngsuran.getSelectedRow(), 1).toString());       
        cbxIdSales.setSelectedItem(defaultTableModel.getValueAt(tableAngsuran.getSelectedRow(), 2).toString());        
        clickTable = true;
    }
    
    private void empty() {
        cbxIdKredit.setSelectedIndex(0);       
        cbxIdSales.setSelectedIndex(0);        
    }
    
    private void performSave() {
        if (dateAngsuran.getDate() != null) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to save new data ?", "Info", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                Angsur request = new Angsur();
                request.setId(0L);
                request.setIdKredit(Long.parseLong(cbxIdKredit.getSelectedItem().toString()));                                                          
                request.setIdSales(Long.parseLong(cbxIdSales.getSelectedItem().toString()));                               
                request.setTanggal((Date) dateAngsuran.getDate());               
                angsurJdbc.insert(request);
                loadTable();
                empty();
                JOptionPane.showMessageDialog(null, "Successfully save data", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data not empty", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
     private void performUpdate() {
        if (clickTable) {
            if (dateAngsuran.getDate() != null) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to update data by id " + defaultTableModel.getValueAt(tableAngsuran.getSelectedRow(), 0).toString() + " ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    Angsur request = new Angsur();
                    request.setId(Long.parseLong(defaultTableModel.getValueAt(tableAngsuran.getSelectedRow(), 0).toString()));
                    request.setIdKredit(Long.parseLong(cbxIdKredit.getSelectedItem().toString()));                                  
                    request.setIdSales(Long.parseLong(cbxIdSales.getSelectedItem().toString()));                   
                    request.setTanggal((Date) dateAngsuran.getDate());
                    angsurJdbc.update(request);
                    loadTable();
                    empty();
                    JOptionPane.showMessageDialog(null, "Successfully update data", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data not empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Delete or edit must click table", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
     
    private void performDelete() {
        if (clickTable) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to delete data by id " + defaultTableModel.getValueAt(tableAngsuran.getSelectedRow(), 0).toString() + " ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                angsurJdbc.delete(Long.parseLong(defaultTableModel.getValueAt(tableAngsuran.getSelectedRow(), 0).toString()));
                loadTable();
                empty();
                JOptionPane.showMessageDialog(null, "Successfully delete data", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Delete or edit must click table", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxIdKredit = new javax.swing.JComboBox<>();
        dateAngsuran = new com.toedter.calendar.JDateChooser();
        cbxIdSales = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAngsuran = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORM ANGSURAN");

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Angsuran");

        btnLogout.setBackground(new java.awt.Color(0, 102, 255));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kreditmotor/image/logout.png"))); // NOI18N
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(241, 241, 241)
                .addComponent(btnLogout)
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnLogout))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Id Kredit     :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tanggal      :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Id Sales      :");

        cbxIdKredit.setBackground(new java.awt.Color(204, 204, 204));
        cbxIdKredit.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        cbxIdKredit.setForeground(new java.awt.Color(255, 255, 255));
        cbxIdKredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kredit 1", "Kredit 2" }));

        dateAngsuran.setBackground(new java.awt.Color(204, 204, 204));
        dateAngsuran.setForeground(new java.awt.Color(255, 255, 255));

        cbxIdSales.setBackground(new java.awt.Color(204, 204, 204));
        cbxIdSales.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        cbxIdSales.setForeground(new java.awt.Color(255, 255, 255));
        cbxIdSales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sales 1", "Sales 2" }));

        tableAngsuran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Id Kredit", "Tanggal", "Id Sales"
            }
        ));
        tableAngsuran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAngsuranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAngsuran);

        btnInsert.setBackground(new java.awt.Color(51, 102, 255));
        btnInsert.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnInsert.setForeground(new java.awt.Color(255, 255, 255));
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(51, 102, 255));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kreditmotor/image/delete.png"))); // NOI18N

        btnDelete.setBackground(new java.awt.Color(51, 102, 255));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(51, 102, 255));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kreditmotor/image/clear.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kreditmotor/image/edit.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kreditmotor/image/insert (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxIdKredit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateAngsuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxIdSales, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInsert)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxIdKredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateAngsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxIdSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kreditmotor/image/calendar.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(402, 402, 402))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        performSave();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        performUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        performDelete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        empty();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        new FormMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void tableAngsuranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAngsuranMouseClicked
        clickTable();
    }//GEN-LAST:event_tableAngsuranMouseClicked

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormAngsur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAngsur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAngsur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAngsur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAngsur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbxIdKredit;
    private javax.swing.JComboBox<String> cbxIdSales;
    private com.toedter.calendar.JDateChooser dateAngsuran;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAngsuran;
    // End of variables declaration//GEN-END:variables
}
