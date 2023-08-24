/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package elli.shop;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.common.Logger;

public class karyawan2 extends javax.swing.JFrame {

    public void tampilkan_data() {
        //Method yang berfungsi untuk menampilkan data dari tabel karyawan
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No.");
        model.addColumn("NK");
        model.addColumn("ID Akun");
        model.addColumn("Nama Karyawan");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("Jabatan");
        model.addColumn("Gaji");
        String cari = carikrwntxt.getText();
        try {
            int no = 1;
            String sql = "Select * From karyawan WHERE nama_karyawan LIKE'%" + cari + "%'";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3),
                    res.getString(4), res.getString(5), res.getString(6), res.getString(7)});

                tabel_karyawan.setModel(model);

            }
        } catch (SQLException e) {
            System.out.println("ERROR :" + e.getMessage());

        }
    }

    public void Kosongkan_form() {
        //method untuk  mengosongkan text dari kolom yang telah didefinisikan menjadi tidak bernilai (NULL) 
        idtxt1.setText(null);
        namatxt.setText(null);
        kelamintxt.setSelectedItem("Pilih Jenis Kelamin");
        alamattxt.setText(null);
        jabatantxt.setSelectedItem(null);
        gajitxt.setSelectedItem("Pilih Gaji");
    }

    public void otomatis1() {
        //Method menampilkan id karyawan (NK) secara otomatis dari tabel karyawan
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stm = c.createStatement();
            String sql = "SELECT * FROM karyawan ORDER BY NK DESC";
            ResultSet r = stm.executeQuery(sql);
            if (r.next()) {
                String id = r.getString("NK").substring(2);
                String TR = "" + (Integer.parseInt(id) + 1);
                String nol = "";

                if (TR.length() == 1) {
                    nol = "00";
                } else if (TR.length() == 2) {
                    nol = "0";
                } else if (TR.length() == 3) {
                    nol = "";
                }

                idtxt1.setText("KR" + nol + TR);
            } else {
                idtxt1.setText("KR008");
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deletekrywn() {
        //Aksi yang digunakan untuk menghapus data dari tabel karyawan berdasarkan kolom NK
        int opt = JOptionPane.showConfirmDialog(this, "Apakah Yakin Ingin Menghapus Data Ini?",
                "Hapus", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            try {
                String sql = "DELETE FROM karyawan WHERE NK ='" + idtxt1.getText() + "'";
                java.sql.Connection con = (Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        tampilkan_data();
        Kosongkan_form();
        otomatis1();
    }

    public void updatekrywn() {
        //Aksi yang digunakan untuk mengupdate data dari tabel karyawan
        try {
            String sql = "UPDATE karyawan SET NK='" + idtxt1.getText() + "' ,ID_Akun='" + idakuntxt.getSelectedItem()
                    + "' ,nama_karyawan='" + namatxt.getText() + "' ,jenis_kelamin='" + kelamintxt.getSelectedItem()
                    + "' ,alamat_karyawan='" + alamattxt.getText() + "' ,jabatan='" + jabatantxt.getSelectedItem()
                    + "' ,gaji='" + gajitxt.getSelectedItem() + "' On Duplicate Key Update NK='" + idtxt1.getText()
                    + "' ,nama_karyawan='" + namatxt.getText() + "' ,jenis_kelamin='" + kelamintxt.getSelectedItem()
                    + "' ,alamat_karyawan='" + alamattxt.getText() + "' ,jabatan='" + jabatantxt.getSelectedItem() + "' ,gaji='" + gajitxt.getSelectedItem() + "'";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampilkan_data();
        Kosongkan_form();
        otomatis1();
    }

    public void addkrywn() {
        //Fungsi yang digunakan untuk menambah data ke dalam tabel karyawan
        try {
            String sql = "INSERT INTO karyawan VALUES ('" + idtxt1.getText() + "','" + idakuntxt.getSelectedItem()
                    + "','" + namatxt.getText() + "','" + kelamintxt.getSelectedItem() + "','" + alamattxt.getText()
                    + "','" + jabatantxt.getSelectedItem() + "','" + gajitxt.getSelectedItem() + "')";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }
        tampilkan_data();
        Kosongkan_form();
        otomatis1();
    }

    public karyawan2() {
        initComponents();
        tampilkan_data();
        otomatis1();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundedPanel4 = new org.hq.RoundedPanel();
        roundedPanel3 = new org.hq.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        alamattxt = new javax.swing.JTextField();
        kelamintxt = new javax.swing.JComboBox<>();
        namatxt = new javax.swing.JTextField();
        idtxt1 = new javax.swing.JTextField();
        addbutton = new javax.swing.JButton();
        updatebutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        refreshbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_karyawan = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        alamattxt1 = new javax.swing.JTextField();
        kelamintxt1 = new javax.swing.JComboBox<>();
        namatxt1 = new javax.swing.JTextField();
        idtxt2 = new javax.swing.JTextField();
        gajitxt = new javax.swing.JComboBox<>();
        addbutton1 = new javax.swing.JButton();
        updatebutton1 = new javax.swing.JButton();
        deletebutton1 = new javax.swing.JButton();
        refreshbtn1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        idakuntxt = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jabatantxt = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        carikrwntxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        roundedPanel1 = new org.hq.RoundedPanel();
        jLabel11 = new javax.swing.JLabel();
        labeltrans7 = new javax.swing.JLabel();
        labeltrans1 = new javax.swing.JLabel();
        labeltrans10 = new javax.swing.JLabel();
        labeltrans11 = new javax.swing.JLabel();
        labeltrans12 = new javax.swing.JLabel();
        labeltrans13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        paneldasbor = new org.hq.RoundedPanel();
        paneltransaksi = new org.hq.RoundedPanel();
        panellap = new org.hq.RoundedPanel();
        panelsup = new org.hq.RoundedPanel();
        panelkrwn = new org.hq.RoundedPanel();
        panelbrg = new org.hq.RoundedPanel();
        panellogout = new org.hq.RoundedPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel4.setBackground(new java.awt.Color(252, 249, 137));
        roundedPanel4.setRoundBottomLeft(10);
        roundedPanel4.setRoundBottomRight(10);
        roundedPanel4.setRoundTopLeft(10);
        roundedPanel4.setRoundTopRight(10);

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(roundedPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, 720, 10));

        roundedPanel3.setBackground(new java.awt.Color(0, 87, 118));
        roundedPanel3.setRoundBottomLeft(10);
        roundedPanel3.setRoundBottomRight(10);
        roundedPanel3.setRoundTopLeft(10);
        roundedPanel3.setRoundTopRight(10);

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 720, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 87, 118));
        jLabel4.setText("DATA KARYAWAN");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 220, 50));

        jPanel2.setBackground(new java.awt.Color(0, 87, 118));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alamattxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        alamattxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        alamattxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamattxtActionPerformed(evt);
            }
        });
        jPanel2.add(alamattxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 180, 20));

        kelamintxt.setBackground(new java.awt.Color(252, 249, 137));
        kelamintxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kelamintxt.setForeground(new java.awt.Color(0, 87, 118));
        kelamintxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Kelamin", "Laki-laki", "Perempuan", "Lainnya" }));
        kelamintxt.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                kelamintxtMouseMoved(evt);
            }
        });
        jPanel2.add(kelamintxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 140, -1));

        namatxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        namatxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        namatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namatxtActionPerformed(evt);
            }
        });
        jPanel2.add(namatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 180, 20));

        idtxt1.setEditable(false);
        idtxt1.setBackground(new java.awt.Color(252, 249, 137));
        idtxt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idtxt1.setForeground(new java.awt.Color(0, 87, 118));
        idtxt1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        idtxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtxt1ActionPerformed(evt);
            }
        });
        jPanel2.add(idtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 140, 20));

        addbutton.setBackground(new java.awt.Color(252, 249, 137));
        addbutton.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        addbutton.setForeground(new java.awt.Color(0, 87, 118));
        addbutton.setText("Simpan");
        addbutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addbutton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                addbuttonMouseMoved(evt);
            }
        });
        addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(addbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 80, 30));

        updatebutton.setBackground(new java.awt.Color(230, 230, 230));
        updatebutton.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        updatebutton.setText("Edit");
        updatebutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updatebutton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                updatebuttonMouseMoved(evt);
            }
        });
        updatebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatebuttonMouseClicked(evt);
            }
        });
        updatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(updatebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 80, 30));

        deletebutton.setBackground(new java.awt.Color(252, 249, 137));
        deletebutton.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        deletebutton.setForeground(new java.awt.Color(0, 87, 118));
        deletebutton.setText("Hapus");
        deletebutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deletebutton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                deletebuttonMouseMoved(evt);
            }
        });
        deletebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletebuttonMouseClicked(evt);
            }
        });
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 80, 30));

        refreshbtn.setBackground(new java.awt.Color(0, 0, 0));
        refreshbtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refreshbtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshbtn.setText("BATAL");
        refreshbtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        refreshbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                refreshbtnMouseMoved(evt);
            }
        });
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });
        jPanel2.add(refreshbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 60, 30));

        tabel_karyawan.setBackground(new java.awt.Color(252, 249, 137));
        tabel_karyawan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabel_karyawan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabel_karyawan.setForeground(new java.awt.Color(0, 87, 118));
        tabel_karyawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No.", "NK", "Nama Karyawan", "Je", "Title 5", "Title 6"
            }
        ));
        tabel_karyawan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel_karyawan.getTableHeader().setReorderingAllowed(false);
        tabel_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_karyawanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_karyawan);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 700, 270));

        jLabel32.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Gaji");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 60, 20));

        jLabel23.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Jabatan");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 60, 20));

        jLabel33.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Alamat");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, 20));

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Akun");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 60, 20));

        jLabel31.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Nama Karyawan");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 20));

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID Karyawan");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        alamattxt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        alamattxt1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        alamattxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamattxt1ActionPerformed(evt);
            }
        });
        jPanel2.add(alamattxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 180, 20));

        kelamintxt1.setBackground(new java.awt.Color(252, 249, 137));
        kelamintxt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kelamintxt1.setForeground(new java.awt.Color(0, 87, 118));
        kelamintxt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Kelamin", "Laki-laki", "Perempuan", "Lainnya" }));
        jPanel2.add(kelamintxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 140, -1));

        namatxt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        namatxt1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        namatxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namatxt1ActionPerformed(evt);
            }
        });
        jPanel2.add(namatxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 180, 20));

        idtxt2.setEditable(false);
        idtxt2.setBackground(new java.awt.Color(252, 249, 137));
        idtxt2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idtxt2.setForeground(new java.awt.Color(0, 87, 118));
        idtxt2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        idtxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtxt2ActionPerformed(evt);
            }
        });
        jPanel2.add(idtxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 140, 20));

        gajitxt.setBackground(new java.awt.Color(252, 249, 137));
        gajitxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gajitxt.setForeground(new java.awt.Color(0, 87, 118));
        gajitxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Gaji", "250000", "500000", "750000", "1000000", "1500000", "2000000" }));
        gajitxt.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                gajitxtMouseMoved(evt);
            }
        });
        jPanel2.add(gajitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 160, -1));

        addbutton1.setBackground(new java.awt.Color(224, 25, 25));
        addbutton1.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        addbutton1.setForeground(new java.awt.Color(255, 255, 255));
        addbutton1.setText("Simpan");
        addbutton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbutton1ActionPerformed(evt);
            }
        });
        jPanel2.add(addbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 80, 30));

        updatebutton1.setBackground(new java.awt.Color(230, 230, 230));
        updatebutton1.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        updatebutton1.setText("Edit");
        updatebutton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updatebutton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatebutton1MouseClicked(evt);
            }
        });
        updatebutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebutton1ActionPerformed(evt);
            }
        });
        jPanel2.add(updatebutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 80, 30));

        deletebutton1.setBackground(new java.awt.Color(224, 25, 25));
        deletebutton1.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        deletebutton1.setForeground(new java.awt.Color(255, 255, 255));
        deletebutton1.setText("Hapus");
        deletebutton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deletebutton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletebutton1MouseClicked(evt);
            }
        });
        deletebutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebutton1ActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 80, 30));

        refreshbtn1.setBackground(new java.awt.Color(0, 0, 0));
        refreshbtn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refreshbtn1.setForeground(new java.awt.Color(255, 255, 255));
        refreshbtn1.setText("BATAL");
        refreshbtn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        refreshbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtn1ActionPerformed(evt);
            }
        });
        jPanel2.add(refreshbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 60, 30));

        jLabel34.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Gaji");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 60, 20));

        jLabel24.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Jabatan");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 60, 20));

        jLabel35.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Alamat");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, 20));

        jLabel12.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Jenis Kelamin");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, 20));

        jLabel36.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Nama Karyawan");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 20));

        jLabel13.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID Karyawan");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        idakuntxt.setBackground(new java.awt.Color(252, 249, 137));
        idakuntxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idakuntxt.setForeground(new java.awt.Color(0, 87, 118));
        idakuntxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih ID Akun", "A01", "A02", "A03" }));
        jPanel2.add(idakuntxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 110, -1));
        idakuntxt.getAccessibleContext().setAccessibleDescription("");

        jLabel10.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jenis Kelamin");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, 20));

        jabatantxt.setBackground(new java.awt.Color(252, 249, 137));
        jabatantxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jabatantxt.setForeground(new java.awt.Color(0, 87, 118));
        jabatantxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jabatan", "Kasir Toko", "OB" }));
        jabatantxt.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jabatantxtMouseMoved(evt);
            }
        });
        jPanel2.add(jabatantxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 160, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 720, 530));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\search2.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 50, 40));

        carikrwntxt.setBackground(new java.awt.Color(0, 87, 118));
        carikrwntxt.setForeground(new java.awt.Color(252, 249, 137));
        carikrwntxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        carikrwntxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        carikrwntxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                carikrwntxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                carikrwntxtFocusLost(evt);
            }
        });
        carikrwntxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carikrwntxtMouseClicked(evt);
            }
        });
        carikrwntxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carikrwntxtActionPerformed(evt);
            }
        });
        carikrwntxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carikrwntxtKeyReleased(evt);
            }
        });
        jPanel1.add(carikrwntxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 150, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 740, 630));

        jPanel3.setBackground(new java.awt.Color(0, 87, 118));
        jPanel3.setPreferredSize(new java.awt.Dimension(181, 635));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setBackground(new java.awt.Color(0, 87, 118));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("MAIN  MENU");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 121, 139, 30));

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);
        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 87, 118));
        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\top_menu_48px.png")); // NOI18N
        roundedPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labeltrans7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeltrans7.setForeground(new java.awt.Color(0, 87, 118));
        labeltrans7.setText("DASHBOARD");
        labeltrans7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labeltrans7MouseMoved(evt);
            }
        });
        labeltrans7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labeltrans7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labeltrans7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labeltrans7MouseExited(evt);
            }
        });
        roundedPanel1.add(labeltrans7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        labeltrans1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeltrans1.setForeground(new java.awt.Color(0, 78, 118));
        labeltrans1.setText("TRANSAKSI");
        labeltrans1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labeltrans1MouseMoved(evt);
            }
        });
        labeltrans1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labeltrans1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labeltrans1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labeltrans1MouseExited(evt);
            }
        });
        roundedPanel1.add(labeltrans1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        labeltrans10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeltrans10.setForeground(new java.awt.Color(0, 87, 118));
        labeltrans10.setText("LAPORAN");
        labeltrans10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labeltrans10MouseMoved(evt);
            }
        });
        labeltrans10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labeltrans10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labeltrans10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labeltrans10MouseExited(evt);
            }
        });
        roundedPanel1.add(labeltrans10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        labeltrans11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeltrans11.setForeground(new java.awt.Color(0, 78, 118));
        labeltrans11.setText("DATA SUPPLIER");
        labeltrans11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labeltrans11MouseMoved(evt);
            }
        });
        labeltrans11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labeltrans11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labeltrans11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labeltrans11MouseExited(evt);
            }
        });
        roundedPanel1.add(labeltrans11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        labeltrans12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeltrans12.setForeground(new java.awt.Color(252, 249, 137));
        labeltrans12.setText("DATA KARYAWAN");
        labeltrans12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labeltrans12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labeltrans12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labeltrans12MouseExited(evt);
            }
        });
        roundedPanel1.add(labeltrans12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        labeltrans13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeltrans13.setForeground(new java.awt.Color(0, 78, 118));
        labeltrans13.setText("DATA BARANG");
        labeltrans13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labeltrans13MouseMoved(evt);
            }
        });
        labeltrans13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labeltrans13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labeltrans13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labeltrans13MouseExited(evt);
            }
        });
        roundedPanel1.add(labeltrans13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 87, 118));
        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Pictures\\logoutnew().png")); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        roundedPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        paneldasbor.setBackground(new java.awt.Color(255, 255, 255));
        paneldasbor.setRoundBottomRight(20);
        paneldasbor.setRoundTopRight(20);

        javax.swing.GroupLayout paneldasborLayout = new javax.swing.GroupLayout(paneldasbor);
        paneldasbor.setLayout(paneldasborLayout);
        paneldasborLayout.setHorizontalGroup(
            paneldasborLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        paneldasborLayout.setVerticalGroup(
            paneldasborLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        roundedPanel1.add(paneldasbor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 140, 20));

        paneltransaksi.setBackground(new java.awt.Color(255, 255, 255));
        paneltransaksi.setRoundBottomRight(20);
        paneltransaksi.setRoundTopRight(20);

        javax.swing.GroupLayout paneltransaksiLayout = new javax.swing.GroupLayout(paneltransaksi);
        paneltransaksi.setLayout(paneltransaksiLayout);
        paneltransaksiLayout.setHorizontalGroup(
            paneltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        paneltransaksiLayout.setVerticalGroup(
            paneltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        roundedPanel1.add(paneltransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        panellap.setBackground(new java.awt.Color(255, 255, 255));
        panellap.setRoundBottomRight(20);
        panellap.setRoundTopRight(20);

        javax.swing.GroupLayout panellapLayout = new javax.swing.GroupLayout(panellap);
        panellap.setLayout(panellapLayout);
        panellapLayout.setHorizontalGroup(
            panellapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panellapLayout.setVerticalGroup(
            panellapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        roundedPanel1.add(panellap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));

        panelsup.setBackground(new java.awt.Color(255, 255, 255));
        panelsup.setRoundBottomRight(20);
        panelsup.setRoundTopRight(20);

        javax.swing.GroupLayout panelsupLayout = new javax.swing.GroupLayout(panelsup);
        panelsup.setLayout(panelsupLayout);
        panelsupLayout.setHorizontalGroup(
            panelsupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelsupLayout.setVerticalGroup(
            panelsupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        roundedPanel1.add(panelsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        panelkrwn.setBackground(new java.awt.Color(0, 87, 118));
        panelkrwn.setRoundBottomRight(20);
        panelkrwn.setRoundTopRight(20);

        javax.swing.GroupLayout panelkrwnLayout = new javax.swing.GroupLayout(panelkrwn);
        panelkrwn.setLayout(panelkrwnLayout);
        panelkrwnLayout.setHorizontalGroup(
            panelkrwnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelkrwnLayout.setVerticalGroup(
            panelkrwnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        roundedPanel1.add(panelkrwn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        panelbrg.setBackground(new java.awt.Color(255, 255, 255));
        panelbrg.setRoundBottomRight(20);
        panelbrg.setRoundTopRight(20);

        javax.swing.GroupLayout panelbrgLayout = new javax.swing.GroupLayout(panelbrg);
        panelbrg.setLayout(panelbrgLayout);
        panelbrgLayout.setHorizontalGroup(
            panelbrgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelbrgLayout.setVerticalGroup(
            panelbrgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        roundedPanel1.add(panelbrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        panellogout.setBackground(new java.awt.Color(0, 87, 118));
        panellogout.setRoundBottomLeft(20);
        panellogout.setRoundTopRight(20);
        panellogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 87, 118));
        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Pictures\\logoutnew().png")); // NOI18N
        jLabel16.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel16MouseMoved(evt);
            }
        });
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });
        panellogout.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 40, 40));

        roundedPanel1.add(panellogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 40, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 87, 118));
        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\squared_menu_48px.png")); // NOI18N
        roundedPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 138, -1, 40));

        jPanel3.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 160, 470));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\iconnew-min (1).png")); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, -1));

        setSize(new java.awt.Dimension(921, 631));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void alamattxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamattxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamattxtActionPerformed

    private void namatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namatxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namatxtActionPerformed

    private void idtxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtxt1ActionPerformed

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
        addkrywn();
    }//GEN-LAST:event_addbuttonActionPerformed

    private void updatebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatebuttonMouseClicked
        updatekrywn();
    }//GEN-LAST:event_updatebuttonMouseClicked

    private void updatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebuttonActionPerformed

    }//GEN-LAST:event_updatebuttonActionPerformed

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked

    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        deletekrywn();
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        Kosongkan_form();
        otomatis1();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void tabel_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_karyawanMouseClicked
        //Aksi yang digunakan untuk menampilkan data dari tabel_karyawan ke kolom text yang telah didefinisikan ketika salah satu baris diklik
        int baris = tabel_karyawan.rowAtPoint(evt.getPoint());
        String id = tabel_karyawan.getValueAt(baris, 1).toString();
        idtxt1.setText(id);
        String akun = tabel_karyawan.getValueAt(baris, 2).toString();
        idakuntxt.setSelectedItem(akun);
        String nama = tabel_karyawan.getValueAt(baris, 3).toString();
        namatxt.setText(nama);
        String kelamin = tabel_karyawan.getValueAt(baris, 4).toString();
        kelamintxt.setSelectedItem(kelamin);
        String alamat = tabel_karyawan.getValueAt(baris, 5).toString();
        alamattxt.setText(alamat);
        String jabatan = tabel_karyawan.getValueAt(baris, 6).toString();
        jabatantxt.setSelectedItem(jabatan);
        String gaji = tabel_karyawan.getValueAt(baris, 7).toString();
        gajitxt.setSelectedItem(gaji);
    }//GEN-LAST:event_tabel_karyawanMouseClicked

    private void alamattxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamattxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamattxt1ActionPerformed

    private void namatxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namatxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namatxt1ActionPerformed

    private void idtxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtxt2ActionPerformed

    private void addbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbutton1ActionPerformed
        addkrywn();
    }//GEN-LAST:event_addbutton1ActionPerformed

    private void updatebutton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatebutton1MouseClicked
        updatekrywn();
    }//GEN-LAST:event_updatebutton1MouseClicked

    private void updatebutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebutton1ActionPerformed

    }//GEN-LAST:event_updatebutton1ActionPerformed

    private void deletebutton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebutton1MouseClicked

    }//GEN-LAST:event_deletebutton1MouseClicked

    private void deletebutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebutton1ActionPerformed
        deletekrywn();
    }//GEN-LAST:event_deletebutton1ActionPerformed

    private void refreshbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtn1ActionPerformed
        Kosongkan_form();
        otomatis1();
    }//GEN-LAST:event_refreshbtn1ActionPerformed

    private void carikrwntxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carikrwntxtFocusGained

    }//GEN-LAST:event_carikrwntxtFocusGained

    private void carikrwntxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carikrwntxtFocusLost

    }//GEN-LAST:event_carikrwntxtFocusLost

    private void carikrwntxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carikrwntxtMouseClicked

    }//GEN-LAST:event_carikrwntxtMouseClicked

    private void carikrwntxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carikrwntxtActionPerformed
        tampilkan_data();
        carikrwntxt.setText(null);
    }//GEN-LAST:event_carikrwntxtActionPerformed

    private void carikrwntxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carikrwntxtKeyReleased
        tampilkan_data();
    }//GEN-LAST:event_carikrwntxtKeyReleased

    private void labeltrans7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseClicked

        try {
            Dashboard2 das = new Dashboard2();
            das.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(karyawan2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_labeltrans7MouseClicked

    private void labeltrans7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseEntered
        Color randomColor = new Color(252, 249, 137);
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans7.setForeground(randomColor);
        paneldasbor.setBackground(randomColor2);
    }//GEN-LAST:event_labeltrans7MouseEntered

    private void labeltrans7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseExited
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans7.setForeground(randomColor2);
        paneldasbor.setBackground(Color.white);
    }//GEN-LAST:event_labeltrans7MouseExited

    private void labeltrans1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans1MouseClicked
        transaksi2 t = new transaksi2();
        t.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labeltrans1MouseClicked

    private void labeltrans1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans1MouseEntered
        Color randomColor = new Color(252, 249, 137);
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans1.setForeground(randomColor);
        paneltransaksi.setBackground(randomColor2);
    }//GEN-LAST:event_labeltrans1MouseEntered

    private void labeltrans1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans1MouseExited
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans1.setForeground(randomColor2);
        paneltransaksi.setBackground(Color.white);
    }//GEN-LAST:event_labeltrans1MouseExited

    private void labeltrans10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans10MouseClicked
        laporan l = new laporan();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labeltrans10MouseClicked

    private void labeltrans10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans10MouseEntered
        Color randomColor = new Color(252, 249, 137);
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans10.setForeground(randomColor);
        panellap.setBackground(randomColor2);
    }//GEN-LAST:event_labeltrans10MouseEntered

    private void labeltrans10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans10MouseExited
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans10.setForeground(randomColor2);
        panellap.setBackground(Color.white);
    }//GEN-LAST:event_labeltrans10MouseExited

    private void labeltrans11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans11MouseClicked
        supplier plier = new supplier();
        plier.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labeltrans11MouseClicked

    private void labeltrans11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans11MouseEntered
        Color randomColor = new Color(252, 249, 137);
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans11.setForeground(randomColor);
        panelsup.setBackground(randomColor2);
    }//GEN-LAST:event_labeltrans11MouseEntered

    private void labeltrans11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans11MouseExited
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans11.setForeground(randomColor2);
        panelsup.setBackground(Color.white);
    }//GEN-LAST:event_labeltrans11MouseExited

    private void labeltrans12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseClicked

    }//GEN-LAST:event_labeltrans12MouseClicked

    private void labeltrans12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseEntered

    }//GEN-LAST:event_labeltrans12MouseEntered

    private void labeltrans12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseExited

    }//GEN-LAST:event_labeltrans12MouseExited

    private void labeltrans13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseClicked

        try {
            barang2 rang = new barang2();
            rang.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(karyawan2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_labeltrans13MouseClicked

    private void labeltrans13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseEntered
        Color randomColor = new Color(252, 249, 137);
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans13.setForeground(randomColor);
        panelbrg.setBackground(randomColor2);
    }//GEN-LAST:event_labeltrans13MouseEntered

    private void labeltrans13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseExited
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans13.setForeground(randomColor2);
        panelbrg.setBackground(Color.white);
    }//GEN-LAST:event_labeltrans13MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Login2 gin = new Login2();
        gin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        panellogout.setBackground(Color.red);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        Color randomColor2 = new Color(0, 87, 118);
        panellogout.setBackground(randomColor2);
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        opsi_login gin = new opsi_login();
        gin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        panellogout.setBackground(Color.red);
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        Color randomColor2 = new Color(0, 87, 118);
        panellogout.setBackground(randomColor2);
    }//GEN-LAST:event_jLabel16MouseExited

    private void addbuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbuttonMouseMoved
        addbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addbuttonMouseMoved

    private void updatebuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatebuttonMouseMoved
        updatebutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_updatebuttonMouseMoved

    private void deletebuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseMoved
        deletebutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deletebuttonMouseMoved

    private void refreshbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshbtnMouseMoved
        refreshbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_refreshbtnMouseMoved

    private void labeltrans7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseMoved
        labeltrans7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans7MouseMoved

    private void labeltrans1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans1MouseMoved
        labeltrans1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans1MouseMoved

    private void labeltrans10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans10MouseMoved
        labeltrans10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans10MouseMoved

    private void labeltrans11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans11MouseMoved
        labeltrans11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans11MouseMoved

    private void labeltrans13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseMoved
        labeltrans13.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans13MouseMoved

    private void jLabel16MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseMoved
        jLabel16.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel16MouseMoved

    private void kelamintxtMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kelamintxtMouseMoved

    }//GEN-LAST:event_kelamintxtMouseMoved

    private void jabatantxtMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jabatantxtMouseMoved

    }//GEN-LAST:event_jabatantxtMouseMoved

    private void gajitxtMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gajitxtMouseMoved

    }//GEN-LAST:event_gajitxtMouseMoved

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(karyawan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(karyawan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(karyawan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(karyawan2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new karyawan2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbutton;
    private javax.swing.JButton addbutton1;
    private javax.swing.JTextField alamattxt;
    private javax.swing.JTextField alamattxt1;
    private javax.swing.JTextField carikrwntxt;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton deletebutton1;
    private javax.swing.JComboBox<String> gajitxt;
    private javax.swing.JComboBox<String> idakuntxt;
    private javax.swing.JTextField idtxt1;
    private javax.swing.JTextField idtxt2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jabatantxt;
    private javax.swing.JComboBox<String> kelamintxt;
    private javax.swing.JComboBox<String> kelamintxt1;
    private javax.swing.JLabel labeltrans1;
    private javax.swing.JLabel labeltrans10;
    private javax.swing.JLabel labeltrans11;
    private javax.swing.JLabel labeltrans12;
    private javax.swing.JLabel labeltrans13;
    private javax.swing.JLabel labeltrans7;
    private javax.swing.JTextField namatxt;
    private javax.swing.JTextField namatxt1;
    private org.hq.RoundedPanel panelbrg;
    private org.hq.RoundedPanel paneldasbor;
    private org.hq.RoundedPanel panelkrwn;
    private org.hq.RoundedPanel panellap;
    private org.hq.RoundedPanel panellogout;
    private org.hq.RoundedPanel panelsup;
    private org.hq.RoundedPanel paneltransaksi;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JButton refreshbtn1;
    private org.hq.RoundedPanel roundedPanel1;
    private org.hq.RoundedPanel roundedPanel3;
    private org.hq.RoundedPanel roundedPanel4;
    private javax.swing.JTable tabel_karyawan;
    private javax.swing.JButton updatebutton;
    private javax.swing.JButton updatebutton1;
    // End of variables declaration//GEN-END:variables
}
