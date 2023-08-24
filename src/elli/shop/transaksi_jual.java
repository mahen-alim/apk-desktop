/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elli.shop;

import elli.shop.transaksi2;
import elli.shop.Koneksi;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class transaksi_jual extends javax.swing.JFrame {

    public void savejual() {
        //Method untuk menyimpan transaksi penjualan ke  dalam tabel penjualan_rinci                               
        int jumlah, stok, bayar, total;

        jumlah = Integer.parseInt(jumlahtxt.getText());
        stok = Integer.parseInt(stoktxt.getText());
        bayar = Integer.parseInt(bayartxt.getText());
        total = Integer.parseInt(diskontxt1.getText());

        if (jumlah > stok) {// Jika jumlah beli lebih dari stok barang maka akan muncul notifikasi peringatan
            JOptionPane.showMessageDialog(null, "Jumlah Stok Tidak Mencukupi/Habis");
        } else if (bayar == 0) {// Jika nominal beli sama dengan nol/belum bayar maka akan muncul notifikasi peringatan
            JOptionPane.showMessageDialog(null, "Masukkan Nominal Bayar");
        } else if (bayar < total) {
            JOptionPane.showMessageDialog(null, "Nominal yang anda masukkan tidak mencukupi pembayaran");
        } else {
            try {
                Connection c = (Connection) Koneksi.configDB();
                int baris = tabel_trans_jual.getRowCount();
                String tunai = bayartxt.getText();
                String kembalian = kembalitxt.getText();
                String totalbayar = diskontxt1.getText();
                String total_diskon = totaltxt.getText();

                for (int i = 0; i < baris; i++) {
                    String sql = "INSERT INTO penjualan_rinci(No_Transaksi, ID_Pembeli, ID_Barang, Nama_Barang, "
                            + "Jumlah, Harga_Jual, total_harga, total_bayar, total_diskon, Tanggal, Tunai, Kembalian) VALUES('"
                            + tabel_trans_jual.getValueAt(i, 0) + "','" + tabel_trans_jual.getValueAt(i, 1)
                            + "','" + tabel_trans_jual.getValueAt(i, 2) + "','" + tabel_trans_jual.getValueAt(i, 3)
                            + "','" + tabel_trans_jual.getValueAt(i, 4) + "','" + tabel_trans_jual.getValueAt(i, 5)
                            + "','" + tabel_trans_jual.getValueAt(i, 6) + "','" + totalbayar + "','" + total_diskon
                            + "','" + tabel_trans_jual.getValueAt(i, 8) + "','" + tunai + "','" + kembalian + "')";
                    try ( PreparedStatement p1 = c.prepareStatement(sql)) {
                        p1.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Transaksi berhasil");
                }

            } catch (HeadlessException | SQLException e) {
                System.out.println("Simpan penjualan ERROR!");
            }
            //Method untuk menyimpan transaksi penjualan ke  dalam tabel laporan_jual
            try {
                Connection c = (Connection) Koneksi.configDB();
                int baris = tabel_trans_jual.getRowCount();
                String jenis = jenistxt.getText();
                String hari = haritxt.getText();
                String bulan = bulantxt.getText();
                String diskon = totaltxt.getText();
                for (int i = 0; i < baris; i++) {
                    try ( PreparedStatement p1 = c.prepareStatement("INSERT INTO laporan_jual VALUES(?,?,?,?,?,?,?,?,?,?,?)")) {
                        p1.setString(1, (String) tabel_trans_jual.getValueAt(i, 0));
                        p1.setString(2, (String) tabel_trans_jual.getValueAt(i, 2));
                        p1.setString(3, (String) tabel_trans_jual.getValueAt(i, 3));
                        p1.setString(4, (String) tabel_trans_jual.getValueAt(i, 4));
                        p1.setString(5, (String) tabel_trans_jual.getValueAt(i, 5));
                        p1.setString(6, diskon);
                        p1.setString(7, (String) tabel_trans_jual.getValueAt(i, 7));
                        p1.setString(8, jenis);
                        p1.setString(9, (String) tabel_trans_jual.getValueAt(i, 8));
                        p1.setString(10, hari);
                        p1.setString(11, bulan);
                        p1.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Tersimpan ke Laporan");
                }

            } catch (HeadlessException | SQLException e) {
                e.printStackTrace();
            }
            clear();
            kosong();
            diskontxt1.setText("0");
            utama();
        }
        tampilkan_barang();
        autonumber();
        autonumber3();

    }

    String tanggal;
    private DefaultTableModel model = null;

    // method untuk menampilkan data barang dari tabel barang
    public void tampilkan_barang() {
        DefaultTableModel mod = new DefaultTableModel();

        mod.addColumn("No.");
        mod.addColumn("id_Barang");
        mod.addColumn("Nama_barang");
        mod.addColumn("Harga_jual");
        mod.addColumn("Stok_barang");
        mod.addColumn("Expired");
        String cari = caribrgtxt1.getText();

        //pencarian data berdasarkan nama barang dan id barang
        try {
            int no = 1;
            String sql = "Select * From barang WHERE Nama_barang LIKE'%" + cari + "%'" + "or ID_Barang LIKE'%" + cari + "%'";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                mod.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(5), res.getString(6)});

                tabel_barang1.setModel(mod);
            }
        } catch (SQLException e) {
            System.out.println("ERROR :" + e.getMessage());

        }
    }

    public void showdatascan2() {
        String cari = idtxt.getText();

        //pencarian data berdasarkan scan barcode
        try {
            int no = 1;
            String sql = "Select Nama_barang, Harga_jual, Stok_barang From barang WHERE ID_Barang LIKE'%" + cari + "%'";
            java.sql.Connection con = (Connection) Koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                String nama = res.getString("Nama_barang");
                String harga = res.getString("Harga_jual");
                String stok = res.getString("Stok_barang");

                namatxt.setText(nama);
                hargatxt.setText(harga);
                stoktxt.setText(stok);
            } else {
                JOptionPane.showMessageDialog(null, "Barang dengan ID tersebut tidak ditemukan");
            }
        } catch (SQLException e) {
            System.out.println("ERROR :" + e.getMessage());
        }
    }

    public void totalBiaya() {
        //Method perhitungan total biaya yang didapat dari penjumlahan dari kolom total_diskon
        int jumlahbaris = tabel_trans_jual.getRowCount();
        int hargabrg, total = 0;
        for (int i = 0; i < jumlahbaris; i++) {
            hargabrg = Integer.parseInt(tabel_trans_jual.getValueAt(i, 7).toString());
            total += hargabrg;
        }
        diskontxt1.setText(String.valueOf(total));
    }

    public void totalDiskon() {
        //Method perhitungan total biaya yang didapat dari perkalian jumlah dengan harga barang lalu ditambahkan 0
        int jumlahbaris = tabel_trans_jual.getRowCount();
        int hargabdiskon, hargaadiskon, total = 0;
        for (int i = 0; i < jumlahbaris; i++) {
            hargabdiskon = Integer.parseInt(tabel_trans_jual.getValueAt(i, 6).toString());
            hargaadiskon = Integer.parseInt(tabel_trans_jual.getValueAt(i, 7).toString());
            total += hargabdiskon - hargaadiskon;
        }
        totaltxt.setText(String.valueOf(total));
    }

    public void autonumber() {
        //Method menampilkan No. Transaksi secara otomatis dari tabel penjualan_rinci
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stm = c.createStatement();
            String sql = "SELECT * FROM penjualan_rinci ORDER BY No_Transaksi DESC";
            try ( ResultSet r = stm.executeQuery(sql)) {
                if (r.next()) {
                    String nofaktur = r.getString("No_Transaksi").substring(2);
                    String TR = "" + (Integer.parseInt(nofaktur) + 1);
                    String nol = "";

                    switch (TR.length()) {
                        case 1:
                            nol = "000";
                            break;
                        case 2:
                            nol = "00";
                            break;
                        case 3:
                            nol = "0";
                            break;
                        case 4:
                            nol = "";
                            break;
                        default: {
                        }
                    }
                    transaksitxt.setText("TR" + nol + TR);
                } else {
                    transaksitxt.setText("TR0001");
                }
            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Autonumber ERROR!");
        }
    }

    public void autonumber3() {
        //Method menampilkan id pembeli secara otomatis dari tabel penjualan_rinci
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stm = c.createStatement();
            String sql1 = "SELECT * FROM penjualan_rinci ORDER BY ID_Pembeli DESC";
            try ( ResultSet res = stm.executeQuery(sql1)) {
                if (res.next()) {
                    String nofaktur = res.getString("ID_Pembeli").substring(1);
                    String TRi = "" + (Integer.parseInt(nofaktur) + 1);
                    String nol = "";

                    switch (TRi.length()) {
                        case 1:
                            nol = "000";
                        case 2:
                            nol = "00";
                        case 3:
                            nol = "0";
                        case 4:
                            nol = "";
                        default: {
                        }
                    }

                    pembelitxt.setText("P" + nol + TRi);
                } else {
                    pembelitxt.setText("P0006");
                }
            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Autonumber ERROR!");
        }
    }

    public void autonumber2() {
        //Method menampilkan id pembeli secara otomatis dari tabel penjualan_rinci
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stm = c.createStatement();
            String sql1 = "SELECT * FROM penjualan_rinci ORDER BY No_Transaksi DESC";
            try ( ResultSet res = stm.executeQuery(sql1)) {

                if (res.next()) {
                    String nofaktur = res.getString("No_Transaksi").substring(2);
                    String TRi = "" + (Integer.parseInt(nofaktur));
                    String nol = "";

                    switch (TRi.length()) {
                        case 1:
                            nol = "000";
                        case 2:
                            nol = "00";
                        case 3:
                            nol = "0";
                        case 4:
                            nol = "";
                        default: {
                        }
                    }

                    paramfield.setText("TR0" + nol + TRi);
                } else {
                    paramfield.setText("TR0190");
                }
            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Autonumber ERROR!");
        }
    }

    public void sebelumdiskon() {
        int jumlah, harga, total;
        jumlah = Integer.parseInt(jumlahtxt.getText());
        harga = Integer.parseInt(hargatxt.getText());
        total = (jumlah * harga);
        totaltxt.setText(String.valueOf(total));
    }

    public void loadData() {
        //Method yang digunakan untuk menambahkan text kedalam tabel_trans_jual
        DefaultTableModel mod = (DefaultTableModel) tabel_trans_jual.getModel();
        mod.addRow(new Object[]{
            transaksitxt.getText(),
            pembelitxt.getText(),
            idtxt.getText(),
            namatxt.getText(),
            jumlahtxt.getText(),
            hargatxt.getText(),
            sebelumdiskontxt.getText(),
            totaltxt.getText(),
            tanggaltxt.getText(),});

    }

    public void kosong() {
        //Method untuk mengosongkan baris dari kolom tabel_trans_jual
        DefaultTableModel mod = (DefaultTableModel) tabel_trans_jual.getModel();

        while (mod.getRowCount() > 0) {
            mod.removeRow(0);
        }
    }

    public void utama() {
        //Method untuk mengosongkan text yang telah didefinsikan menjadi tidak bernilai (NULL) dan sebagai penampil No Faktur yang baru
        transaksitxt.setText("");
        idtxt.setText("");
        namatxt.setText("");
        hargatxt.setText("");
        jumlahtxt.setText("");
        stoktxt.setText("");
        exptxt.setText("");
        sebelumdiskontxt.setText("");
        autonumber();
    }

    public void clear() {
        //Method untuk mengosongkan text yang telah didefinsikan menjadi bernilai 0
        pembelitxt.setText("0");
        totaltxt.setText("0");
        bayartxt.setText("0");
        kembalitxt.setText("0");
        diskontxt1.setText("0");
        totaltxt.setText("0");
    }

    public void clear2() {
        //Method untuk mengosongkan text yang telah didefinsikan menjadi tidak bernilai (NULL)
        idtxt.setText("");
        namatxt.setText("");
        hargatxt.setText("");
    }

    public void tambah_transaksi() {
        //Method yang digunakan untuk menampilkan text dari jumlahtxt & hbtxt ke totaltxt & simpantxt
        int jumlah, harga, total, diskon, stok;
        jumlah = Integer.parseInt(jumlahtxt.getText());
        stok = Integer.parseInt(stoktxt.getText());

        // Jika jumlah beli melebihi stok barang maka akan muncul notifikasi peringatan
        if (jumlah > stok) {
            JOptionPane.showMessageDialog(null, "Jumlah Stok Tidak Mencukupi/Habis");
        } else if (jumlah == 0) {
            JOptionPane.showMessageDialog(null, "Masukkan jumlah barang terlebih dahulu");
        } else {
            jumlah = Integer.parseInt(jumlahtxt.getText());
            harga = Integer.parseInt(hargatxt.getText());
            total = (jumlah * harga);
            totaltxt.setText(String.valueOf(total));

            //menentukan hierarki diskon per barang    
            if (total >= 50000) {
                diskon = total * 50 / 100;
                total = total - diskon;
                totaltxt.setText(String.valueOf(total));
                JOptionPane.showMessageDialog(null, "Selamat anda mendapatkan diskon 50%");
            } else if (total >= 20000) {
                diskon = total * 20 / 100;
                total = total - diskon;
                totaltxt.setText(String.valueOf(total));
                JOptionPane.showMessageDialog(null, "Selamat anda mendapatkan diskon 20%");
            } else if (total >= 10000) {
                diskon = total * 10 / 100;
                total = total - diskon;
                totaltxt.setText(String.valueOf(total));
                JOptionPane.showMessageDialog(null, "Selamat anda mendapatkan diskon 10%");
            } else if (total < 10000) {
                diskon = total * 0 / 100;
                total = total - diskon;
                totaltxt.setText(String.valueOf(total));
                JOptionPane.showMessageDialog(null, "Maaf anda tidak mendapat diskon");
            }
            loadData();
            clear2();
            totalBiaya();
            totalDiskon();
            idtxt.requestFocus();
        }
    }

    public void tanggal() {
        //Method yang berfungsi untuk menampilkan tanggal otomatis (continue)
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        tanggaltxt.setText(s.format(date));
    }

    public void JOPS() {
        //Method untuk menampilkan informasi toko
        String Nama_Supplier = "Elli Shop";
        String sektor = "UMKM";
        String Alamat_Supplier = "Nganjuk";
        String jenistoko = "Toko Kelontong";
        String jam = "08am - 09pm";
        String barang_termurah = "Royco Ayam";
        String barang_termahal = "Rokok Gudang Garam";

        JOptionPane.showMessageDialog(null, "Nama Toko : " + Nama_Supplier
                + "\nSektor Usaha : " + sektor
                + "\nAlamat Toko : " + Alamat_Supplier
                + "\nJenis Usaha : " + jenistoko
                + "\nJam Operasional : " + jam
                + "\nBarang Termurah : " + barang_termurah
                + "\nBarang Termahal : " + barang_termahal);
    }

    public void gethari() {
        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        Locale lokal = new Locale("id", "ID");
        String namaHari = day.getDisplayName(TextStyle.FULL, lokal);
        haritxt.setText(namaHari);
    }

    public void getbulan() {
        LocalDate today = LocalDate.now();
        Month mon = today.getMonth();
        Locale lokal = new Locale("id", "ID");
        String monname = mon.getDisplayName(TextStyle.FULL, lokal);
        bulantxt.setText(monname);
    }

    public void cetakstruk() {
        try {
            // Load report design
            File reportFile = new File("D:\\Tes_report\\Elli Shop\\src\\elli\\shop\\report1.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
            // Show Parameter
            HashMap h = new HashMap();
            h.put("kode", paramfield.getText());
            // Compile report design to JasperReport object
            JasperReport jasperReport = net.sf.jasperreports.engine.JasperCompileManager.compileReport(jasperDesign);
            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, h, Koneksi.configDB());
            // Display the report using JasperViewer
            JasperViewer jview = new JasperViewer(jasperPrint, false);
            jview.setTitle("Cetak Struk " + paramfield.getText());
            jview.setVisible(true);
            jview.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jview.setLocationRelativeTo(null);
            jview.setFitPageZoomRatio();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public transaksi_jual() throws SQLException {
        initComponents();
        System.out.println("FILE :" + new File("D:\\Tes_report\\Elli Shop\\src\\elli\\shop\\report1.jrxml").exists());
        //create table
        model = new DefaultTableModel();
        tabel_trans_jual.setModel(model);

        model.addColumn("No transaksi");
        model.addColumn("ID Pembeli");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga Jual");
        model.addColumn("Sebelum Diskon");
        model.addColumn("Total Bayar");
        model.addColumn("Tanggal");
        tampilkan_barang();
        autonumber();
        getbulan();
        utama();
        gethari();
        tanggal();
        autonumber2();
        autonumber3();

        totaltxt.setText("0");
        bayartxt.setText("0");
        kembalitxt.setText("0");
        diskontxt1.setText("0");
        idtxt.requestFocus();
        jumlahtxt.setText("0");
        sebelumdiskontxt.setText("0");

        idlaporantxt.setVisible(false);
        haritxt.setVisible(false);
        bulantxt.setVisible(false);
        jenistxt.setVisible(false);
        paramfield.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        kembalitxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        idtxt = new javax.swing.JTextField();
        namatxt = new javax.swing.JTextField();
        hargatxt = new javax.swing.JTextField();
        jumlahtxt = new javax.swing.JTextField();
        bayartxt = new javax.swing.JTextField();
        addbutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        caribrgtxt1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        findbutton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_trans_jual = new javax.swing.JTable();
        tanggaltxt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_barang1 = new javax.swing.JTable();
        simpanbtn = new javax.swing.JButton();
        backbutton = new javax.swing.JLabel();
        refreshbtn = new javax.swing.JButton();
        stoktxt = new javax.swing.JTextField();
        exptxt = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        haritxt = new javax.swing.JTextField();
        bulantxt = new javax.swing.JTextField();
        jenistxt = new javax.swing.JTextField();
        idlaporantxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        transaksitxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pembelitxt = new javax.swing.JTextField();
        totaltxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        diskontxt1 = new javax.swing.JTextField();
        sebelumdiskontxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        paramfield = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kembalitxt.setEditable(false);
        kembalitxt.setBackground(new java.awt.Color(255, 200, 78));
        jPanel1.add(kembalitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 610, 160, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("ID Pembeli");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 70, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ID Barang");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 80, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Nama Barang");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Harga");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 80, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Total Harga");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 70, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Total Bayar");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 540, 100, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Bayar");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 50, -1));

        idtxt.setBackground(new java.awt.Color(0, 0, 0));
        idtxt.setForeground(new java.awt.Color(255, 255, 255));
        idtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idtxtMouseClicked(evt);
            }
        });
        idtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtxtActionPerformed(evt);
            }
        });
        idtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idtxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idtxtKeyReleased(evt);
            }
        });
        jPanel1.add(idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 120, -1));

        namatxt.setEditable(false);
        namatxt.setBackground(new java.awt.Color(0, 0, 0));
        namatxt.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(namatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 120, -1));

        hargatxt.setEditable(false);
        hargatxt.setBackground(new java.awt.Color(0, 0, 0));
        hargatxt.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(hargatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 110, -1));

        jumlahtxt.setBackground(new java.awt.Color(0, 0, 0));
        jumlahtxt.setForeground(new java.awt.Color(255, 255, 255));
        jumlahtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahtxtActionPerformed(evt);
            }
        });
        jumlahtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlahtxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahtxtKeyReleased(evt);
            }
        });
        jPanel1.add(jumlahtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 80, -1));

        bayartxt.setBackground(new java.awt.Color(0, 0, 0));
        bayartxt.setForeground(new java.awt.Color(255, 255, 255));
        bayartxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayartxtActionPerformed(evt);
            }
        });
        bayartxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bayartxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayartxtKeyReleased(evt);
            }
        });
        jPanel1.add(bayartxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 580, 160, -1));

        addbutton.setBackground(new java.awt.Color(255, 200, 78));
        addbutton.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        addbutton.setText("Tambah");
        addbutton.setBorder(null);
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
        jPanel1.add(addbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 80, 40));

        deletebutton.setBackground(new java.awt.Color(255, 200, 78));
        deletebutton.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        deletebutton.setText("Hapus");
        deletebutton.setBorder(null);
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
        jPanel1.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 380, 80, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Kembalian");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, 70, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 200, 78));
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 10, 70));

        caribrgtxt1.setBackground(new java.awt.Color(250, 250, 250));
        caribrgtxt1.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
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
        jPanel2.add(caribrgtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 170, 30));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 200, 78));
        jLabel29.setText("TRANSAKSI PENJUALAN");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 50));

        findbutton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Pictures\\info.png")); // NOI18N
        findbutton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(0, 0, 0)));
        findbutton2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                findbutton2MouseMoved(evt);
            }
        });
        findbutton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findbutton2MouseClicked(evt);
            }
        });
        findbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findbutton2ActionPerformed(evt);
            }
        });
        jPanel2.add(findbutton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 30, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Pictures\\cari.png")); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 30, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 70));

        tabel_trans_jual.setBackground(new java.awt.Color(255, 200, 78));
        tabel_trans_jual.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabel_trans_jual.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        tabel_trans_jual.setForeground(new java.awt.Color(0, 87, 118));
        tabel_trans_jual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabel_trans_jual.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel_trans_jual.getTableHeader().setReorderingAllowed(false);
        tabel_trans_jual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_trans_jualMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_trans_jual);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 820, 150));

        tanggaltxt.setEditable(false);
        tanggaltxt.setBackground(new java.awt.Color(255, 200, 78));
        tanggaltxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggaltxtActionPerformed(evt);
            }
        });
        jPanel1.add(tanggaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 120, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Expired");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 50, 20));

        tabel_barang1.setBackground(new java.awt.Color(0, 0, 0));
        tabel_barang1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabel_barang1.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        tabel_barang1.setForeground(new java.awt.Color(255, 200, 78));
        tabel_barang1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_barang1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel_barang1.getTableHeader().setReorderingAllowed(false);
        tabel_barang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_barang1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_barang1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 820, 150));

        simpanbtn.setBackground(new java.awt.Color(255, 200, 78));
        simpanbtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        simpanbtn.setText("SIMPAN");
        simpanbtn.setBorder(null);
        simpanbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                simpanbtnMouseMoved(evt);
            }
        });
        simpanbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanbtnActionPerformed(evt);
            }
        });
        jPanel1.add(simpanbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 570, 80, 40));

        backbutton.setBackground(new java.awt.Color(0, 0, 0));
        backbutton.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
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
        jPanel1.add(backbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 30, 30));

        refreshbtn.setBackground(new java.awt.Color(0, 0, 0));
        refreshbtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refreshbtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshbtn.setText("Batal");
        refreshbtn.setBorder(null);
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
        jPanel1.add(refreshbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 330, 60, 30));

        stoktxt.setEditable(false);
        stoktxt.setBackground(new java.awt.Color(0, 0, 0));
        stoktxt.setForeground(new java.awt.Color(255, 255, 255));
        stoktxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stoktxtActionPerformed(evt);
            }
        });
        jPanel1.add(stoktxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 90, -1));

        exptxt.setEditable(false);
        exptxt.setBackground(new java.awt.Color(0, 0, 0));
        exptxt.setForeground(new java.awt.Color(255, 255, 255));
        exptxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exptxtActionPerformed(evt);
            }
        });
        jPanel1.add(exptxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 130, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Tanggal");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 50, 20));

        haritxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                haritxtActionPerformed(evt);
            }
        });
        jPanel1.add(haritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 70, -1));

        bulantxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulantxtActionPerformed(evt);
            }
        });
        jPanel1.add(bulantxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, 120, -1));

        jenistxt.setText("Cash");
        jPanel1.add(jenistxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 50, -1));

        idlaporantxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idlaporantxtActionPerformed(evt);
            }
        });
        jPanel1.add(idlaporantxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 480, 110, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("No.Transaksi");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 90, 20));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 10, 560));

        transaksitxt.setEditable(false);
        transaksitxt.setBackground(new java.awt.Color(255, 200, 78));
        transaksitxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksitxtActionPerformed(evt);
            }
        });
        jPanel1.add(transaksitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 130, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Stok");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 70, -1));

        pembelitxt.setEditable(false);
        jPanel1.add(pembelitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 120, -1));

        totaltxt.setEditable(false);
        totaltxt.setBackground(new java.awt.Color(255, 200, 78));
        totaltxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaltxtActionPerformed(evt);
            }
        });
        jPanel1.add(totaltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 160, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Total Diskon");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, -1, 20));

        diskontxt1.setEditable(false);
        diskontxt1.setBackground(new java.awt.Color(255, 200, 78));
        diskontxt1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        diskontxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diskontxt1ActionPerformed(evt);
            }
        });
        jPanel1.add(diskontxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 570, 290, 40));

        sebelumdiskontxt.setEditable(false);
        sebelumdiskontxt.setBackground(new java.awt.Color(0, 0, 0));
        sebelumdiskontxt.setForeground(new java.awt.Color(255, 255, 255));
        sebelumdiskontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sebelumdiskontxtActionPerformed(evt);
            }
        });
        jPanel1.add(sebelumdiskontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 120, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Jumlah");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 80, -1));
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 710, 480));

        paramfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paramfieldActionPerformed(evt);
            }
        });
        jPanel1.add(paramfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtxtActionPerformed

    }//GEN-LAST:event_idtxtActionPerformed

    private void jumlahtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahtxtActionPerformed

    }//GEN-LAST:event_jumlahtxtActionPerformed

    private void jumlahtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahtxtKeyPressed

    }//GEN-LAST:event_jumlahtxtKeyPressed

    private void jumlahtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahtxtKeyReleased
        // Pemanggilan method tambah_transaksi();
        int harga = Integer.parseInt(hargatxt.getText());
        int jmlh = Integer.parseInt(jumlahtxt.getText());
        int tot = harga * jmlh;
        sebelumdiskontxt.setText(String.valueOf(tot));
    }//GEN-LAST:event_jumlahtxtKeyReleased

    private void bayartxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayartxtActionPerformed
        // Aksi untuk melakukan pembayaran, ketika harga beli lebih besar dari uang pembayaran maka akan muncul pesan yang telah didefinisikan
        int total, bayar, kembalian;

        total = Integer.parseInt(diskontxt1.getText());
        bayar = Integer.parseInt(bayartxt.getText());

        if (bayar < total) {// Jika nominal bayar lebih kecil dari total harga maka akan muncul notifikasi peringatan
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else if (total == bayar) {// Jika nominal bayar sama dengan total harga maka akan muncul notifikasi "Uang Pas
            JOptionPane.showMessageDialog(null, "Uang Pas");
        } else { //Jika uang bayar lebih besar dari harga beli maka akan muncul nominal pengembalian di kolom kembalitxt
            kembalian = bayar - total;
            kembalitxt.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_bayartxtActionPerformed

    private void bayartxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayartxtKeyPressed

    }//GEN-LAST:event_bayartxtKeyPressed

    private void bayartxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayartxtKeyReleased

    }//GEN-LAST:event_bayartxtKeyReleased

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
        //Pemanggilan method tambah_transaksi();
        tambah_transaksi();
    }//GEN-LAST:event_addbuttonActionPerformed

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked

    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        //Aksi yang digunakan untuk menghapus data dari kolom tabel_trans_beli
        DefaultTableModel model = (DefaultTableModel) tabel_trans_jual.getModel();
        int row = tabel_trans_jual.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        totaltxt.setText(null);
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void caribrgtxt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribrgtxt1FocusGained

    }//GEN-LAST:event_caribrgtxt1FocusGained

    private void caribrgtxt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caribrgtxt1FocusLost

    }//GEN-LAST:event_caribrgtxt1FocusLost

    private void caribrgtxt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caribrgtxt1MouseClicked

    }//GEN-LAST:event_caribrgtxt1MouseClicked

    private void caribrgtxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caribrgtxt1ActionPerformed
        tampilkan_barang();
    }//GEN-LAST:event_caribrgtxt1ActionPerformed

    private void caribrgtxt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caribrgtxt1KeyReleased
        tampilkan_barang();
    }//GEN-LAST:event_caribrgtxt1KeyReleased

    private void findbutton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findbutton2MouseClicked

    }//GEN-LAST:event_findbutton2MouseClicked

    private void findbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findbutton2ActionPerformed
        JOPS();
    }//GEN-LAST:event_findbutton2ActionPerformed

    private void tabel_trans_jualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_trans_jualMouseClicked

    }//GEN-LAST:event_tabel_trans_jualMouseClicked

    private void tanggaltxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggaltxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggaltxtActionPerformed

    private void tabel_barang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barang1MouseClicked
        //Aksi untuk menampilkan data dari tabel_barang1 kedalam kolom-kolom text yang telah didefinisikan  ketika salah satu baris diklik oleh user
        int baris = tabel_barang1.rowAtPoint(evt.getPoint());
        String id = tabel_barang1.getValueAt(baris, 1).toString();
        idtxt.setText(id);
        String nama = tabel_barang1.getValueAt(baris, 2).toString();
        namatxt.setText(nama);
        String harga = tabel_barang1.getValueAt(baris, 3).toString();
        hargatxt.setText(harga);
        String stok = tabel_barang1.getValueAt(baris, 4).toString();
        stoktxt.setText(stok);
        String tgl = tabel_barang1.getValueAt(baris, 5).toString();
        exptxt.setText(tgl);

        int harga1 = Integer.parseInt(hargatxt.getText());
        int jmlh1 = Integer.parseInt(jumlahtxt.getText());
        int tot = harga1 * jmlh1;
        sebelumdiskontxt.setText(String.valueOf(tot));

    }//GEN-LAST:event_tabel_barang1MouseClicked

    private void simpanbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanbtnActionPerformed
        String bayar = bayartxt.getText();
        if (bayar == "0") {
            JOptionPane.showMessageDialog(null, "Masukkan nominal bayar terlebih dahulu");
        } else {
            savejual();
            autonumber2();
            autonumber3();
            try {
                String url = "jdbc:mysql://localhost:3306/umkm_elli";
                String user = "root";
                String pass = "";

                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Connection con = DriverManager.getConnection(url, user, pass);
                cetakstruk();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error :" + ex.getMessage());

            }
        }
    }//GEN-LAST:event_simpanbtnActionPerformed

    private void backbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseClicked
        transaksi2 board = new transaksi2();
        board.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_backbuttonMouseClicked

    private void backbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseEntered
        Color randomColor2 = new Color(255, 200, 78);
        backbutton.setForeground(randomColor2);
    }//GEN-LAST:event_backbuttonMouseEntered

    private void backbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseExited
        backbutton.setForeground(Color.black);
    }//GEN-LAST:event_backbuttonMouseExited

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        //pemanggilan method clear2();
        clear2();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void stoktxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stoktxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stoktxtActionPerformed

    private void exptxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exptxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exptxtActionPerformed

    private void haritxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_haritxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_haritxtActionPerformed

    private void bulantxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulantxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bulantxtActionPerformed

    private void idlaporantxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idlaporantxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idlaporantxtActionPerformed

    private void transaksitxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksitxtActionPerformed

    }//GEN-LAST:event_transaksitxtActionPerformed

    private void totaltxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaltxtActionPerformed

    }//GEN-LAST:event_totaltxtActionPerformed

    private void diskontxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diskontxt1ActionPerformed

    }//GEN-LAST:event_diskontxt1ActionPerformed

    private void sebelumdiskontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sebelumdiskontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sebelumdiskontxtActionPerformed

    private void paramfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paramfieldActionPerformed
        autonumber2();
    }//GEN-LAST:event_paramfieldActionPerformed

    private void idtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idtxtKeyReleased
        showdatascan2();
    }//GEN-LAST:event_idtxtKeyReleased

    private void idtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idtxtMouseClicked
         
    }//GEN-LAST:event_idtxtMouseClicked

    private void idtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idtxtKeyPressed
        namatxt.setText(null);
        hargatxt.setText(null);
        stoktxt.setText(null);
    }//GEN-LAST:event_idtxtKeyPressed

    private void addbuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbuttonMouseMoved
        addbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addbuttonMouseMoved

    private void refreshbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshbtnMouseMoved
        refreshbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_refreshbtnMouseMoved

    private void deletebuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseMoved
        deletebutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deletebuttonMouseMoved

    private void simpanbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanbtnMouseMoved
        simpanbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_simpanbtnMouseMoved

    private void findbutton2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findbutton2MouseMoved
        findbutton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_findbutton2MouseMoved

    private void backbuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseMoved
        backbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_backbuttonMouseMoved

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
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi_jual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new transaksi_jual().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(transaksi_jual.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbutton;
    private javax.swing.JLabel backbutton;
    private javax.swing.JTextField bayartxt;
    private javax.swing.JTextField bulantxt;
    private javax.swing.JTextField caribrgtxt1;
    private javax.swing.JButton deletebutton;
    private javax.swing.JTextField diskontxt1;
    private javax.swing.JTextField exptxt;
    private javax.swing.JButton findbutton2;
    private javax.swing.JTextField hargatxt;
    private javax.swing.JTextField haritxt;
    private javax.swing.JTextField idlaporantxt;
    private javax.swing.JTextField idtxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jenistxt;
    private javax.swing.JTextField jumlahtxt;
    private javax.swing.JTextField kembalitxt;
    private javax.swing.JTextField namatxt;
    private javax.swing.JTextField paramfield;
    private javax.swing.JTextField pembelitxt;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JTextField sebelumdiskontxt;
    private javax.swing.JButton simpanbtn;
    private javax.swing.JTextField stoktxt;
    private javax.swing.JTable tabel_barang1;
    private javax.swing.JTable tabel_trans_jual;
    private javax.swing.JTextField tanggaltxt;
    private javax.swing.JTextField totaltxt;
    private javax.swing.JTextField transaksitxt;
    // End of variables declaration//GEN-END:variables
}
