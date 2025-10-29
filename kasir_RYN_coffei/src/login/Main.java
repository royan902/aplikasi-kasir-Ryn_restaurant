/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

/**
 *
 * @author Acer Aspire Lite 15
 */
import config.SessionManager;
// import login.halaman_login; // Tidak perlu impor, karena sudah 1 package
import admin.dashboard_admin;
import kasir.dashboard_kasir;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        // Menjalankan di thread UI Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Cek apakah sudah ada session yang tersimpan
                if (SessionManager.isLoggedIn()) {
                    
                    // 2. Jika sudah login, cek role
                    String role = SessionManager.getRole();
                    
                    if ("admin".equals(role)) {
                        // Langsung buka dashboard admin
                        new dashboard_admin().setVisible(true);
                    } else if ("kasir".equals(role)) {
                        // Langsung buka dashboard kasir
                        new dashboard_kasir().setVisible(true);
                    } else {
                        // Role tidak dikenal, suruh login ulang
                        new halaman_login().setVisible(true);
                    }
                    
                } else {
                    // 3. Jika belum login, buka halaman login
                    new halaman_login().setVisible(true);
                }
            }
        });
    }
}
