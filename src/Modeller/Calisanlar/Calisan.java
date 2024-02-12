package Modeller.Calisanlar;

import Modeller.Departmanlar.Departman;
import Veritabani.Calisanlar;
import Veritabani.Departmanlar;

import java.util.ArrayList;

public class Calisan {

    private String calisanId;
    private String adSoyad;
    private int maas;
    private Departman Departman;
    private String isimKodu = "";

    public Calisan(String adSoyad, int maas, String departmanKodu) {
        this.adSoyad = adSoyad;
        this.maas = maas;
        this.setDepartman(departmanKodu);
        this.setCalisanId();    // constructor çalıştığında, aşağıda tanımlayacağınız bu metod vasıtasıyla tekil bi ID alacak...
        // Örn: Şirkette 257 calisan var, Bilişim teklonojileri departmaninda Mehmet Ali Bulut kaydedilecek olsun,
        // Mehmet Ali icin ID 'BTD258MAB' olmalıdır.
        Calisanlar.addACalisan(this);

    }

    // Terminalden girilen calisanin departman koduna göre, gerekli departman set edilmelidir.
    // Çalışan sınıfının Constructor 'ı main'de tanımlı. Program çalıştığında, terminalden gireceğimiz
    // departman kodu, buradaki metod (setDepartman) vasıtasıyla departman listesinin tümünü dolaşıp (foreach)
    // getDepartmanKodu() ile bulunan sonuçlardan biriyle aynı mı? Diye bakıyoruz...
    // Aynıysa, "terminalden girilen çalışanı bu departmana set et" demektir...


    private void setDepartman(String depKodu) {
        // TODO setDepartman() methodunu doldurunuz

        //  Terminalden girilen calisanin departman koduna göre, gerekli departman set edilmelidir.
        //  Çalışan sınıfının Constructor 'ı main'de tanımlı. Program çalıştığında, terminalden gireceğimiz
        //  departman kodu, buradaki metod (setDepartman) vasıtasıyla departman listesinin tümünü dolaşıp (foreach)
        //  getDepartmanKodu() ile bulunan sonuçlardan biriyle aynı mı? Diye bakıyoruz...
        //  Aynıysa, "terminalden girilen çalışanı bu departmana set et" demektir...
        //  İpucu: Departman listesinin (Veritabani.Departmanlar.DepartmanList) içerisindeki departmanların kodları var,
        //  bu kodlari donguye tutmak ise yarayabilir.

        for (Departman d : Departmanlar.getDepartmanList()) {
            if (d.getDepartmanKodu().equals(depKodu)) {
                this.Departman = d;
            }

        }
    }



    private void setCalisanId() {
        // TODO setCalisanId() methodunu doldurunuz
        // Calisanin ID sinin kendisine özel olduğundan bahsetmistik,
        // ID nin nasil kaydedileceği Readme Dosyasi içerisinde yer aliyor.

        this.calisanId = this.Departman.getDepartmanKodu() + (Calisanlar.getCalisanList().size() + 1)+getCalisanIsimKodu();//degisiklik isim kodu icin

    }

    private String getCalisanIsimKodu() {
        // TODO getCalisanIsimKOdu() methodunu doldurunuz
        // Calisanin ID sinin sonuna isim kodu eklenmesi için, ismi parçalayan bir method
        // Basit string metodlari ise isinize cok yarayacaktir fakat dinamik olmasina dikkat edelim...
        // Mesela 2 isim bir soyisim girildiğinde hata vermesin.

        this.isimKodu += this.adSoyad.charAt(0);

        for (int i = 0; i < this.adSoyad.length(); i++) {
            if (adSoyad.charAt(i) == ' ') {
                this.isimKodu += adSoyad.charAt(i+1);//Burada degisiklik yaptim bosluktan sonra karakteri eklesin diye
            }
        }
        return this.isimKodu;
    }


    // Calisanin id sini almak icin basit getter method
    public String getCalisanId() {
        return this.calisanId;
    }

    // Calisanin departmanini almak icin basit getter method
    public Departman getDepartman() {
        return this.Departman;
    }


    // Departman adini verebilmek için bir method
    public String getDepartmanAdi() {

        // TODO getDepartmanAdi() methodunu doldurunuz
        // İpucu: Departman Kodu YD ise departman adi Yonetim Departmani olarak kaydedilmelidir.

        String departman = "";
        if (this.Departman.getDepartmanKodu().equals("YD")) {
            departman = "Yönetim Departmanı";
        } else if (this.Departman.getDepartmanKodu().equals("BTD")) {
            departman = "Bilişim Teklonojileri Departmani";
        } else if (this.Departman.getDepartmanKodu().equals("IKD")) {
            departman = "Insan Kaynaklari Departmani";
        }

         return departman;// TODO burayi unutmayin???
    }

    // Calisana zam yapilmasi için gerekli bir method
    public static void zamYap(String calisanId) {

        // TODO zamYap() methodunu doldurunuz
        // İpucu:Calisan ID si kullanilarak yapilmalidir, diğer attributelarin aynilarindan 1 er tane daha olabilirdi.

        ArrayList<Calisan> calisanList = Calisanlar.getCalisanList();
        for (int i = 0; i < calisanList.size(); i++) {
            if (calisanList.get(i).getCalisanId().equals(calisanId)) {
                System.out.println("önceki maasi="+calisanList.get(i).maas);//degisiklik
                calisanList.get(i).maas += (calisanList.get(i).maas * calisanList.get(i).Departman.getZamOrani()) / 100;
                System.out.println("zamli maas="+calisanList.get(i).maas);//degisiklik
                System.out.println("Islem basari ile gerçekleşti, Onceki menuye aktariliyorsunuz");//Degisiklik
            } else System.out.println("calisanID ye ait kayit bulunmamistir");//Degisiklik
        }

    }

    // Calisanlari yazdırmak için gerekli bir override
    @Override
    public String toString() {

        // TODO toString() metheodunu doldurunuz
        // İpucu: Detayli anlatim EmployeesRequirements.pdf içerisinde.
        return "Calisan ID : '" + calisanId + '\'' +
                ", Isim Soyisim : '" + adSoyad + '\'' +
                ", Maas : '" + maas + '\'' +
                ", Departman : '" + getDepartmanAdi() + '\'';
        //  return  "???? " ;// TODO burayi unutmayin


    }
}
