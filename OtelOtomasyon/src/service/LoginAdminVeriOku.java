package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.LinkedList;
import model.LoginAdmin;

public class LoginAdminVeriOku {

    public static LinkedList<LoginAdmin> adminVerileriOku(String dosyaAdi) {
        LinkedList<LoginAdmin> adminListesi = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] veri = satir.split(";");
                LoginAdmin admin = new LoginAdmin(
                     veri[0],   
                     veri[1],    
                     veri[2]    
                );
                adminListesi.insertLast(admin);
            }
        } catch (IOException e) {
            System.out.println("Admin verileri okunurken bir hata oluştu: " + e.getMessage());
        }
        return adminListesi;
    }
    
    public static LoginAdmin adminBul(LinkedList<LoginAdmin> adminListesi, String kullaniciAdi) {
        for (LoginAdmin admin : adminListesi) {
            if (admin.getKullaniciAdi().equalsIgnoreCase(kullaniciAdi)) {
                return admin;
            }
        }
        return null; 
    }
} 
