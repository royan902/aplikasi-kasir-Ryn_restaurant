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

public class daftar_metode_pembayaran extends javax.swing.JPanel {

    /**
     * Creates new form kategoti
     */
    private DefaultTableModel tabModel;
    
    public daftar_metode_pembayaran() {
        initComponents();
        
        setupTabel();           
        loadMetodePembayaran(); 
        tampilData();           
        resetForm();            // ===== PERUBAHAN ===== Cukup panggil resetForm (yang sudah berisi ID otomatis)
    }
    
    private void setupTabel() {
        tabModel = new DefaultTableModel();
        tabModel.addColumn("NO.");
        tabModel.addColumn("ID AKUN");
        tabModel.addColumn("METODE PEMBAYARAN");
        tabModel.addColumn("NAMA PEMBAYARAN");
        tabModel.addColumn("KETERANGAN");
        
        tabel_pembayaran.setModel(tabModel);
        
        tabel_pembayaran.getColumnModel().getColumn(0).setMaxWidth(40);
        tabel_pembayaran.getColumnModel().getColumn(1).setMinWidth(70);
    }
    
    private void loadMetodePembayaran() {
        cb_metode_pembayaran.removeAllItems(); 
        
        String sql = "SELECT nama_metode FROM metode_pembayaran ORDER BY nama_metode ASC";
        
        try (Connection conn = KoneksiDB.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                cb_metode_pembayaran.addItem(rs.getString("nama_metode"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Gagal memuat data metode pembayaran: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void tampilData() {
        tabModel.setRowCount(0); 
        
        String sql = "SELECT * FROM akun_pembayaran ORDER BY id_akun_pembayaran ASC";
        
        try (Connection conn = KoneksiDB.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            int no = 1;
            while(rs.next()){
                tabModel.addRow(new Object[]{
                    no++,
                    rs.getInt("id_akun_pembayaran"), 
                    rs.getString("metode_pembayaran"),
                    rs.getString("nama_pembayaran"),
                    rs.getString("keterangan")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Terjadi error saat memuat data akun: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ===== PERUBAHAN ===== Method resetForm sekarang memanggil buatIdOtomatis
    private void resetForm() {
        tf_id_akun_pembayaran.setText("");
        tf_id_akun_pembayaran.setEditable(false); 
        
        tf_nama_pembayaran.setText("");
        tf_keterangan.setText("");
        
        if (cb_metode_pembayaran.getItemCount() > 0) {
            cb_metode_pembayaran.setSelectedIndex(0);
        }
        
        btn_simpan.setText("SIMPAN");
        buatIdOtomatis(); // Panggil method pembuat ID di akhir
    }

    // ===== METHOD BARU DITAMBAHKAN (Logika untuk Bug ID) =====
    private void buatIdOtomatis() {
        // ID ini hanya untuk TAMPILAN. Saat INSERT, kita tetap mengandalkan auto-increment DB
        String sql = "SELECT MAX(id_akun_pembayaran) AS id_terakhir FROM akun_pembayaran";
        
        try (Connection conn = KoneksiDB.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int idTerakhir = rs.getInt("id_terakhir");
                int idBaru = idTerakhir + 1;
                
                // Jika idTerakhir = 0 (tabel kosong), maka idBaru akan 1
                tf_id_akun_pembayaran.setText(String.valueOf(idBaru));
                
            } else {
                // Fallback jika query gagal
                tf_id_akun_pembayaran.setText("1");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Gagal membuat ID Akun otomatis: " + e.getMessage(), 
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
        tabel_pembayaran = new javax.swing.JTable();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_id_akun_pembayaran = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_nama_pembayaran = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_keterangan = new javax.swing.JTextArea();
        cb_metode_pembayaran = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(1660, 920));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DAFTAR AKUN METODE PEMBAYARAN");
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

        tabel_pembayaran.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tabel_pembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NO.", "ID AKUN PEMBAYARAN", "METODE_PEMBAYARAN", "NAMA_PEMBAYARAN", "KETERANGAN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_pembayaran.setRowHeight(35);
        jScrollPane1.setViewportView(tabel_pembayaran);
        if (tabel_pembayaran.getColumnModel().getColumnCount() > 0) {
            tabel_pembayaran.getColumnModel().getColumn(0).setMaxWidth(40);
            tabel_pembayaran.getColumnModel().getColumn(1).setResizable(false);
            tabel_pembayaran.getColumnModel().getColumn(2).setResizable(false);
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TAMBAH AKUN PEMBAYARAN");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel4.setOpaque(true);
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID AKUN PEMBAYARAN :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, -1));

        tf_id_akun_pembayaran.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(tf_id_akun_pembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 360, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("NAMA PEMBAYARAN :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 360, -1));

        tf_nama_pembayaran.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tf_nama_pembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nama_pembayaranActionPerformed(evt);
            }
        });
        jPanel2.add(tf_nama_pembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 360, 50));

        btn_simpan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        jPanel2.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 360, 60));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("JENIS METODE PEMBAYARAN :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 360, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("KETERANGAN :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 360, 30));

        tf_keterangan.setColumns(20);
        tf_keterangan.setRows(5);
        jScrollPane2.setViewportView(tf_keterangan);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 360, 100));

        cb_metode_pembayaran.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cb_metode_pembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cb_metode_pembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 360, 50));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 870));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        int baris = tabel_pembayaran.getSelectedRow();
        
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = tabModel.getValueAt(baris, 1).toString();
        String nama = tabModel.getValueAt(baris, 3).toString();
        
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus akun: " + nama + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM akun_pembayaran WHERE id_akun_pembayaran = ?";
            
            try (Connection conn = KoneksiDB.getKoneksi();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                 
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, 
                    "Data akun berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                tampilData();
                resetForm();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, 
                    "Gagal menghapus data: " + e.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        int baris = tabel_pembayaran.getSelectedRow();
        
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih data yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = tabModel.getValueAt(baris, 1).toString();
        String metode = tabModel.getValueAt(baris, 2).toString();
        String nama = tabModel.getValueAt(baris, 3).toString();
        Object keteranganObj = tabModel.getValueAt(baris, 4);
        String keterangan = (keteranganObj == null) ? "" : keteranganObj.toString();
        
        tf_id_akun_pembayaran.setText(id);
        tf_nama_pembayaran.setText(nama);
        cb_metode_pembayaran.setSelectedItem(metode);
        tf_keterangan.setText(keterangan);
        
        btn_simpan.setText("UPDATE");
        tf_id_akun_pembayaran.setEditable(false);
    }//GEN-LAST:event_btn_editActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
       String kataKunci = tf_pencarian.getText();
        tabModel.setRowCount(0); 
        
        String sql = "SELECT * FROM akun_pembayaran WHERE " +
                     "CAST(id_akun_pembayaran AS TEXT) ILIKE ? OR " +
                     "nama_pembayaran ILIKE ? OR " +
                     "metode_pembayaran ILIKE ?";
        
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
                    rs.getInt("id_akun_pembayaran"),
                    rs.getString("metode_pembayaran"),
                    rs.getString("nama_pembayaran"),
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
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void tf_nama_pembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nama_pembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nama_pembayaranActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String idStr = tf_id_akun_pembayaran.getText();
        String nama = tf_nama_pembayaran.getText();
        String metode = cb_metode_pembayaran.getSelectedItem().toString();
        String keterangan = tf_keterangan.getText();
        // String id = tf_id_akun_pembayaran.getText(); // Baris ini tidak diperlukan lagi

        if (nama.isEmpty() || metode.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nama dan Metode Pembayaran tidak boleh kosong!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ===== PERUBAHAN ===== Konversi ID ke integer dan validasi
        int id;
        try {
             id = Integer.parseInt(idStr);
        } catch (NumberFormatException e){
             JOptionPane.showMessageDialog(this,
                "ID Akun Pembayaran tidak valid!", "Input Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
        // ===============================================

        if (btn_simpan.getText().equals("SIMPAN")) {
            // Mode SIMPAN (Create)
            // ===== PERUBAHAN ===== Tambahkan 'id_akun_pembayaran' ke query INSERT
            String sql = "INSERT INTO akun_pembayaran (id_akun_pembayaran, metode_pembayaran, nama_pembayaran, keterangan) " +
                         "VALUES (?, ?, ?, ?)";

            try (Connection conn = KoneksiDB.getKoneksi();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                // ===== PERUBAHAN ===== Set parameter ID
                ps.setInt(1, id);
                ps.setString(2, metode);
                ps.setString(3, nama);
                ps.setString(4, keterangan);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                    "Akun pembayaran baru berhasil disimpan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                // ===== PERUBAHAN ===== Tambahkan cek PKEY violation
                 if(e.getMessage().contains("akun_pembayaran_pkey")) { // Cek primary key violation
                    JOptionPane.showMessageDialog(this,
                        "ID Akun Pembayaran '" + id + "' sudah ada. Terjadi kesalahan pada ID otomatis.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                 } else {
                     JOptionPane.showMessageDialog(this,
                        "Gagal menyimpan data: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                 }
                 // ===============================================
            }

        } else {
            // Mode UPDATE (Tidak perlu diubah, sudah benar)
            String sql = "UPDATE akun_pembayaran SET metode_pembayaran = ?, nama_pembayaran = ?, keterangan = ? " +
                         "WHERE id_akun_pembayaran = ?";

            try (Connection conn = KoneksiDB.getKoneksi();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, metode);
                ps.setString(2, nama);
                ps.setString(3, keterangan);
                ps.setInt(4, id); // WHERE clause sudah benar

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                    "Data akun berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                    "Gagal memperbarui data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        tampilData();
        resetForm();
    }//GEN-LAST:event_btn_simpanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cb_metode_pembayaran;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tabel_pembayaran;
    private javax.swing.JTextField tf_id_akun_pembayaran;
    private javax.swing.JTextArea tf_keterangan;
    private javax.swing.JTextField tf_nama_pembayaran;
    private javax.swing.JTextField tf_pencarian;
    // End of variables declaration//GEN-END:variables
}
