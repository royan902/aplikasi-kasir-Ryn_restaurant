/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author Acer Aspire Lite 15
 */
import java.util.prefs.Preferences; // Import sudah benar

/**
 * Kelas ini mengelola sesi pengguna secara persisten.
 * Data akan tetap tersimpan bahkan setelah aplikasi ditutup.
 */
public class SessionManager {

    // Membuat node preferensi untuk aplikasi ini
    private static final Preferences prefs = Preferences.userNodeForPackage(SessionManager.class);

    // Kunci untuk menyimpan data
    private static final String ID_KEY = "user_id";
    private static final String USERNAME_KEY = "username";
    private static final String ROLE_KEY = "role";

    /**
     * Menyimpan data pengguna ke session (Preferences).
     * Dipanggil saat login berhasil.
     */
    public static void createSession(int id, String username, String role) {
        prefs.putInt(ID_KEY, id);
        // ===== PERBAIKAN DI SINI =====
        prefs.put(USERNAME_KEY, username); // Bukan putString
        prefs.put(ROLE_KEY, role);         // Bukan putString
        // =============================
        try {
            prefs.flush(); // Memastikan data langsung tersimpan
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Menghapus semua data sesi.
     * Dipanggil saat logout.
     */
    public static void clearSession() {
        try {
            prefs.remove(ID_KEY);
            prefs.remove(USERNAME_KEY);
            prefs.remove(ROLE_KEY);
            prefs.flush(); // Memastikan data terhapus
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mengecek apakah pengguna sudah login atau belum.
     * @return true jika sudah login, false jika belum.
     */
    public static boolean isLoggedIn() {
        // Kita cek berdasarkan 'role'. Jika 'role' tidak kosong, anggap sudah login.
        return !getRole().isEmpty();
    }

    // --- Getter untuk mengambil data sesi ---

    public static int getUserId() {
        return prefs.getInt(ID_KEY, 0); // 0 adalah nilai default jika tidak ada
    }

    public static String getUsername() {
        return prefs.get(USERNAME_KEY, ""); // "" adalah nilai default
    }

    public static String getRole() {
        return prefs.get(ROLE_KEY, ""); // "" adalah nilai default
    }
}