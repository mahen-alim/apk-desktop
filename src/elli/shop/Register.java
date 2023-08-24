package elli.shop;

import static com.orsoncharts.data.DataUtils.count;
import elli.shop.Login2;
import elli.shop.Koneksi;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {

    public void input_data() {
        //Fungsi untuk menginput data dari text field terkait ke tabel login
        String user = RFIDtxt.getText();
        String alamat = alamattxt.getText();
        String ques = (String) quescombo.getSelectedItem();
        String ans = (String) answercombo.getSelectedItem();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Salah Satu Kolom Belum Terisi");
        } else if (alamat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Salah Satu Kolom Belum Terisi");
        } else if (ques.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Salah Satu Kolom Belum Terisi");
        } else if (ans.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Salah Satu Kolom Belum Terisi");
        } else {

            try {
                String sql = "INSERT INTO login VALUES ('" + idtxt.getText() + "','" + usertxt.getText() + "','" + RFIDtxt.getText()
                        + "','" + passtxt.getText() + "','" + alamattxt.getText() + "','" + posisitxt.getText()
                        + "','" + quescombo.getSelectedItem() + "','" + answercombo.getSelectedItem() + "')";
                java.sql.Connection con = (Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Registrasi Berhasil");
                Login2 l = new Login2();
                l.setVisible(true);
                this.dispose();

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Registrasi Gagal");
            }
        }
        autoclear();
        autoid();
    }

    private void autoid() {
        //Method untuk menampilkan Id akun secara otomatis dari tabel login
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stm = c.createStatement();
            String sql = "SELECT * FROM login ORDER BY ID_Akun DESC";
            ResultSet r = stm.executeQuery(sql);
            if (r.next()) {
                String nofaktur = r.getString("ID_Akun").substring(1);
                String TR = "" + (Integer.parseInt(nofaktur) + 1);
                String nol = "";

                if (TR.length() == 1) {
                    nol = "0";
                } else if (TR.length() == 2) {
                    nol = "";
                }

                idtxt.setText("A" + nol + TR);
            } else {
                idtxt.setText("A04");
            }
            r.close();

        } catch (Exception e) {
            System.out.println("Autonumber ERROR!");
        }
    }

    public void autoclear() {
        //method untuk menghapus isi textfield terkait menjadi tidak bernilai (NULL)
        RFIDtxt.setText(null);
        passtxt.setText(null);
        conpasstxt.setText(null);
        alamattxt.setText(null);
        posisitxt.setText("Petugas/Kasir");
        quescombo.setSelectedItem("--Pilih Pertanyaan--");
        answercombo.setSelectedItem("--Pilih Jawaban--");
    }

    public void alert_sandi() {
        int count = 0, min = 5, max = 8;
        String data = passtxt.getText();
        data = data.trim();
        String conf = conpasstxt.getText();
        String paswor = passtxt.getText();

        if (data.isEmpty()) {
            alertlabel.setText("Kolom Inputan Harus Diisi");
            alertlabel.setVisible(true);
            alertlabel.setBackground(Color.red);
            count += 1;
        } else if (data.length() < min) {
            alertlabel.setText("Jumlah Inputan Minimal " + min + " Karakter");
            alertlabel.setVisible(true);
            alertlabel.setBackground(Color.red);
            count += 1;
        } else if (data.length() > max) {
            alertlabel.setText("Jumlah Inputan Maksimal " + max + " Karakter");
            alertlabel.setVisible(true);
            alertlabel.setBackground(Color.red);
            count += 1;
        } else {
            alertlabel.setText("Jumlah Inputan Cukup");
            alertlabel.setForeground(Color.green);
        }
    }

    public void confpass() {
        String conf = conpasstxt.getText();
        String paswor = passtxt.getText();
        if (conf.isEmpty()) {
            alert2.setText("Inputan Belum Sesuai");
            alert2.setVisible(true);
            alert2.setForeground(Color.red);
        } else if (conf.matches(paswor)) {
            alert2.setText("Inputan Sesuai");
            alert2.setVisible(true);
            alert2.setForeground(Color.green);
        }
    }

    public Register() {
        initComponents();
        autoid();
        autoclear();
        this.picek.setVisible(false);
        this.picek2.setVisible(false);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        daftarbutton = new javax.swing.JButton();
        hapusbutton = new javax.swing.JButton();
        RFIDtxt = new javax.swing.JTextField();
        rfid_label = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamattxt = new javax.swing.JTextArea();
        passtxt = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        conpasstxt = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        quescombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        answercombo = new javax.swing.JComboBox<>();
        idtxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        posisitxt = new javax.swing.JTextField();
        melek = new javax.swing.JLabel();
        picek = new javax.swing.JLabel();
        melek2 = new javax.swing.JLabel();
        picek2 = new javax.swing.JLabel();
        alertlabel = new javax.swing.JLabel();
        alert2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        usertxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(252, 249, 137));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 440, -1));

        jPanel3.setBackground(new java.awt.Color(0, 87, 118));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 440, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("REGISTRASI PETUGAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, -1));

        jPanel4.setBackground(new java.awt.Color(0, 87, 118));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Batal");
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
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        daftarbutton.setBackground(new java.awt.Color(252, 249, 137));
        daftarbutton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        daftarbutton.setForeground(new java.awt.Color(0, 87, 118));
        daftarbutton.setText("Daftar");
        daftarbutton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                daftarbuttonMouseMoved(evt);
            }
        });
        daftarbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarbuttonActionPerformed(evt);
            }
        });
        jPanel4.add(daftarbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, -1, -1));

        hapusbutton.setBackground(new java.awt.Color(252, 249, 137));
        hapusbutton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        hapusbutton.setForeground(new java.awt.Color(0, 87, 118));
        hapusbutton.setText("Hapus");
        hapusbutton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                hapusbuttonMouseMoved(evt);
            }
        });
        hapusbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusbuttonActionPerformed(evt);
            }
        });
        jPanel4.add(hapusbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, -1, -1));

        RFIDtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RFIDtxtActionPerformed(evt);
            }
        });
        jPanel4.add(RFIDtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 162, -1));

        rfid_label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rfid_label.setForeground(new java.awt.Color(252, 249, 137));
        rfid_label.setText("Kartu RFID");
        jPanel4.add(rfid_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(252, 249, 137));
        jLabel6.setText("Alamat");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        alamattxt.setColumns(20);
        alamattxt.setRows(5);
        jScrollPane1.setViewportView(alamattxt);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 162, 38));

        passtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                passtxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                passtxtMouseExited(evt);
            }
        });
        passtxt.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                passtxtInputMethodTextChanged(evt);
            }
        });
        passtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passtxtActionPerformed(evt);
            }
        });
        passtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passtxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passtxtKeyTyped(evt);
            }
        });
        jPanel4.add(passtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 210, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(252, 249, 137));
        jLabel4.setText("Password");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(252, 249, 137));
        jLabel5.setText("Con. Password");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 20));

        conpasstxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                conpasstxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                conpasstxtMouseExited(evt);
            }
        });
        conpasstxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conpasstxtActionPerformed(evt);
            }
        });
        conpasstxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                conpasstxtKeyTyped(evt);
            }
        });
        jPanel4.add(conpasstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 210, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(252, 249, 137));
        jLabel7.setText("Pilih Pertanyaan");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 20));

        quescombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Pertanyaan--", "Masa-masa paling indah selama kamu hidup?", "Warna Favorit?" }));
        quescombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quescomboActionPerformed(evt);
            }
        });
        jPanel4.add(quescombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 270, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(252, 249, 137));
        jLabel8.setText("Posisi");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(252, 249, 137));
        jLabel9.setText("Pilih Jawaban");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 20));

        answercombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Jawaban--", "SD", "SMP", "SMA", "Hijau", "Merah", "Kuning" }));
        jPanel4.add(answercombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 223, -1));

        idtxt.setEditable(false);
        jPanel4.add(idtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 109, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(252, 249, 137));
        jLabel10.setText("ID User");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 20));

        posisitxt.setEditable(false);
        posisitxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posisitxtActionPerformed(evt);
            }
        });
        jPanel4.add(posisitxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 162, -1));

        melek.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\invisible.png")); // NOI18N
        melek.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                melekMouseMoved(evt);
            }
        });
        melek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                melekMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                melekMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                melekMousePressed(evt);
            }
        });
        jPanel4.add(melek, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 30, 20));

        picek.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\visible.png")); // NOI18N
        picek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                picekMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                picekMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                picekMousePressed(evt);
            }
        });
        jPanel4.add(picek, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 30, 20));

        melek2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\invisible.png")); // NOI18N
        melek2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                melek2MouseMoved(evt);
            }
        });
        melek2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                melek2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                melek2MousePressed(evt);
            }
        });
        jPanel4.add(melek2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 40, -1));

        picek2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\Downloads\\visible.png")); // NOI18N
        picek2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                picek2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                picek2MousePressed(evt);
            }
        });
        jPanel4.add(picek2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 40, -1));

        alertlabel.setForeground(new java.awt.Color(255, 0, 51));
        jPanel4.add(alertlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 210, 20));

        alert2.setForeground(new java.awt.Color(252, 249, 137));
        jPanel4.add(alert2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 210, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(252, 249, 137));
        jLabel11.setText("Username");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 20));

        usertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usertxtActionPerformed(evt);
            }
        });
        jPanel4.add(usertxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 162, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 440, 440));

        setSize(new java.awt.Dimension(440, 539));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //fungsi untuk membatalkan registrasi dengan menampilkan halaman Login
        opsi_login l = new opsi_login();
        l.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void daftarbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarbuttonActionPerformed
        input_data();
    }//GEN-LAST:event_daftarbuttonActionPerformed

    private void hapusbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusbuttonActionPerformed

        alertlabel.setVisible(false);
        alert2.setVisible(false);
        autoclear();
    }//GEN-LAST:event_hapusbuttonActionPerformed

    private void RFIDtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RFIDtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RFIDtxtActionPerformed

    private void passtxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passtxtMouseEntered
        int count = 0, min = 5, max = 8;
        alertlabel.setText("Kolom Inputan Harus Diisi");
        alertlabel.setVisible(true);
        alertlabel.setBackground(Color.red);
        count += 1;

    }//GEN-LAST:event_passtxtMouseEntered

    private void passtxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passtxtMouseExited
        alertlabel.setText(null);
    }//GEN-LAST:event_passtxtMouseExited

    private void passtxtInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_passtxtInputMethodTextChanged

    }//GEN-LAST:event_passtxtInputMethodTextChanged

    private void passtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passtxtActionPerformed

    }//GEN-LAST:event_passtxtActionPerformed

    private void conpasstxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conpasstxtMouseEntered
        confpass();
    }//GEN-LAST:event_conpasstxtMouseEntered

    private void conpasstxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conpasstxtMouseExited
        alert2.setText(null);
    }//GEN-LAST:event_conpasstxtMouseExited

    private void conpasstxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conpasstxtActionPerformed

    }//GEN-LAST:event_conpasstxtActionPerformed

    private void quescomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quescomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quescomboActionPerformed

    private void posisitxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posisitxtActionPerformed

    }//GEN-LAST:event_posisitxtActionPerformed

    private void melekMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melekMouseEntered

    }//GEN-LAST:event_melekMouseEntered

    private void melekMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melekMouseExited

    }//GEN-LAST:event_melekMouseExited

    private void melekMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melekMousePressed
        picek.setVisible(true);
        melek.setVisible(false);
        passtxt.setEchoChar((char) 0);
    }//GEN-LAST:event_melekMousePressed

    private void picekMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picekMouseEntered

    }//GEN-LAST:event_picekMouseEntered

    private void picekMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picekMouseExited

    }//GEN-LAST:event_picekMouseExited

    private void picekMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picekMousePressed
        melek.setVisible(true);
        picek.setVisible(false);
        passtxt.setEchoChar('*');
    }//GEN-LAST:event_picekMousePressed

    private void melek2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melek2MouseEntered

    }//GEN-LAST:event_melek2MouseEntered

    private void melek2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melek2MousePressed
        picek2.setVisible(true);
        melek2.setVisible(false);
        conpasstxt.setEchoChar((char) 0);
    }//GEN-LAST:event_melek2MousePressed

    private void picek2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picek2MouseExited

    }//GEN-LAST:event_picek2MouseExited

    private void picek2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picek2MousePressed
        picek2.setVisible(false);
        melek2.setVisible(true);
        conpasstxt.setEchoChar('*');
    }//GEN-LAST:event_picek2MousePressed

    private void usertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usertxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usertxtActionPerformed

    private void jButton2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseMoved
        jButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jButton2MouseMoved

    private void hapusbuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusbuttonMouseMoved
        hapusbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_hapusbuttonMouseMoved

    private void daftarbuttonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarbuttonMouseMoved
        daftarbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_daftarbuttonMouseMoved

    private void melekMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melekMouseMoved
        melek.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        picek.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_melekMouseMoved

    private void melek2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melek2MouseMoved
        melek2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        picek2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_melek2MouseMoved

    private void passtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passtxtKeyReleased

    }//GEN-LAST:event_passtxtKeyReleased

    private void passtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passtxtKeyPressed

    }//GEN-LAST:event_passtxtKeyPressed

    private void passtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passtxtKeyTyped
        alert_sandi();
    }//GEN-LAST:event_passtxtKeyTyped

    private void conpasstxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_conpasstxtKeyTyped
        confpass();
    }//GEN-LAST:event_conpasstxtKeyTyped

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RFIDtxt;
    private javax.swing.JTextArea alamattxt;
    private javax.swing.JLabel alert2;
    private javax.swing.JLabel alertlabel;
    private javax.swing.JComboBox<String> answercombo;
    private javax.swing.JPasswordField conpasstxt;
    private javax.swing.JButton daftarbutton;
    private javax.swing.JButton hapusbutton;
    private javax.swing.JTextField idtxt;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel melek;
    private javax.swing.JLabel melek2;
    private javax.swing.JPasswordField passtxt;
    private javax.swing.JLabel picek;
    private javax.swing.JLabel picek2;
    private javax.swing.JTextField posisitxt;
    private javax.swing.JComboBox<String> quescombo;
    private javax.swing.JLabel rfid_label;
    private javax.swing.JTextField usertxt;
    // End of variables declaration//GEN-END:variables
}
