package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.LinkedList;

public class RezervasyonLogVeriOkuKaydet {

    public static void rezervasyonLogKaydet(String dosyaAdi, LinkedList<String> rezervasyonLoglar, boolean eklemeModu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi, eklemeModu))) {
            for (String rezervasyonLog : rezervasyonLoglar) {
                writer.write(rezervasyonLog);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Rezervasyon Log kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }

    public static LinkedList<String> rezervasyonLogOku(String dosyaAdi) {
        LinkedList<String> rezervasyonLoglar = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
            	rezervasyonLoglar.insertLast(satir);
            }
        } catch (IOException e) {
            System.out.println("Rezervasyon Log okunurken bir hata oluştu: " + e.getMessage());
        }
        return rezervasyonLoglar;
    }
}
