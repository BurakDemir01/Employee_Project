package Veritabani;

import Modeller.Calisanlar.Calisan;
import Modeller.Departmanlar.Departman;

import java.util.ArrayList;

public class Calisanlar {


    // Buradaki calisanList static cünkü proje calismaya basladiği anda oluşması lazım.
    // Bunu bir veritabani gibi görebiliriz. Calisanlarimizin hepsi bu liste icerisinde yer alacak.
    private static ArrayList<Calisan> calisanList = new ArrayList<>();

    // // Tüm çalışanları almak için
    public static ArrayList<Calisan> getCalisanList() {
        return calisanList;
    }


    public static void addACalisan(Calisan calisan) {
        Calisanlar.calisanList.add(calisan);
        // TODO Bir çalışan eklemek için gerekli method. addACalisan() methodunu doldurunuz


    }


    public static void deleteACalisanWithId(String calisanId) {

        // TODO Bir çalışan silmek için gerekli method. deleteACalisanWithId() methodunu doldurunuz
        for (int i = 0; i < calisanList.size(); i++) {
            if (calisanList.get(i).getCalisanId().equals(calisanId)) {
                calisanList.remove(calisanList.get(i));
                System.out.println(calisanId + " Başarı ile silindi. Onceki Menuye aktariliyorsunuz....");
            }else System.out.println("silmek istediginiz calisanId ye ait kayit bulunamadi");//Degisiklik


        }

    }
        public static void printDepartmandakiCalisanlar(String depertmanKodu){

            // TODO  Departman kodu verilerek, konsola sadece o departmanda calisanlari yazdirmak için
            //       printDepartmandakiCalisanlar() methodunu doldurunuz

            Departman d = null;
            for (Departman departman : Departmanlar.getDepartmanList()) {
                if (departman.getDepartmanKodu().equals(depertmanKodu)) {
                    d = departman;
                }
            }
            //bir for daha yapilacak
          for (Calisan calisan:calisanList){ // Tuba:degisiklik yaptim
              if (calisan.getDepartman().equals(d))
                  System.out.println(calisan);
          }



        }


        public static void printCalisanlar () {

            // TODO  Calisanlari konsola yazdirmak için printCalisanlar() methodunu doldurun
            for (Calisan calisan : calisanList) {
                System.out.println(calisan.toString());
            }
        }


    }

