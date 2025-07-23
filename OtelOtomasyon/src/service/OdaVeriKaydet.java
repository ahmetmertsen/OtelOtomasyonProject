package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import model.LinkedList;
import model.Oda;

public class OdaVeriKaydet {

    public static void odaKaydet(String dosyaAdi, LinkedList<Oda> odalar, boolean eklemeModu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi, eklemeModu))) {
            for (Oda oda : odalar) {
                String satir = oda.getOdaNumarasi() + ";" +
                               oda.getOdaTipi() + ";" +
                               oda.getFiyat() + ";" +
                               oda.getDoluluk() + ";" +
                               oda.getDurum();
                writer.write(satir);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oda kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }
}
