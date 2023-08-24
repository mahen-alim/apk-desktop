/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package elli.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Dashboard2 extends javax.swing.JFrame {

    private void getpendapatan() throws SQLException {
        //Method yang menghitung jumlah pendapatan berdasarkan penjumlahan kolom Total dari tabel penjualan_rinci
        try {
            String sql = "select sum(total_bayar) as pendapatan from penjualan_rinci as tabel ";

            java.sql.Connection c = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = c.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            String pendapatan;
            //Jika penjumlahan berhasil maka akan muncul di textfield pendapatantxt
            if (res.next()) {
                pendapatan = res.getString("pendapatan");
                this.pendapatantxt.setText(pendapatan);
                c.close();
                stm.close();
                res.close();
            }
        } catch (SQLException e) {
        }
    }

    private void getpengeluaran() throws SQLException {
        //Method yang menghitung jumlah pengeluaran berdasarkan penjumlahan kolom Total dari tabel pembelian_rinci
        try {
            String sql = "select sum(Total) as pengeluaran from pembelian_rinci as tabel ";

            java.sql.Connection c = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = c.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            String pengeluaran;
            //Jika penjumlahan berhasil maka akan muncul di textfield pengeluarantxt
            if (res.next()) {
                pengeluaran = res.getString("pengeluaran");
                this.pengeluarantxt.setText(pengeluaran);
                c.close();
                stm.close();
                res.close();
            }
        } catch (SQLException e) {
        }
    }

    public void getjumlahbrg() throws SQLException {
        //Method yang menghitung jumlah barang berdasarkan penjumlahan kolom Jumlah dari tabel penjualan_rinci saat tanggal hari ini tampil
        try {
            String sql = "select sum(Jumlah) as jumlah_barang from penjualan_rinci where DATE(Tanggal) = CURRENT_DATE()";
            java.sql.Connection c = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = c.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            String jumlah;
            //Jika penjumlahan berhasil maka akan muncul di textfield terjualtxt
            if (res.next()) {
                jumlah = res.getString("jumlah_barang");
                this.terjualtxt.setText(jumlah);
                c.close();
                stm.close();
                res.close();
            }
        } catch (SQLException e) {
        }
    }

    public void getpembeli() {
        //Method yang menghitung jumlah pembeli berdasarkan penjumlahan kolom ID_Pembeli dari tabel penjualan_rinci saat tanggal hari ini tampil
        try {
            String sql = "select count(ID_Pembeli) as pembeli from penjualan_rinci where date(Tanggal) = CURRENT_DATE()";

            java.sql.Connection c = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = c.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            String pembeli;
            //Jika penjumlahan berhasil maka akan muncul di textfield jmlhpembelitxt        
            if (res.next()) {
                pembeli = res.getString("pembeli");
                this.pembelifiled.setText(pembeli);
                c.close();
                stm.close();
                res.close();
            }

        } catch (SQLException e) {
        }
    }

    public void getjmlhbrg() {
        //Method yang menghitung jumlah pembeli berdasarkan penjumlahan kolom ID_Barang dari tabel barang 
        try {
            String sql = "select count(ID_Barang) as brg from barang";

            java.sql.Connection c = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = c.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            String brg;
            //Jika penjumlahan berhasil maka akan muncul di textfield brgjmlhtxt        
            if (res.next()) {
                brg = res.getString("brg");
                this.brgjmlhtxt.setText(brg);
                c.close();
                stm.close();
                res.close();
            }

        } catch (SQLException e) {
        }
    }

    public void getjmlhkrywn() {
        //Method yang menghitung jumlah karyawan berdasarkan penjumlahan kolom NK dari tabel karyawan 
        try {
            String sql = "select count(NK) as krywn from karyawan";

            java.sql.Connection c = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = c.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            String krywn;
            //Jika penjumlahan berhasil maka akan muncul di textfield brgjmlhtxt        
            if (res.next()) {
                krywn = res.getString("krywn");
                this.krywntxt.setText(krywn);
                c.close();
                stm.close();
                res.close();
            }

        } catch (SQLException e) {
        }
    }

    public void getsupplier() {
        //Method yang menghitung jumlah supplier berdasarkan penjumlahan kolom ID_Supplier dari tabel supplier 
        try {
            String sql = "select count(ID_Supplier) as sup from supplier";

            java.sql.Connection c = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = c.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            String supplier;
            //Jika penjumlahan berhasil maka akan muncul di textfield brgjmlhtxt        
            if (res.next()) {
                supplier = res.getString("sup");
                this.suptxt.setText(supplier);
                c.close();
                stm.close();
                res.close();
            }

        } catch (SQLException e) {
        }
    }

    public void tanggal() {
        //Method yang berfungsi untuk menampilkan tanggal otomatis (continue)
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        tanggalan.setText(s.format(date));
    }

    public void gethari() {
        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        Locale lokal = new Locale("id", "ID");
        String namaHari = day.getDisplayName(TextStyle.FULL, lokal);
        haritxt.setText(namaHari);
        haritxt1.setText(namaHari);
    }

    public void getbulan() {
        LocalDate today = LocalDate.now();
        Month mon = today.getMonth();
        Locale lokal = new Locale("id", "ID");
        String monname = mon.getDisplayName(TextStyle.FULL, lokal);
        labelbulan.setText(monname);
        labelbulan1.setText(monname);
    }

    public void showbar() {
        try {
            int jmlh_brg = Integer.parseInt(brgjmlhtxt.getText());

            int jmlh_krywn = Integer.parseInt(krywntxt.getText());

            int jmlh_supplier = Integer.parseInt(suptxt.getText());

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(Integer.valueOf(jmlh_brg), "1", "Jumlah Barang");
            dataset.setValue(Integer.valueOf(jmlh_krywn), "3", "Jumlah Karyawan");
            dataset.setValue(Integer.valueOf(jmlh_supplier), "5", "Jumlah Supplier");

            JFreeChart chart = ChartFactory.createBarChart3D("Akumulasi Data", "Data",
                    "Jumlah", dataset, PlotOrientation.VERTICAL, false, true, true);
            panelchart.setLayout(new java.awt.BorderLayout());
            ChartPanel fr = new ChartPanel(chart);
            panelchart.add(fr, BorderLayout.CENTER);
            panelchart.validate();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void showpie() {
        //Fungsi untuk menampilkan pie chart serta untuk menampilkan data dari tabelharian
        try {
            int jmlh_brg = Integer.parseInt(brgjmlhtxt.getText());

            int jmlh_krywn = Integer.parseInt(krywntxt.getText());

            int jmlh_supplier = Integer.parseInt(suptxt.getText());

            DefaultPieDataset pie = new DefaultPieDataset();
            pie.setValue("Jumlah Barang", Integer.valueOf(jmlh_brg));
            pie.setValue("Jumlah Karyawan", Integer.valueOf(jmlh_krywn));
            pie.setValue("Jumlah Supplier", Integer.valueOf(jmlh_supplier));

            JFreeChart car = ChartFactory.createPieChart3D("Akumulasi Data", pie,
                    rootPaneCheckingEnabled, rootPaneCheckingEnabled, Locale.ITALY);
            panelchart.setLayout(new java.awt.BorderLayout());
            ChartPanel fr = new ChartPanel(car);
            panelchart.add(fr, BorderLayout.CENTER);
            panelchart.validate();
        } catch (NumberFormatException e) {
        }
    }

    public Dashboard2() throws SQLException {
        initComponents();
        getpendapatan();
        tanggal();
        getpembeli();
        getpengeluaran();
        getjumlahbrg();
        getjmlhbrg();
        getjmlhkrywn();
        getsupplier();
        gethari();
        getbulan();

        namabrgtxt.setVisible(false);
        totaltxt.setVisible(false);
        hargatxt.setVisible(false);
        jumlahtxt.setVisible(false);
        brgjmlhtxt.setVisible(false);
        suptxt.setVisible(false);
        krywntxt.setVisible(false);

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
        jLabel7 = new javax.swing.JLabel();
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
        tanggalan = new javax.swing.JTextField();
        roundedPanel2 = new org.hq.RoundedPanel();
        roundedPanel3 = new org.hq.RoundedPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        roundedPanel8 = new org.hq.RoundedPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        roundedPanel9 = new org.hq.RoundedPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        roundedPanel10 = new org.hq.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        roundedPanel11 = new org.hq.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        roundedPanel5 = new org.hq.RoundedPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pembelifiled = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        haritxt = new javax.swing.JLabel();
        panelchart = new javax.swing.JPanel();
        jumlahtxt = new javax.swing.JLabel();
        brgjmlhtxt = new javax.swing.JLabel();
        namabrgtxt = new javax.swing.JLabel();
        totaltxt = new javax.swing.JLabel();
        suptxt = new javax.swing.JLabel();
        krywntxt = new javax.swing.JLabel();
        hargatxt = new javax.swing.JLabel();
        roundedPanel4 = new org.hq.RoundedPanel();
        pendapatantxt = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        labelluar1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        labelbulan1 = new javax.swing.JLabel();
        roundedPanel6 = new org.hq.RoundedPanel();
        labelluar = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pengeluarantxt = new javax.swing.JLabel();
        labelbulan = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        roundedPanel7 = new org.hq.RoundedPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        haritxt1 = new javax.swing.JLabel();
        terjualtxt = new javax.swing.JLabel();
        barpanel = new org.hq.RoundedPanel();
        jLabel27 = new javax.swing.JLabel();
        piepanel = new org.hq.RoundedPanel();
        jLabel26 = new javax.swing.JLabel();

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
        labeltrans7.setForeground(new java.awt.Color(252, 249, 137));
        labeltrans7.setText("DASHBOARD");
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

        paneldasbor.setBackground(new java.awt.Color(0, 87, 118));
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
        panellogout.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panellogoutMouseMoved(evt);
            }
        });
        panellogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panellogoutMouseEntered(evt);
            }
        });
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
        jLabel4.setText("DASHBOARD");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 6, 180, 50));

        tanggalan.setEditable(false);
        tanggalan.setBackground(new java.awt.Color(0, 87, 118));
        tanggalan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tanggalan.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(tanggalan, new org.netbeans.lib.awtextra.AbsoluteConstraints(634, 15, 100, -1));

        roundedPanel2.setBackground(new java.awt.Color(0, 87, 118));
        roundedPanel2.setRoundBottomLeft(20);
        roundedPanel2.setRoundBottomRight(20);
        roundedPanel2.setRoundTopLeft(20);
        roundedPanel2.setRoundTopRight(20);

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel2.add(roundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 730, 10));

        roundedPanel3.setBackground(new java.awt.Color(252, 249, 137));
        roundedPanel3.setRoundBottomLeft(20);
        roundedPanel3.setRoundBottomRight(20);
        roundedPanel3.setRoundTopLeft(20);
        roundedPanel3.setRoundTopRight(20);

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel2.add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 730, 10));

        jPanel3.setBackground(new java.awt.Color(0, 87, 118));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(252, 249, 137));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel8.setBackground(new java.awt.Color(0, 87, 118));
        roundedPanel8.setRoundTopLeft(50);
        roundedPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(252, 249, 137));
        jLabel5.setText(" Jam Operasional");
        roundedPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 100, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 87, 118));
        jLabel12.setText("  17.00 Pm");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 100, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 87, 118));
        jLabel14.setText(" 08.00 Am -");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 40));

        roundedPanel8.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 110));

        jPanel4.add(roundedPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 140, 140));

        roundedPanel9.setBackground(new java.awt.Color(0, 87, 118));
        roundedPanel9.setRoundTopLeft(50);
        roundedPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 87, 118));
        jLabel16.setText(" Toko Elli");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 40));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 87, 118));
        jLabel28.setText(" Toko Elli");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 40));

        roundedPanel9.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 110));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(252, 249, 137));
        jLabel10.setText("Nama Toko");
        roundedPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jPanel4.add(roundedPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 140, 140));

        roundedPanel10.setBackground(new java.awt.Color(0, 87, 118));
        roundedPanel10.setRoundTopLeft(50);
        roundedPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(252, 249, 137));
        jLabel6.setText("Jenis Usaha");
        roundedPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 87, 118));
        jLabel13.setText(" Kelontong");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, 40));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 87, 118));
        jLabel30.setText("  Toko ");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        roundedPanel10.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 110));

        jPanel4.add(roundedPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 140, 140));

        roundedPanel11.setBackground(new java.awt.Color(0, 87, 118));
        roundedPanel11.setRoundTopLeft(50);
        roundedPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(252, 249, 137));
        jLabel1.setText(" Mitra");
        roundedPanel11.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 87, 118));
        jLabel29.setText("Sembilan");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 50));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 87, 118));
        jLabel31.setText("PT. SRC");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 50));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 87, 118));
        jLabel32.setText("Indonesia");
        jPanel8.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 50));

        roundedPanel11.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 110));

        jPanel4.add(roundedPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 140, 140));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 730, 140));

        roundedPanel5.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel5.setRoundBottomRight(50);
        roundedPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 87, 118));
        jLabel18.setText("        Total Pembeli");
        roundedPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 140, 20));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 87, 118));
        jLabel17.setText("/Hari");
        roundedPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 40, 10));

        pembelifiled.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        pembelifiled.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel5.add(pembelifiled, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 40, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 87, 118));
        jSeparator1.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 170, 10));

        haritxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        haritxt.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel5.add(haritxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 40, 20));

        jPanel3.add(roundedPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 100));

        panelchart.setBackground(new java.awt.Color(0, 87, 118));
        panelchart.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jumlahtxt.setText("jLabel38");

        brgjmlhtxt.setText("jLabel38");

        namabrgtxt.setText("jLabel38");

        totaltxt.setText("jLabel38");

        suptxt.setText("jLabel38");

        krywntxt.setText("jLabel38");

        hargatxt.setText("jLabel38");

        javax.swing.GroupLayout panelchartLayout = new javax.swing.GroupLayout(panelchart);
        panelchart.setLayout(panelchartLayout);
        panelchartLayout.setHorizontalGroup(
            panelchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelchartLayout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jumlahtxt)
                .addGap(6, 6, 6)
                .addComponent(brgjmlhtxt)
                .addGap(12, 12, 12)
                .addComponent(krywntxt)
                .addGap(18, 18, 18)
                .addComponent(suptxt)
                .addGap(18, 18, 18)
                .addComponent(totaltxt)
                .addGap(18, 18, 18)
                .addComponent(namabrgtxt)
                .addGap(6, 6, 6)
                .addComponent(hargatxt)
                .addGap(0, 105, Short.MAX_VALUE))
        );
        panelchartLayout.setVerticalGroup(
            panelchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelchartLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jumlahtxt)
                    .addComponent(brgjmlhtxt)
                    .addComponent(krywntxt)
                    .addComponent(suptxt)
                    .addComponent(totaltxt)
                    .addComponent(namabrgtxt)
                    .addComponent(hargatxt))
                .addGap(0, 206, Short.MAX_VALUE))
        );

        jPanel3.add(panelchart, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 730, 230));

        roundedPanel4.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel4.setRoundBottomRight(50);
        roundedPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pendapatantxt.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        pendapatantxt.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel4.add(pendapatantxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 100, 30));

        jLabel24.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 87, 118));
        jLabel24.setText("  Rp");
        roundedPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 40, 30));

        labelluar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelluar1.setForeground(new java.awt.Color(0, 87, 118));
        labelluar1.setText("  Total Pendapatan");
        labelluar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelluar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelluar1MouseExited(evt);
            }
        });
        roundedPanel4.add(labelluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 120, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 87, 118));
        jLabel25.setText("/Bulan");
        roundedPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 20, 40, 10));

        jSeparator5.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 170, -1));

        labelbulan1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelbulan1.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel4.add(labelbulan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 50, 20));

        jPanel3.add(roundedPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 170, 100));

        roundedPanel6.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel6.setRoundBottomRight(50);
        roundedPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelluar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelluar.setForeground(new java.awt.Color(0, 87, 118));
        labelluar.setText("  Total Pengeluaran");
        labelluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelluarMouseExited(evt);
            }
        });
        roundedPanel6.add(labelluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 120, 20));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 87, 118));
        jLabel19.setText("/Bulan");
        roundedPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 20, 40, 10));

        jLabel20.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 87, 118));
        jLabel20.setText("  Rp");
        roundedPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 40, 30));

        pengeluarantxt.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        pengeluarantxt.setForeground(new java.awt.Color(0, 87, 118));
        pengeluarantxt.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                pengeluarantxtAncestorResized(evt);
            }
        });
        roundedPanel6.add(pengeluarantxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 100, 30));

        labelbulan.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelbulan.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel6.add(labelbulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 40, 20));

        jSeparator2.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel6.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 170, 10));

        jPanel3.add(roundedPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 170, 100));

        roundedPanel7.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel7.setRoundBottomRight(50);
        roundedPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setBackground(new java.awt.Color(0, 87, 118));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 87, 118));
        jLabel21.setText(" Barang terjual hari ini");
        roundedPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 20));

        jLabel23.setBackground(new java.awt.Color(0, 87, 118));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 87, 118));
        jLabel23.setText("/Hari");
        roundedPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 40, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel7.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 170, 10));

        haritxt1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        haritxt1.setForeground(new java.awt.Color(0, 87, 118));
        haritxt1.setText("    ");
        roundedPanel7.add(haritxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 40, 20));

        terjualtxt.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        terjualtxt.setForeground(new java.awt.Color(0, 87, 118));
        roundedPanel7.add(terjualtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 40, 30));

        jPanel3.add(roundedPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 170, 100));

        barpanel.setBackground(new java.awt.Color(255, 255, 255));
        barpanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 87, 118), 1, true));
        barpanel.setRoundTopLeft(15);
        barpanel.setRoundTopRight(15);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 87, 118));
        jLabel27.setText("    Pie Chart");
        jLabel27.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel27MouseMoved(evt);
            }
        });
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel27MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel27MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel27MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout barpanelLayout = new javax.swing.GroupLayout(barpanel);
        barpanel.setLayout(barpanelLayout);
        barpanelLayout.setHorizontalGroup(
            barpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        barpanelLayout.setVerticalGroup(
            barpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        jPanel3.add(barpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 80, 20));

        piepanel.setBackground(new java.awt.Color(255, 255, 255));
        piepanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 87, 118), 1, true));
        piepanel.setRoundTopLeft(15);
        piepanel.setRoundTopRight(15);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 87, 118));
        jLabel26.setText("    Bar Chart");
        jLabel26.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jLabel26HierarchyChanged(evt);
            }
        });
        jLabel26.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel26MouseMoved(evt);
            }
        });
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel26MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel26MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel26MouseReleased(evt);
            }
        });
        jLabel26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel26KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout piepanelLayout = new javax.swing.GroupLayout(piepanel);
        piepanel.setLayout(piepanelLayout);
        piepanelLayout.setHorizontalGroup(
            piepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        piepanelLayout.setVerticalGroup(
            piepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        jPanel3.add(piepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 730, 530));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 750, 640));

        setSize(new java.awt.Dimension(930, 632));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labeltrans7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseClicked

    }//GEN-LAST:event_labeltrans7MouseClicked

    private void labeltrans7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseEntered

    }//GEN-LAST:event_labeltrans7MouseEntered

    private void labeltrans7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans7MouseExited

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
            Logger.getLogger(Dashboard2.class.getName()).log(Level.SEVERE, null, ex);
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

    private void jLabel26HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabel26HierarchyChanged

    }//GEN-LAST:event_jLabel26HierarchyChanged

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        showbar();
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        Color randomColor = new Color(0, 87, 118);

        jLabel26.setForeground(Color.white);
        piepanel.setBackground(randomColor);
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jLabel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseExited
        Color randomColor = new Color(0, 87, 118);

        jLabel26.setForeground(randomColor);
        piepanel.setBackground(Color.white);
    }//GEN-LAST:event_jLabel26MouseExited

    private void jLabel26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel26KeyPressed
       
    }//GEN-LAST:event_jLabel26KeyPressed

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        showpie();
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseEntered
        // Membuat objek Color dengan nilai acak
        Color randomColor = new Color(0, 87, 118);

        jLabel27.setForeground(Color.white);
        barpanel.setBackground(randomColor);
    }//GEN-LAST:event_jLabel27MouseEntered

    private void jLabel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseExited
        // Membuat objek Color dengan nilai acak
        Color randomColor = new Color(0, 87, 118);

        jLabel27.setForeground(randomColor);
        barpanel.setBackground(Color.white);

    }//GEN-LAST:event_jLabel27MouseExited

    private void jLabel27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MousePressed
        // Membuat objek Color dengan nilai acak
        Color randomColor = new Color(0, 87, 118);

        jLabel27.setForeground(Color.white);
        barpanel.setBackground(randomColor);
        jLabel26.setForeground(Color.white);
        piepanel.setBackground(randomColor);
    }//GEN-LAST:event_jLabel27MousePressed

    private void labelluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelluarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelluarMouseEntered

    private void labelluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelluarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelluarMouseExited

    private void pengeluarantxtAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_pengeluarantxtAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_pengeluarantxtAncestorResized

    private void labelluar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelluar1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelluar1MouseEntered

    private void labelluar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelluar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_labelluar1MouseExited

    private void labeltrans1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans1MouseMoved
        labeltrans1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans1MouseMoved

    private void jLabel27MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseMoved
        jLabel27.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel27MouseMoved

    private void jLabel26MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseMoved
        jLabel26.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel26MouseMoved

    private void labeltrans10MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans10MouseMoved
        labeltrans10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans10MouseMoved

    private void labeltrans11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans11MouseMoved
        labeltrans11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans11MouseMoved

    private void labeltrans12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans12MouseMoved
        labeltrans12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans12MouseMoved

    private void labeltrans13MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeltrans13MouseMoved
        labeltrans13.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labeltrans13MouseMoved

    private void jLabel8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseMoved
        jLabel8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel8MouseMoved

    private void panellogoutMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panellogoutMouseMoved
        panellogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_panellogoutMouseMoved

    private void panellogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panellogoutMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panellogoutMouseEntered

    private void jLabel26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MousePressed
        // Membuat objek Color dengan nilai acak
        Color randomColor = new Color(0, 87, 118);

        jLabel26.setForeground(Color.white);
        piepanel.setBackground(randomColor);
        jLabel27.setForeground(Color.white);
        barpanel.setBackground(randomColor);
    }//GEN-LAST:event_jLabel26MousePressed

    private void jLabel26MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseReleased
        // Membuat objek Color dengan nilai acak
        Color randomColor = new Color(0, 87, 118);

        jLabel26.setForeground(randomColor);
        piepanel.setBackground(Color.white);
    }//GEN-LAST:event_jLabel26MouseReleased

    private void jLabel27MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseReleased
        // Membuat objek Color dengan nilai acak
        Color randomColor = new Color(0, 87, 118);

        jLabel27.setForeground(randomColor);
        barpanel.setBackground(Color.white);      
    }//GEN-LAST:event_jLabel27MouseReleased

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
            java.util.logging.Logger.getLogger(Dashboard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Dashboard2().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.hq.RoundedPanel barpanel;
    private javax.swing.JLabel brgjmlhtxt;
    private javax.swing.JLabel hargatxt;
    private javax.swing.JLabel haritxt;
    private javax.swing.JLabel haritxt1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel jumlahtxt;
    private javax.swing.JLabel krywntxt;
    private javax.swing.JLabel labelbulan;
    private javax.swing.JLabel labelbulan1;
    private javax.swing.JLabel labelluar;
    private javax.swing.JLabel labelluar1;
    private javax.swing.JLabel labeltrans1;
    private javax.swing.JLabel labeltrans10;
    private javax.swing.JLabel labeltrans11;
    private javax.swing.JLabel labeltrans12;
    private javax.swing.JLabel labeltrans13;
    private javax.swing.JLabel labeltrans7;
    private javax.swing.JLabel namabrgtxt;
    private org.hq.RoundedPanel panelbrg;
    private javax.swing.JPanel panelchart;
    private org.hq.RoundedPanel paneldasbor;
    private org.hq.RoundedPanel panelkrwn;
    private org.hq.RoundedPanel panellap;
    private org.hq.RoundedPanel panellogout;
    private org.hq.RoundedPanel panelsup;
    private org.hq.RoundedPanel paneltransaksi;
    private javax.swing.JLabel pembelifiled;
    private javax.swing.JLabel pendapatantxt;
    private javax.swing.JLabel pengeluarantxt;
    private org.hq.RoundedPanel piepanel;
    private org.hq.RoundedPanel roundedPanel1;
    private org.hq.RoundedPanel roundedPanel10;
    private org.hq.RoundedPanel roundedPanel11;
    private org.hq.RoundedPanel roundedPanel2;
    private org.hq.RoundedPanel roundedPanel3;
    private org.hq.RoundedPanel roundedPanel4;
    private org.hq.RoundedPanel roundedPanel5;
    private org.hq.RoundedPanel roundedPanel6;
    private org.hq.RoundedPanel roundedPanel7;
    private org.hq.RoundedPanel roundedPanel8;
    private org.hq.RoundedPanel roundedPanel9;
    private javax.swing.JLabel suptxt;
    private javax.swing.JTextField tanggalan;
    private javax.swing.JLabel terjualtxt;
    private javax.swing.JLabel totaltxt;
    // End of variables declaration//GEN-END:variables
}
