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
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class supplier extends javax.swing.JFrame {

    private void otomatis() {
        //Method menampilkan id_supplier secara otomatis dari tabel supplier
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stm = c.createStatement();
            String sql = "SELECT * FROM supplier ORDER BY  id_supplier DESC";
            ResultSet r = stm.executeQuery(sql);
            if (r.next()) {
                String id = r.getString("id_supplier").substring(2);
                String TR = "" + (Integer.parseInt(id) + 1);
                String nol = "";

                if (TR.length() == 1) {
                    nol = "000";
                } else if (TR.length() == 2) {
                    nol = "00";
                } else if (TR.length() == 3) {
                    nol = "0";
                } else if (TR.length() == 4) {
                    nol = "";
                }
                idtxt.setText("SP" + nol + TR);
            } else {
                idtxt.setText("SP0007");
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void kosong_txt() {
        //method untuk  mengosongkan text dari kolom carisuppliertxt menjadi tidak bernilai (NULL) 
        carisuppliertxt.setText(null);
    }

    public void Kosongkan_form() {
        //method untuk  mengosongkan text dari kolom yang telah didefinisikan menjadi tidak bernilai (NULL) 
        idtxt.setText(null);
        namatxt.setText(null);
        alamatttxt.setText(null);
        telepontxt.setText(null);
    }

    public void tampilkan_data() {
        //Method yang berfungsi untuk menampilkan data dari tabel supplier
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No.");
        model.addColumn("Id_supplier");
        model.addColumn("Nama_supplier");
        model.addColumn("Alamat_supplier");
        model.addColumn("No_telepon");
        String cari = carisuppliertxt.getText(); //pencarian data berdasarkan nama supplier
        try {
            int no = 1;
            String sql = "Select * From supplier WHERE Nama_supplier LIKE'%" + cari + "%'";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(3), res.getString(4)});

                tabel_supplier.setModel(model);

            }
        } catch (SQLException e) {
            System.out.println("ERROR :" + e.getMessage());

        }
    }

    public void addsup() {
        //Aksi yang digunakan untuk menambah data ke dalam tabel supplier
        int count = 0, min = 12, max = 13;
        String data = telepontxt.getText();
        data = data.trim();
        if (data.isEmpty()) {
            alertlabel.setText("Kolom Inputan Harus Diisi");
            alertlabel.setVisible(true);
            count += 1;
        } else if (data.length() < min) {
            alertlabel.setText("Jumlah Inputan Minimal " + min + " Karakter");
            alertlabel.setVisible(true);
            count += 1;
        } else if (data.length() > max) {
            alertlabel.setText("Jumlah Inputan Maksimal " + max + " Karakter");
            alertlabel.setVisible(true);
            count += 1;

        } else {
            alertlabel.setVisible(false);

            try {
                String sql = "INSERT INTO supplier VALUES ('" + idtxt.getText() + "','" + namatxt.getText() + "','"
                        + alamatttxt.getText() + "','" + telepontxt.getText() + "')";
                java.sql.Connection con = (Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Simpan Data Berhasil!");

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Simpan Data Gagal");
            }
            Kosongkan_form();
        }
        tampilkan_data();
        otomatis();
    }

    public void updatesup() {
        //Aksi yang digunakan untuk mengupdate data dari tabel supplier
        int count = 0, min = 12, max = 13;
        String data = telepontxt.getText();
        data = data.trim();
        if (data.isEmpty()) {
            alertlabel.setText("Kolom Inputan Harus Diisi");
            alertlabel.setVisible(true);
            count += 1;
        } else if (data.length() < min) {
            alertlabel.setText("Jumlah Inputan Minimal " + min + " Karakter");
            alertlabel.setVisible(true);
            count += 1;
        } else if (data.length() > max) {
            alertlabel.setText("Jumlah Inputan Maksimal " + max + " Karakter");
            alertlabel.setVisible(true);
            count += 1;

        } else {
            alertlabel.setVisible(false);

            try {
                String sql = "INSERT supplier SET ID_Supplier='" + idtxt.getText() + "' ,Nama_supplier='" + namatxt.getText()
                        + "' ,Alamat_supplier='" + alamatttxt.getText() + "' ,No_telepon='" + telepontxt.getText()
                        + "' On Duplicate Key Update ID_Supplier ='" + idtxt.getText() + "' ,Nama_supplier='" + namatxt.getText()
                        + "' ,Alamat_supplier='" + alamatttxt.getText() + "' ,No_telepon='" + telepontxt.getText() + "'";
                java.sql.Connection con = (Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Edit Data Berhasil");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());

            }
            Kosongkan_form();
        }
        tampilkan_data();
    }

    public void deletesup() {
        //Aksi yang digunakan untuk menghapus data dari tabel supplier berdasarkan Id_supplier
        int opt = JOptionPane.showConfirmDialog(this, "Apakah Yakin Ingin Menghapus Data Ini?",
                "Hapus", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            try {
                String sql = "DELETE FROM supplier WHERE Id_supplier ='" + idtxt.getText() + "'";
                java.sql.Connection con = (Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.execute();

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());

            }
        }
        tampilkan_data();
        Kosongkan_form();
        otomatis();
    }

    public supplier() {
        initComponents();
        Kosongkan_form();
        tampilkan_data();
        otomatis();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        roundedPanel1 = new org.hq.RoundedPanel();
        jLabel9 = new javax.swing.JLabel();
        labeltrans7 = new javax.swing.JLabel();
        labeltrans1 = new javax.swing.JLabel();
        labeltrans10 = new javax.swing.JLabel();
        labeltrans11 = new javax.swing.JLabel();
        labeltrans12 = new javax.swing.JLabel();
        labeltrans13 = new javax.swing.JLabel();
        paneldasbor = new org.hq.RoundedPanel();
        paneltransaksi = new org.hq.RoundedPanel();
        panellap = new org.hq.RoundedPanel();
        panelsup = new org.hq.RoundedPanel();
        panelkrwn = new org.hq.RoundedPanel();
        panelbrg = new org.hq.RoundedPanel();
        panellogout = new org.hq.RoundedPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        idtxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        telepontxt = new javax.swing.JTextField();
        alamatttxt = new javax.swing.JTextField();
        namatxt = new javax.swing.JTextField();
        refreshbtn = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        updatebutton = new javax.swing.JButton();
        addbutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_supplier = new javax.swing.JTable();
        alertlabel = new javax.swing.JLabel();
        carisuppliertxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        roundedPanel3 = new org.hq.RoundedPanel();
        roundedPanel4 = new org.hq.RoundedPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 87, 118));
        jPanel1.setPreferredSize(new java.awt.Dimension(181, 635));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setBackground(new java.awt.Color(0, 87, 118));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("MAIN  MENU");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 121, 139, 30));

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);
        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 87, 118));
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\top_menu_48px.png")); // NOI18N
        roundedPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        labeltrans11.setForeground(new java.awt.Color(252, 249, 137));
        labeltrans11.setText("DATA SUPPLIER");
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
        labeltrans12.setForeground(new java.awt.Color(0, 78, 118));
        labeltrans12.setText("DATA KARYAWAN");
        labeltrans12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labeltrans12MouseMoved(evt);
            }
        });
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

        panelsup.setBackground(new java.awt.Color(0, 87, 118));
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

        panelkrwn.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 87, 118));
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Pictures\\logoutnew().png")); // NOI18N
        jLabel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel8MouseMoved(evt);
            }
        });
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        panellogout.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 40, 40));

        roundedPanel1.add(panellogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 40, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 87, 118));
        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\squared_menu_48px.png")); // NOI18N
        roundedPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 138, -1, 40));

        jPanel1.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 160, 470));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\iconnew-min (1).png")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 87, 118));
        jLabel4.setText("DATA SUPPLIER");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 190, 50));

        jPanel3.setBackground(new java.awt.Color(0, 87, 118));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idtxt.setEditable(false);
        idtxt.setBackground(new java.awt.Color(252, 249, 137));
        idtxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idtxt.setForeground(new java.awt.Color(0, 87, 118));
        idtxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 130, 20));

        jLabel11.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ID Supplier");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        jLabel14.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama Supplier");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 20));

        jLabel10.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Alamat");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, 20));

        jLabel16.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("No. Telepon");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, 20));

        telepontxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        telepontxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        jPanel3.add(telepontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 180, 20));

        alamatttxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        alamatttxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        jPanel3.add(alamatttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 180, 20));

        namatxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        namatxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        jPanel3.add(namatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 180, 20));

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
        jPanel3.add(refreshbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 60, 30));

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
        jPanel3.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 80, 30));

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
        jPanel3.add(updatebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 80, 30));

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
        jPanel3.add(addbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 80, 30));

        tabel_supplier.setBackground(new java.awt.Color(252, 249, 137));
        tabel_supplier.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabel_supplier.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabel_supplier.setForeground(new java.awt.Color(0, 87, 118));
        tabel_supplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id_supplier", "Nama_supplier", "Alamat_supplier", "No_telpon"
            }
        ));
        tabel_supplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel_supplier.getTableHeader().setReorderingAllowed(false);
        tabel_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_supplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_supplier);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 700, 270));

        alertlabel.setForeground(new java.awt.Color(252, 249, 137));
        jPanel3.add(alertlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 270, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 720, 530));

        carisuppliertxt.setBackground(new java.awt.Color(0, 87, 118));
        carisuppliertxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        carisuppliertxt.setForeground(new java.awt.Color(252, 249, 137));
        carisuppliertxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        carisuppliertxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        carisuppliertxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                carisuppliertxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                carisuppliertxtFocusLost(evt);
            }
        });
        carisuppliertxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carisuppliertxtMouseClicked(evt);
            }
        });
        carisuppliertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carisuppliertxtActionPerformed(evt);
            }
        });
        carisuppliertxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carisuppliertxtKeyReleased(evt);
            }
        });
        jPanel2.add(carisuppliertxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 150, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\search2.png")); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 50, 40));

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

        jPanel2.add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 720, 10));

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

        jPanel2.add(roundedPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 90, 720, 10));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 740, 630));

        setSize(new java.awt.Dimension(921, 631));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labeltrans7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseClicked

        try {
            Dashboard2 das = new Dashboard2();
            das.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
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

    }//GEN-LAST:event_labeltrans11MouseClicked

    private void labeltrans11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans11MouseEntered

    }//GEN-LAST:event_labeltrans11MouseEntered

    private void labeltrans11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans11MouseExited

    }//GEN-LAST:event_labeltrans11MouseExited

    private void labeltrans12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseClicked
        karyawan2 wan = new karyawan2();
        wan.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labeltrans12MouseClicked

    private void labeltrans12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseEntered
        Color randomColor = new Color(252, 249, 137);
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans12.setForeground(randomColor);
        panelkrwn.setBackground(randomColor2);
    }//GEN-LAST:event_labeltrans12MouseEntered

    private void labeltrans12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseExited
        Color randomColor2 = new Color(0, 87, 118);

        labeltrans12.setForeground(randomColor2);
        panelkrwn.setBackground(Color.white);
    }//GEN-LAST:event_labeltrans12MouseExited

    private void labeltrans13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseClicked

        try {
            barang2 rang = new barang2();
            rang.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(laporan.class.getName()).log(Level.SEVERE, null, ex);
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

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        opsi_login gin = new opsi_login();
        gin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        panellogout.setBackground(Color.red);
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        Color randomColor2 = new Color(0, 87, 118);
        panellogout.setBackground(randomColor2);
    }//GEN-LAST:event_jLabel8MouseExited

    private void carisuppliertxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carisuppliertxtFocusGained

    }//GEN-LAST:event_carisuppliertxtFocusGained

    private void carisuppliertxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carisuppliertxtFocusLost

    }//GEN-LAST:event_carisuppliertxtFocusLost

    private void carisuppliertxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carisuppliertxtMouseClicked

    }//GEN-LAST:event_carisuppliertxtMouseClicked

    private void carisuppliertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carisuppliertxtActionPerformed
        tampilkan_data();
    }//GEN-LAST:event_carisuppliertxtActionPerformed

    private void carisuppliertxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carisuppliertxtKeyReleased
        tampilkan_data();
    }//GEN-LAST:event_carisuppliertxtKeyReleased

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        Kosongkan_form();
        otomatis();
        alertlabel.setVisible(false);
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked
        deletesup();
    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed

    }//GEN-LAST:event_deletebuttonActionPerformed

    private void updatebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatebuttonMouseClicked
        updatesup();
    }//GEN-LAST:event_updatebuttonMouseClicked

    private void updatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebuttonActionPerformed

    }//GEN-LAST:event_updatebuttonActionPerformed

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
        addsup();
    }//GEN-LAST:event_addbuttonActionPerformed

    private void tabel_supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_supplierMouseClicked
        //Aksi yang digunakan untuk menampilkan data dari tabel_supplier ke kolom text yang telah didefinisikan ketika salah satu baris diklik
        int baris = tabel_supplier.rowAtPoint(evt.getPoint());
        String id = tabel_supplier.getValueAt(baris, 1).toString();
        idtxt.setText(id);
        String nama = tabel_supplier.getValueAt(baris, 2).toString();
        namatxt.setText(nama);
        String alamat = tabel_supplier.getValueAt(baris, 3).toString();
        alamatttxt.setText(alamat);
        String telepon = tabel_supplier.getValueAt(baris, 4).toString();
        telepontxt.setText(telepon);
        alertlabel.setVisible(false);
    }//GEN-LAST:event_tabel_supplierMouseClicked

    private void labeltrans7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseMoved
        labeltrans7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans7MouseMoved

    private void labeltrans1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans1MouseMoved
        labeltrans1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans1MouseMoved

    private void labeltrans10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans10MouseMoved
        labeltrans10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans10MouseMoved

    private void labeltrans12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseMoved
        labeltrans12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans12MouseMoved

    private void labeltrans13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseMoved
        labeltrans13.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans13MouseMoved

    private void jLabel8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseMoved
        jLabel8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel8MouseMoved

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
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbutton;
    private javax.swing.JTextField alamatttxt;
    private javax.swing.JLabel alertlabel;
    private javax.swing.JTextField carisuppliertxt;
    private javax.swing.JButton deletebutton;
    private javax.swing.JTextField idtxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labeltrans1;
    private javax.swing.JLabel labeltrans10;
    private javax.swing.JLabel labeltrans11;
    private javax.swing.JLabel labeltrans12;
    private javax.swing.JLabel labeltrans13;
    private javax.swing.JLabel labeltrans7;
    private javax.swing.JTextField namatxt;
    private org.hq.RoundedPanel panelbrg;
    private org.hq.RoundedPanel paneldasbor;
    private org.hq.RoundedPanel panelkrwn;
    private org.hq.RoundedPanel panellap;
    private org.hq.RoundedPanel panellogout;
    private org.hq.RoundedPanel panelsup;
    private org.hq.RoundedPanel paneltransaksi;
    private javax.swing.JButton refreshbtn;
    private org.hq.RoundedPanel roundedPanel1;
    private org.hq.RoundedPanel roundedPanel3;
    private org.hq.RoundedPanel roundedPanel4;
    private javax.swing.JTable tabel_supplier;
    private javax.swing.JTextField telepontxt;
    private javax.swing.JButton updatebutton;
    // End of variables declaration//GEN-END:variables
}
