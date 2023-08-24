package elli.shop;

import elli.shop.laporan;
import elli.shop.Koneksi;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class laporan_jual extends javax.swing.JFrame {

    public final void tampil_data_main() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No.");
        model.addColumn("No. Transaksi");
        model.addColumn("Id Barang");
        model.addColumn("Nama barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga Jual");
        model.addColumn("Total Diskon");
        model.addColumn("Total Harga");
        model.addColumn("Jenis Pembayaran");
        model.addColumn("Tanggal");

        try {
            int no = 1;
            String sql = "Select * From laporan_jual";
            Connection con = (Connection) Koneksi.configDB();
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3),
                    res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8),
                    res.getString(9)});

                tabelharian.setModel(model);
            }
        } catch (SQLException e) {
            System.out.println("ERROR :" + e.getMessage());

        }
    }

    public final void tampil_data(String d1, String d2) throws SQLException {
        //Menampilkan data dari database laporan_beli ke tabel laporan harian (pembelian)
        Connection con = (Connection) Koneksi.configDB();
        PreparedStatement stm;
        ResultSet res;

        try {

            if (d1.equals("") || d2.equals("")) {
                stm = con.prepareStatement("SELECT * FROM `laporan_jual`");
            } else {
                stm = con.prepareStatement("SELECT * FROM `laporan_jual` WHERE `Tanggal` BETWEEN ? AND ?");
                stm.setString(1, d1);
                stm.setString(2, d2);
            }
            res = stm.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tabelharian.getModel();
            Object[] row;

            while (res.next()) {
                row = new Object[9];

                row[0] = res.getString(1);
                row[1] = res.getString(2);
                row[2] = res.getString(3);
                row[3] = res.getInt(4);
                row[4] = res.getInt(5);
                row[5] = res.getInt(6);
                row[6] = res.getInt(7);
                row[7] = res.getString(8);
                row[8] = res.getDate(9);
                model.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void caribrg() {
        try {
            tabelharian.setModel(new DefaultTableModel(null, new Object[]{"No_Transaksi", "ID_Barang", "Nama_Barang", "Jumlah", "Harga Jual",
                "Total Diskon", "Total Harga", "Jenis_Pembayaran", "Tanggal"}));
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = date.format(tanggalan.getDate());
            String date2 = date.format(tanggalan1.getDate());

            tampil_data(date1, date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public laporan_jual() {
        initComponents();
        tampil_data_main();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        backbutton = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelharian = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        notranstxt = new javax.swing.JTextField();
        idbrgtxt = new javax.swing.JTextField();
        namatxt = new javax.swing.JTextField();
        jumlahtxt = new javax.swing.JTextField();
        hjtxt = new javax.swing.JTextField();
        totaltxt = new javax.swing.JTextField();
        jenistxt = new javax.swing.JTextField();
        tgltxt = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tanggalan1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        tanggalan = new com.toedter.calendar.JDateChooser();

        jScrollPane3.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 87, 118));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(252, 249, 137));
        jLabel29.setText("LAPORAN PENJUALAN");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 340, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, -1));

        backbutton.setBackground(new java.awt.Color(0, 0, 0));
        backbutton.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
        backbutton.setForeground(new java.awt.Color(255, 255, 255));
        backbutton.setText("«");
        backbutton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                backbuttonMouseMoved(evt);
            }
        });
        backbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backbuttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backbuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backbuttonMouseExited(evt);
            }
        });
        jPanel1.add(backbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 30, 30));

        tabelharian.setBackground(new java.awt.Color(252, 249, 137));
        tabelharian.setForeground(new java.awt.Color(0, 87, 118));
        tabelharian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Transaksi", "Id Barang", "Nama Barang", "Jumlah", "Harga Jual", "Total", "Jenis Pembayaran", "Tanggal"
            }
        ));
        tabelharian.getTableHeader().setReorderingAllowed(false);
        tabelharian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelharianMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelharian);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 1010, 420));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, -1));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 10, 560));
        jPanel1.add(notranstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 270, -1, -1));
        jPanel1.add(idbrgtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 250, -1, -1));

        namatxt.setText("jTextField1");
        jPanel1.add(namatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, -1, -1));

        jumlahtxt.setText("jTextField1");
        jPanel1.add(jumlahtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 280, -1, -1));

        hjtxt.setText("jTextField1");
        hjtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hjtxtActionPerformed(evt);
            }
        });
        jPanel1.add(hjtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, -1, -1));

        totaltxt.setText("jTextField1");
        jPanel1.add(totaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 260, -1, -1));

        jenistxt.setText("jTextField1");
        jPanel1.add(jenistxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 250, -1, -1));

        tgltxt.setText("jTextField1");
        jPanel1.add(tgltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 240, 80, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(252, 249, 137));
        jButton2.setText("Cari");
        jButton2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton2MouseMoved(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 60, 30));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Batal");
        jButton3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton3MouseMoved(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 70, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(252, 249, 137));
        jLabel1.setText("Tanggal Akhir");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(252, 249, 137));
        jLabel2.setText("Tanggal Awal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));
        jPanel1.add(tanggalan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 160, 30));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 650, 530));
        jPanel1.add(tanggalan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 160, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 630));

        setSize(new java.awt.Dimension(1100, 627));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseClicked
        laporan board = new laporan();
        board.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_backbuttonMouseClicked

    private void backbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseEntered
        // TODO add your handling code here:
        backbutton.setForeground(Color.YELLOW);
    }//GEN-LAST:event_backbuttonMouseEntered

    private void backbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseExited
        // TODO add your handling code here:
        backbutton.setForeground(Color.WHITE);
    }//GEN-LAST:event_backbuttonMouseExited

    private void tabelharianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelharianMouseClicked

    }//GEN-LAST:event_tabelharianMouseClicked

    private void hjtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hjtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hjtxtActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        caribrg();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tanggalan.setDate(null);
        tanggalan1.setDate(null);
        tampil_data_main();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseMoved
        jButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton2MouseMoved

    private void jButton3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseMoved
        jButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton3MouseMoved

    private void backbuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseMoved
        backbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_backbuttonMouseMoved

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(laporan_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan_jual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backbutton;
    private javax.swing.JTextField hjtxt;
    private javax.swing.JTextField idbrgtxt;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField jenistxt;
    private javax.swing.JTextField jumlahtxt;
    private javax.swing.JTextField namatxt;
    private javax.swing.JTextField notranstxt;
    private javax.swing.JTable tabelharian;
    private com.toedter.calendar.JDateChooser tanggalan;
    private com.toedter.calendar.JDateChooser tanggalan1;
    private javax.swing.JTextField tgltxt;
    private javax.swing.JTextField totaltxt;
    // End of variables declaration//GEN-END:variables
}
