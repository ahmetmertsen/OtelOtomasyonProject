package service;

import model.Rezervasyon;
import model.KisiBilgileri;
import model.Oda;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.LinkedList;

public class RezervasyonVeriOku {

    public static LinkedList<Rezervasyon> rezervasyonlariOku(String dosyaAdi) {
        LinkedList<Rezervasyon> rezervasyonlar = new LinkedList<>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy"); 

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] veri = satir.split(";");
                try {
                    int rezervasyonId = Integer.parseInt(veri[0]);
                    int kisiSayisi = Integer.parseInt(veri[1]);
                    String odaNumarasi = veri[2];
                    String odaTipi = veri[3];
                    double fiyat = Double.parseDouble(veri[4]);

                    Date girisTarihi = dateFormatter.parse(veri[5]);
                    Date cikisTarihi = dateFormatter.parse(veri[6]);

                    LinkedList<KisiBilgileri> kisiBilgileri = KisiBilgileri.parseKisiBilgileri(veri[7]);

                    Oda oda = new Oda(odaNumarasi, odaTipi, fiyat);

                    Rezervasyon rezervasyon = new Rezervasyon(rezervasyonId, kisiSayisi, oda, girisTarihi, cikisTarihi, kisiBilgileri);

                    rezervasyonlar.insertLast(rezervasyon);

                } catch (ParseException e) {
                    System.err.println("Tarih işlenirken hata oluştu: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Veri formatı hatası: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
        }
        return rezervasyonlar;
    }
    
    public static int getMaxRezervasyonId(String dosyaAdi) {
        int maxId = 0; 
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] veri = satir.split(";");
                try {
                    int rezervasyonId = Integer.parseInt(veri[0].trim());
                    if (rezervasyonId > maxId) {
                        maxId = rezervasyonId;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("ID format hatası: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
        }
        return maxId;
    }
}
