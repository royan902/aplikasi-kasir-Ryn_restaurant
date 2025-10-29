/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author Acer Aspire Lite 15
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    
    // ===== PERUBAHAN BESAR DI SINI =====
    // Kita tidak lagi menyimpan koneksi dalam variabel statis.
    // private static Connection koneksi; <-- BARIS INI DIHAPUS

    /**
     * Method ini sekarang SELALU MEMBUAT KONEKSI BARU.
     * Ini adalah cara yang benar untuk bekerja dengan try-with-resources
     * di setiap panel/form.
     */
    public static Connection getKoneksi() {
        Connection koneksiBaru = null; // Buat variabel lokal
        try {
            String url = "jdbc:postgresql://localhost:5432/db_ryn_coffee";
            String user = "postgres"; // Ganti dengan username PostgreSQL Anda
            String password = "muha8420"; // Password Anda dari file sebelumnya

            koneksiBaru = DriverManager.getConnection(url, user, password);
            // System.out.println("Koneksi baru ke PostgreSQL berhasil dibuat!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Gagal koneksi ke database: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return koneksiBaru; // Kembalikan koneksi yang baru dibuat
    }
    // ===================================
}