/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package admin;

/**
 *
 * @author Acer Aspire Lite 15
 */
import config.KoneksiDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class daftar_menu extends javax.swing.JPanel {

    /**
     * Creates new form kategoti
     */
    private DefaultTableModel tabModel;
    
    public daftar_menu() {
        initComponents();
        
        setupTabel();       
        loadKategori();     
        loadStatus();       
        
        tampilData();       
        resetForm();        
        buatIdOtomatis();
    }
    
    private void setupTabel() {
        tabModel = new DefaultTableModel();
        tabModel.addColumn("NO.");
        tabModel.addColumn("ID MENU");
        tabModel.addColumn("NAMA MENU");
        tabModel.addColumn("KATEGORI");
        tabModel.addColumn("HARGA");
        tabModel.addColumn("STATUS");
        tabModel.addColumn("KETERANGAN");
        
        tabel_daftar_menu.setModel(tabModel);
        
        // Mengatur lebar kolom agar rapi
        tabel_daftar_menu.getColumnModel().getColumn(0).setMaxWidth(40); // NO
        tabel_daftar_menu.getColumnModel().getColumn(1).setMinWidth(80); // ID
        tabel_daftar_menu.getColumnModel().getColumn(2).setMinWidth(150); // NAMA
        tabel_daftar_menu.getColumnModel().getColumn(4).setMinWidth(80); // HARGA
        tabel_daftar_menu.getColumnModel().getColumn(5).setMinWidth(80); // STATUS
    }
    
    private void loadKategori() {
        cb_jenis_kategori.removeAllItems(); // Kosongkan item default
        String sql = "SELECT nama_kategori FROM kategori ORDER BY nama_kategori ASC";
        
        try (Connection conn = KoneksiDB.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                cb_jenis_kategori.addItem(rs.getString("nama_kategori"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Gagal memuat data kategori: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadStatus() {
        cb_status.removeAllItems(); // Kosongkan item default
        cb_status.addItem("Tersedia");
        cb_status.addItem("Tidak Tersedia");
    }

    private void tampilData() {
        tabModel.setRowCount(0); // Hapus data lama di tabel
        String sql = "SELECT * FROM menu ORDER BY id_menu ASC";
        
        try (Connection conn = KoneksiDB.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            int no = 1;
            while(rs.next()){
                tabModel.addRow(new Object[]{
                    no++,
                    rs.getString("id_menu"),
                    rs.getString("nama_menu"),
                    rs.getString("kategori"),
                    rs.getInt("harga"), // Ambil sebagai integer
                    rs.getString("status"),
                    rs.getString("keterangan")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Terjadi error saat memuat data menu: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetForm() {
        tf_id_menu.setText("");
        tf_id_menu.setEditable(false); // ID tidak bisa diedit
        
        tf_nama_menu.setText("");
        tf_harga.setText("");
        tf_keterangan.setText("");
        
        if (cb_jenis_kategori.getItemCount() > 0) {
            cb_jenis_kategori.setSelectedIndex(0);
        }
        if (cb_status.getItemCount() > 0) {
            cb_status.setSelectedIndex(0);
        }
        
        btn_simpan.setText("SIMPAN");
    }

    private void buatIdOtomatis() {
        String sql = "SELECT MAX(RIGHT(id_menu, 3)) AS no_terakhir FROM menu WHERE id_menu LIKE 'MNU%'";
        
        try (Connection conn = KoneksiDB.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            String idBaru;
            if (rs.next()) {
                String noTerakhir = rs.getString("no_terakhir");
                int noUrut;

                if (noTerakhir == null) {
                    noUrut = 1; // Jika tidak ada data, mulai dari 1
                } else {
                    noUrut = Integer.parseInt(noTerakhir) + 1; // Jika ada, tambah 1
                }
                idBaru = String.format("MNU%03d", noUrut);
            } else {
                idBaru = "MNU001"; // Default jika tabel kosong
            }
            tf_id_menu.setText(idBaru);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Gagal membuat ID Menu otomatis: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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
        jLabel1 = new javax.swing.JLabel();
        tf_pencarian = new javax.swing.JTextField();
        btn_refresh = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_daftar_menu = new javax.swing.JTable();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_id_menu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_nama_menu = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_harga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_keterangan = new javax.swing.JTextArea();
        cb_status = new javax.swing.JComboBox<>();
        cb_jenis_kategori = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(1660, 920));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DAFTAR KATEGORI MENU");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 60));

        tf_pencarian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(tf_pencarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, 50));

        btn_refresh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_refresh.setForeground(new java.awt.Color(255, 255, 255));
        btn_refresh.setText("REFRESH");
        btn_refresh.setToolTipText("");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        jPanel1.add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, 150, 50));

        btn_cari.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_cari.setForeground(new java.awt.Color(255, 255, 255));
        btn_cari.setText("CARI");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 100, 50));

        tabel_daftar_menu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tabel_daftar_menu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NO.", "ID MENU", "NAMA KATEGORI", "JENIS KATEGORI", "HARGA", "STATUS", "KETERANGAN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_daftar_menu.setRowHeight(35);
        jScrollPane1.setViewportView(tabel_daftar_menu);
        if (tabel_daftar_menu.getColumnModel().getColumnCount() > 0) {
            tabel_daftar_menu.getColumnModel().getColumn(0).setMaxWidth(40);
            tabel_daftar_menu.getColumnModel().getColumn(1).setResizable(false);
            tabel_daftar_menu.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1180, 670));

        btn_edit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit.setText("EDIT");
        btn_edit.setToolTipText("");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 150, 50));

        btn_delete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("DELETE");
        btn_delete.setToolTipText("");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 50));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 1220, 870));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TAMBAH MENU");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel4.setOpaque(true);
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID MENU :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, -1));

        tf_id_menu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(tf_id_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 360, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("NAMA MENU :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 360, -1));

        tf_nama_menu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(tf_nama_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 360, 50));

        btn_simpan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel2.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 740, 360, 60));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("JENIS KATEGORI :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 360, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("HARGA :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 360, -1));

        tf_harga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tf_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_hargaActionPerformed(evt);
            }
        });
        jPanel2.add(tf_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 360, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("KETERANGAN");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 587, 360, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("STATUS :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 360, -1));

        tf_keterangan.setColumns(20);
        tf_keterangan.setRows(5);
        jScrollPane2.setViewportView(tf_keterangan);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 360, 100));

        cb_status.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 360, 50));

        cb_jenis_kategori.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cb_jenis_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cb_jenis_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 360, 50));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 870));

        jPanel3.setMinimumSize(new java.awt.Dimension(1660, 920));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(0, 0, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DAFTAR KATEGORI MENU");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel2.setOpaque(true);
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 60));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, 50));

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("REFRESH");
        jButton6.setToolTipText("");
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, 150, 50));

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("CARI");
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 100, 50));

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NO.", "ID KATEGORI", "NAMA KATEGORI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(35);
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMaxWidth(40);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1180, 670));

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("EDIT");
        jButton8.setToolTipText("");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 150, 50));

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("DELETE");
        jButton9.setToolTipText("");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 50));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 1220, 870));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(0, 0, 153));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("TAMBAH MENU");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel11.setOpaque(true);
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 60));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ID MENU :");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, -1));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel5.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 360, 50));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("NAMA MENU :");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 360, -1));

        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 360, 50));

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("SIMPAN");
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 740, 360, 60));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("JENIS KATEGORI :");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 360, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("HARGA :");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 360, -1));

        jTextField8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 360, 50));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("STATUS :");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 360, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("STATUS :");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 360, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 360, 100));

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 360, 50));

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 360, 50));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 870));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tf_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_hargaActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        int baris = tabel_daftar_menu.getSelectedRow();
        
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih data menu yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = tabModel.getValueAt(baris, 1).toString();
        String nama = tabModel.getValueAt(baris, 2).toString();
        
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus menu: " + nama + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM menu WHERE id_menu = ?";
            
            try (Connection conn = KoneksiDB.getKoneksi();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                 
                ps.setString(1, id);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, 
                    "Data menu berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                // Muat ulang data
                tampilData();
                resetForm();
                buatIdOtomatis();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, 
                    "Gagal menghapus data: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        int baris = tabel_daftar_menu.getSelectedRow();
        
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih data menu yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Ambil data dari tabel
        String id = tabModel.getValueAt(baris, 1).toString();
        String nama = tabModel.getValueAt(baris, 2).toString();
        String kategori = tabModel.getValueAt(baris, 3).toString();
        String harga = tabModel.getValueAt(baris, 4).toString();
        String status = tabModel.getValueAt(baris, 5).toString();
        // Cek jika keterangan null
        Object keteranganObj = tabModel.getValueAt(baris, 6);
        String keterangan = (keteranganObj == null) ? "" : keteranganObj.toString();
        
        // Masukkan data ke form
        tf_id_menu.setText(id);
        tf_nama_menu.setText(nama);
        cb_jenis_kategori.setSelectedItem(kategori);
        tf_harga.setText(harga);
        cb_status.setSelectedItem(status);
        tf_keterangan.setText(keterangan);
        
        // Ubah tombol
        btn_simpan.setText("UPDATE");
        tf_id_menu.setEditable(false);
    }//GEN-LAST:event_btn_editActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String id = tf_id_menu.getText();
        String nama = tf_nama_menu.getText();
        String kategori = cb_jenis_kategori.getSelectedItem().toString();
        String hargaStr = tf_harga.getText();
        String status = cb_status.getSelectedItem().toString();
        String keterangan = tf_keterangan.getText();
        
        // 2. Validasi input
        if (nama.isEmpty() || hargaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nama Menu dan Harga tidak boleh kosong!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int harga;
        try {
            harga = Integer.parseInt(hargaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Harga harus berupa angka!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 3. Cek mode (SIMPAN atau UPDATE)
        if (btn_simpan.getText().equals("SIMPAN")) {
            // Mode SIMPAN (Create)
            String sql = "INSERT INTO menu (id_menu, nama_menu, kategori, harga, status, keterangan) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            
            try (Connection conn = KoneksiDB.getKoneksi();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                 
                ps.setString(1, id);
                ps.setString(2, nama);
                ps.setString(3, kategori);
                ps.setInt(4, harga);
                ps.setString(5, status);
                ps.setString(6, keterangan);
                
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, 
                    "Data menu baru berhasil disimpan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, 
                    "Gagal menyimpan data menu: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            // ===== LOGIKA BARU: Mode UPDATE =====
            String sql = "UPDATE menu SET nama_menu = ?, kategori = ?, harga = ?, " + 
                         "status = ?, keterangan = ? WHERE id_menu = ?";
            
            try (Connection conn = KoneksiDB.getKoneksi();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                 
                ps.setString(1, nama);
                ps.setString(2, kategori);
                ps.setInt(3, harga);
                ps.setString(4, status);
                ps.setString(5, keterangan);
                ps.setString(6, id); // WHERE clause
                
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, 
                    "Data menu berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, 
                    "Gagal memperbarui data menu: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // 4. Kembalikan ke keadaan awal
        tampilData();
        resetForm();
        buatIdOtomatis();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        String kataKunci = tf_pencarian.getText();
        tabModel.setRowCount(0); // Kosongkan tabel
        
        String sql = "SELECT * FROM menu WHERE " +
                     "id_menu ILIKE ? OR " +
                     "nama_menu ILIKE ? OR " +
                     "kategori ILIKE ?";
                     
        // Note: ILIKE adalah case-insensitive LIKE (khusus PostgreSQL)
        // Ganti ke LIKE jika database Anda case-sensitive
        
        try (Connection conn = KoneksiDB.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            String keyword = "%" + kataKunci + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ps.setString(3, keyword);
            
            ResultSet rs = ps.executeQuery();
            
            int no = 1;
            while(rs.next()){
                tabModel.addRow(new Object[]{
                    no++,
                    rs.getString("id_menu"),
                    rs.getString("nama_menu"),
                    rs.getString("kategori"),
                    rs.getInt("harga"),
                    rs.getString("status"),
                    rs.getString("keterangan")
                });
            }
            
            if (tabModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "Data tidak ditemukan", "Pencarian", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Terjadi error saat mencari data: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        tf_pencarian.setText("");
        tampilData();
        resetForm();
        buatIdOtomatis();
    }//GEN-LAST:event_btn_refreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cb_jenis_kategori;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTable tabel_daftar_menu;
    private javax.swing.JTextField tf_harga;
    private javax.swing.JTextField tf_id_menu;
    private javax.swing.JTextArea tf_keterangan;
    private javax.swing.JTextField tf_nama_menu;
    private javax.swing.JTextField tf_pencarian;
    // End of variables declaration//GEN-END:variables
}
