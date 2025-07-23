package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.LinkedList;

public class OdaLogVeriOkuKaydet {
	public static void odaLogKaydet(String dosyaAdi, LinkedList<String> odaLoglar, boolean eklemeModu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi, eklemeModu))) {
            for (String odaLog : odaLoglar) {
                writer.write(odaLog);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oda Log kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }

    public static LinkedList<String> odaLogOku(String dosyaAdi) {
        LinkedList<String> odaLoglar = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
            	odaLoglar.insertLast(satir);
            }
        } catch (IOException e) {
            System.out.println("Oda Log okunurken bir hata oluştu: " + e.getMessage());
        }
        return odaLoglar;
    }

}
