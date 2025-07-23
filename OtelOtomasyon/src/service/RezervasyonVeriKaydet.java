package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import model.LinkedList;
import model.KisiBilgileri;
import model.Rezervasyon;

public class RezervasyonVeriKaydet {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void rezervasyonKaydet(String dosyaAdi, LinkedList<Rezervasyon> rezervasyonListesi, boolean eklemeModu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi, eklemeModu))) {
            for (Rezervasyon rezervasyon : rezervasyonListesi) {
                StringBuilder kisiBilgileri = new StringBuilder();

                if (rezervasyon.getKisiBilgileri() != null && !rezervasyon.getKisiBilgileri().isEmpty()) {
                    for (int i = 0; i < rezervasyon.getKisiBilgileri().size(); i++) {
                        KisiBilgileri kisi = rezervasyon.getKisiBilgileri().get(i);
                        kisiBilgileri.append("KisiBilgileri{tcKimlik='")
                                     .append(kisi.getTcKimlik()).append("', adSoyad='")
                                     .append(kisi.getAdSoyad()).append("', dogumTarihi='")
                                     .append(kisi.getDogumTarihi()).append("'}");

                        if (i < rezervasyon.getKisiBilgileri().size() - 1) {
                            kisiBilgileri.append(",");
                        }
                    }
                } else {
                    kisiBilgileri.append("YOK");
                }

                String satir = rezervasyon.getRezervasyonId() + ";" +
                               rezervasyon.getKisiSayisi() + ";" +
                               rezervasyon.getOda().getOdaNumarasi() + ";" +
                               rezervasyon.getOda().getOdaTipi() + ";" +
                               rezervasyon.getOda().getFiyat() + ";" +
                               (rezervasyon.getGirisTarihi() != null ? DATE_FORMAT.format(rezervasyon.getGirisTarihi()) : "") + ";" +
                               (rezervasyon.getCikisTarihi() != null ? DATE_FORMAT.format(rezervasyon.getCikisTarihi()) : "") + ";" +
                               kisiBilgileri.toString();

                writer.write(satir);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Rezervasyon kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }
    

}
