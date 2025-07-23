package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.LinkedList;
import model.Oda;

public class OdaVeriOku {

	public static LinkedList<Oda> odalariOku(String dosyaAdi) {
        LinkedList<Oda> odalar = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] veri = satir.split(";");
                Oda oda = new Oda(
                        veri[0],
                        veri[1],
                        Double.parseDouble(veri[2]),
                        Boolean.parseBoolean(veri[3]),
                        Boolean.parseBoolean(veri[4])
                );
                odalar.insertLast(oda);
            }
        } catch (IOException e) {
            System.out.println("Oda okunurken bir hata oluştu: " + e.getMessage());
        }
        return odalar;
    }
    
    public static LinkedList<Oda> getBosOdalar(LinkedList<Oda> odalar) {
        LinkedList<Oda> bosOdalar = new LinkedList<>();
        for (Oda oda : odalar) {
            if (!oda.getDoluluk() && oda.getDurum()) { // Doluluk false ve durum true olan odalar
                bosOdalar.insertLast(oda);
            }
        }
        return bosOdalar;
    }
    
    
}
