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
    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                // Pastikan nama database Anda sudah benar
                String url = "jdbc:postgresql://localhost:5432/db_ryn_coffee";
                String user = "postgres"; // Ganti dengan username PostgreSQL Anda
                String password = "muha8420"; // GANTI DENGAN PASSWORD ANDA

                // Tidak perlu Class.forName() di JDBC modern
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi ke PostgreSQL berhasil!");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Gagal koneksi ke database: " + e.getMessage(), 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        return koneksi;
    }
}
