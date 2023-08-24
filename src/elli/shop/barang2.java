/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package elli.shop;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.mysql.cj.protocol.Resultset;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.io.IOException;
import static java.lang.Class.forName;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class barang2 extends javax.swing.JFrame {

    public static Date gettgl(JTable table, int kolom) {
        //Method untuk mmenampilkan tanggal dari JDateChooser 
        JTable tabel = table;
        String tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(), kolom));
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);

        } catch (ParseException ex) {
            Logger.getLogger(barang2.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tanggal;
    }

    public void otomatis() {
        //Method menampilkan id_supplier secara otomatis dari tabel ID_Barang
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stm = c.createStatement();
            String sql = "SELECT * FROM barang ORDER BY ID_Barang DESC";
            ResultSet r = stm.executeQuery(sql);
            if (r.next()) {
                String id = r.getString("ID_Barang").substring(3);
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
                idbrgtxt1.setText("BRG" + nol + TR);
            } else {
                idbrgtxt1.setText("BRG0011");
            }
            r.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void kosong_txt() {
        //method untuk  mengosongkan text dari kolom carisuppliertxt menjadi tidak bernilai (NULL) 
        caribrgtxt1.setText(null);
    }

    public void Kosongkan_form() {
        //method untuk  mengosongkan text dari kolom yang telah didefinisikan menjadi tidak bernilai (NULL) 
        idbrgtxt1.setText(null);
        namatxt.setText(null);
        hargajualtxt.setText(null);
        stoktxt.setText(null);
        tanggalan.setDate(null);
        hbtxt.setText(null);
        bar_read.setIcon(null);
    }

    public void tampilkan_data() {
        //Method yang berfungsi untuk menampilkan data dari tabel barang
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No.");
        model.addColumn("id_Barang");
        model.addColumn("Nama_barang");
        model.addColumn("Harga_jual");
        model.addColumn("Harga_Beli");
        model.addColumn("Stok_barang");
        model.addColumn("Expired");
        String cari = caribrgtxt1.getText(); //pencarian data berdasarkan nama barang
        try {
            int no = 1;
            String sql = "Select * From barang WHERE Nama_barang LIKE'%" + cari + "%'";
            Connection con = (Connection) Koneksi.configDB();
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6)});

                tabel_barang.setModel(model);
            }
        } catch (SQLException e) {
            System.out.println("ERROR :" + e.getMessage());

        }
    }

    public void addbrg() throws Exception {
        //Aksi yang digunakan untuk menambah data ke dalam tabel barang
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(sdf.format(tanggalan.getDate()));
        String id = String.valueOf(idbrgtxt1.getText());
        String kb = String.valueOf(idbrgtxt1);
        try {
            
            String sql = "INSERT INTO barang VALUES ('" + idbrgtxt1.getText() + "','" + namatxt.getText()
                    + "','" + hargajualtxt.getText() + "','" + hbtxt.getText() + "','" + stoktxt.getText() + "','" + tgl + "')";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Simpan Data Berhasil!");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, " Simpan Data Gagal");
        }
        tampilkan_data();
        Kosongkan_form();
        otomatis();

    }

    public void updatebrg() {
        //Aksi yang digunakan untuk mengupdate data dari tabel barang 
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(sdf.format(tanggalan.getDate()));

        try {
            String sql = "INSERT barang SET id_Barang='" + idbrgtxt1.getText() + "' ,nama_Barang='" + namatxt.getText()
                    + "' ,Harga_Jual='" + hargajualtxt.getText() + "' ,Harga_Beli='" + hbtxt.getText()
                    + "' ,Stok_Barang='" + stoktxt.getText() + "' ,Expired='" + tgl + "' On Duplicate Key Update id_Barang ='"
                    + idbrgtxt1.getText() + "' ,nama_Barang='" + namatxt.getText() + "' ,Harga_Jual='" + hargajualtxt.getText()
                    + "' ,Harga_Beli='" + hbtxt.getText() + "' ,Stok_Barang='" + stoktxt.getText()
                    + "' ,Expired='" + tgl + "'";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        }
        tampilkan_data();
        Kosongkan_form();
        otomatis();
    }

    public void deletebrg() {
        //Fungsi untuk menghapus data dari tabel supplier berdasarkan kolom id_Barang
        int opt = JOptionPane.showConfirmDialog(this, "Apakah Yakin Ingin Menghapus Data Ini?", "Hapus", JOptionPane.YES_NO_OPTION);
        if (opt == 0) { // JIka user memilih opsi "YES" maka data akan terhapus dan jika memilih opsi "NO" maka data akan batal terhapus
            try {
                String sql = "DELETE FROM barang WHERE id_Barang ='" + idbrgtxt1.getText() + "'";
                java.sql.Connection con = (Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        tampilkan_data();
        Kosongkan_form();
        otomatis();
    }

    private void newfile() {
        //Kode program untuk menggenerate atau membuat barcode
        Code128Writer writer = new Code128Writer();
        String content = idbrgtxt1.getText();

        try {

            BitMatrix matrix = writer.encode(content, BarcodeFormat.CODE_128, 400, 200);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

            bar_read.setIcon(new ImageIcon(image));

            File outfile = new File("C:\\Users\\ACER\\Documents\\barcodelist" + content + ".png");
            ImageIO.write(toBufferedImage(matrix), "png", outfile);
            System.out.println(image.toString());
            JOptionPane.showMessageDialog(null, "Barcode berhasil di tambahkan ke directory");

        } catch (WriterException | HeadlessException | IOException e) {
        }
    }

    private void showbarcode() {
        //Kode program untuk menggenerate atau membuat barcode
        Code128Writer writer = new Code128Writer();
        String content = idbrgtxt1.getText();
        try {
            BitMatrix matrix = writer.encode(content, BarcodeFormat.CODE_128, 400, 200);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

            bar_read.setIcon(new ImageIcon(image));

        } catch (Exception e) {
        }
    }

    public barang2() throws SQLException {
        initComponents();
        System.out.println("FILE :" + new File("C:\\Users\\ACER\\Documents\\barcodelist").exists());
        tampilkan_data();
        Kosongkan_form();
        otomatis();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        alertlabel = new javax.swing.JLabel();
        namatxt = new javax.swing.JTextField();
        hargajualtxt = new javax.swing.JTextField();
        hbtxt = new javax.swing.JTextField();
        stoktxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        idbrgtxt1 = new javax.swing.JTextField();
        bar_read = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        addbutton = new javax.swing.JButton();
        updatebutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        refreshbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_barang = new javax.swing.JTable();
        barread = new javax.swing.JPanel();
        tanggalan = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        caribrgtxt1 = new javax.swing.JTextField();
        roundedPanel3 = new org.hq.RoundedPanel();
        roundedPanel4 = new org.hq.RoundedPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        roundedPanel1 = new org.hq.RoundedPanel();
        jLabel16 = new javax.swing.JLabel();
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
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 87, 118));
        jLabel4.setText("DATA BARANG");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 180, 50));

        jPanel2.setBackground(new java.awt.Color(0, 87, 118));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alertlabel.setForeground(new java.awt.Color(252, 249, 137));
        jPanel2.add(alertlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 180, 20));

        namatxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        namatxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        jPanel2.add(namatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 180, -1));

        hargajualtxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hargajualtxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        jPanel2.add(hargajualtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 180, -1));

        hbtxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hbtxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(hbtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 180, -1));

        stoktxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        stoktxt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        jPanel2.add(stoktxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 70, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Stok Barang");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, 20));

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Expired");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 50, 20));

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Harga Beli");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, 20));

        jLabel10.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Harga Jual");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, 20));

        jLabel11.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nama Barang");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 20));

        jLabel12.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ID BARANG");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        idbrgtxt1.setEditable(false);
        idbrgtxt1.setBackground(new java.awt.Color(252, 249, 137));
        idbrgtxt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idbrgtxt1.setForeground(new java.awt.Color(0, 87, 118));
        idbrgtxt1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        idbrgtxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idbrgtxt1ActionPerformed(evt);
            }
        });
        jPanel2.add(idbrgtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 130, 20));
        jPanel2.add(bar_read, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 310, 90));

        jLabel13.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Barcode");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 110, 20));

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deletebuttonMouseEntered(evt);
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

        tabel_barang.setAutoCreateRowSorter(true);
        tabel_barang.setBackground(new java.awt.Color(252, 249, 137));
        tabel_barang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabel_barang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabel_barang.setForeground(new java.awt.Color(0, 87, 118));
        tabel_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id_Barang", "Nama_Barang", "Harga_Jual", "Stok_Barang"
            }
        ));
        tabel_barang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel_barang.setGridColor(new java.awt.Color(0, 87, 118));
        tabel_barang.getTableHeader().setReorderingAllowed(false);
        tabel_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_barangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_barangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tabel_barangMouseExited(evt);
            }
        });
        tabel_barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabel_barangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabel_barangKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_barang);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 700, 270));
        jPanel2.add(barread, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 310, 90));
        jPanel2.add(tanggalan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 180, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 720, 540));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\search2.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 50, 40));

        caribrgtxt1.setBackground(new java.awt.Color(0, 87, 118));
        caribrgtxt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        caribrgtxt1.setForeground(new java.awt.Color(252, 249, 137));
        caribrgtxt1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        caribrgtxt1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        caribrgtxt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                caribrgtxt1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                caribrgtxt1FocusLost(evt);
            }
        });
        caribrgtxt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                caribrgtxt1MouseClicked(evt);
            }
        });
        caribrgtxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caribrgtxt1ActionPerformed(evt);
            }
        });
        caribrgtxt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caribrgtxt1KeyReleased(evt);
            }
        });
        jPanel1.add(caribrgtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 150, 30));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 750, 630));

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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 87, 118));
        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\top_menu_48px.png")); // NOI18N
        roundedPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        labeltrans11.setForeground(new java.awt.Color(0, 87, 118));
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
        labeltrans13.setForeground(new java.awt.Color(252, 249, 137));
        labeltrans13.setText("DATA BARANG");
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

        panelbrg.setBackground(new java.awt.Color(0, 87, 118));
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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 87, 118));
        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Pictures\\logoutnew().png")); // NOI18N
        jLabel17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel17MouseMoved(evt);
            }
        });
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        panellogout.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 40, 40));

        roundedPanel1.add(panellogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 40, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 87, 118));
        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\squared_menu_48px.png")); // NOI18N
        roundedPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 138, -1, 40));

        jPanel3.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 160, 470));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\iconnew-min (1).png")); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(921, 631));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idbrgtxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idbrgtxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idbrgtxt1ActionPerformed

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
        //Pemanggilan method penambahan barang dan pembuatan barcode di button simpan
        try {
            newfile();
            addbrg();           
        } catch (Exception ex) {
            Logger.getLogger(barang2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addbuttonActionPerformed

    private void updatebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatebuttonMouseClicked
        updatebrg();
    }//GEN-LAST:event_updatebuttonMouseClicked

    private void updatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebuttonActionPerformed

    }//GEN-LAST:event_updatebuttonActionPerformed

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked
        deletebrg();
    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseEntered

    }//GEN-LAST:event_deletebuttonMouseEntered

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed

    }//GEN-LAST:event_deletebuttonActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        Kosongkan_form();
        otomatis();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void tabel_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseClicked
        /*Aksi yang digunakan untuk menampilkan data dari tabel_barang ke kolom text
        yang telah didefinisikan ketika salah satu baris diklik*/
        int baris = tabel_barang.rowAtPoint(evt.getPoint());
        String id = tabel_barang.getValueAt(baris, 1).toString();
        idbrgtxt1.setText(id);
        String nama = tabel_barang.getValueAt(baris, 2).toString();
        namatxt.setText(nama);
        String hj = tabel_barang.getValueAt(baris, 3).toString();
        hargajualtxt.setText(hj);
        String hb = tabel_barang.getValueAt(baris, 4).toString();
        hbtxt.setText(hb);
        String stok = tabel_barang.getValueAt(baris, 5).toString();
        stoktxt.setText(stok);
        tanggalan.setDate(gettgl(tabel_barang, 6));

        //kode program untuk menampilkan barcode
        showbarcode();
    }//GEN-LAST:event_tabel_barangMouseClicked

    private void tabel_barangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseEntered

    }//GEN-LAST:event_tabel_barangMouseEntered

    private void tabel_barangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseExited

    }//GEN-LAST:event_tabel_barangMouseExited

    private void tabel_barangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_barangKeyPressed

    }//GEN-LAST:event_tabel_barangKeyPressed

    private void tabel_barangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabel_barangKeyReleased

    }//GEN-LAST:event_tabel_barangKeyReleased

    private void caribrgtxt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribrgtxt1FocusGained

    }//GEN-LAST:event_caribrgtxt1FocusGained

    private void caribrgtxt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribrgtxt1FocusLost

    }//GEN-LAST:event_caribrgtxt1FocusLost

    private void caribrgtxt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caribrgtxt1MouseClicked

    }//GEN-LAST:event_caribrgtxt1MouseClicked

    private void caribrgtxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caribrgtxt1ActionPerformed
        tampilkan_data();
    }//GEN-LAST:event_caribrgtxt1ActionPerformed

    private void caribrgtxt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caribrgtxt1KeyReleased
        tampilkan_data();
    }//GEN-LAST:event_caribrgtxt1KeyReleased

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
        supplier s = new supplier();
        s.setVisible(true);
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

    }//GEN-LAST:event_labeltrans13MouseClicked

    private void labeltrans13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseEntered

    }//GEN-LAST:event_labeltrans13MouseEntered

    private void labeltrans13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseExited

    }//GEN-LAST:event_labeltrans13MouseExited

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        opsi_login gin = new opsi_login();
        gin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        panellogout.setBackground(Color.red);
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        Color randomColor2 = new Color(0, 87, 118);
        panellogout.setBackground(randomColor2);
    }//GEN-LAST:event_jLabel17MouseExited

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

    private void labeltrans12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseMoved
        labeltrans12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans12MouseMoved

    private void jLabel17MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseMoved
        jLabel17.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel17MouseMoved
     public static void main(String args[]) {

         java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
                 try {
                     new barang2().setVisible(true);
                 } catch (SQLException ex) {
                     Logger.getLogger(barang2.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         });
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbutton;
    private javax.swing.JLabel alertlabel;
    private javax.swing.JLabel bar_read;
    private javax.swing.JPanel barread;
    private javax.swing.JTextField caribrgtxt1;
    private javax.swing.JButton deletebutton;
    private javax.swing.JTextField hargajualtxt;
    private javax.swing.JTextField hbtxt;
    private javax.swing.JTextField idbrgtxt1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTextField stoktxt;
    private javax.swing.JTable tabel_barang;
    private com.toedter.calendar.JDateChooser tanggalan;
    private javax.swing.JButton updatebutton;
    // End of variables declaration//GEN-END:variables
}
