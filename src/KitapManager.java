import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KitapManager extends Veritabani {

    static Scanner scan = new Scanner(System.in);

    public static void kitapMenu() throws InterruptedException {
        String tercih = "";
        do {// TODO Kullanıcı Çıkış Yapmadığı sürece menüde kalalım...
            System.out.println(
                    "\n============ TECHNO STUDY BOOTCAMP ============\n" +
                            "================== KITAP MENU =================\n" +
                            "\t   1- Kitap Listesi Yazdir\n" +
                            "\t   2- Yazardan Kitap Bulma\n" +
                            "\t   3- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   4- Bilgilerini Girerek Kitap Ekleme\n" +
                            "\t   5- Kitap Ismi Ile Kayit Silme \t\n" +
                            "\t   6- Kitap Odunc Al \t\n" +
                            "\t   7- Kitap Iade Et \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");
            System.out.print("İşleminizi giriniz: ");
            tercih = scan.nextLine().toLowerCase();
            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...

            switch (tercih) {
                case "1":
                    kitapListesiYazdir();
                    break;
                // Yazar Ismiyle Kitap Bulma
                case "2":
                    yazardanKitapBulma();
                    break;
                // Kitap Turu veya Yayin Tarihi Ile Kitap Bulma
                case "3":
                    turVeyaYayinTarihiIleKitapBulma();
                    break;
                // Bilgilerini Girerek Kitap Ekleme
                case "4":
                    kitapEkle();
                    break;
                case "5":
                    isimIleKitapSilme();
                    break;
                case "6":
                    kitapOduncAl();
                    break;
                case "7":
                    kitapIadeEt();
                    break;
                case "A":
                    Helper.anaMenu();
                    break;
                case "Q":
                    Helper.projeDurdur();
                    break;
                default:
                    System.out.println("Lutfen gecerli bir tercih giriniz");
            }
        } while (tercih.equalsIgnoreCase("q"));
        Helper.projeDurdur();

    }

    public static void kitapOduncAl() throws InterruptedException {     //çalıştı
        System.out.println("Odunc almak istediginiz kitabin ismini giriniz: ");
        String kitapAd = scan.nextLine();

        if (!kitaplarMap.containsKey(kitapAd)) {
            System.out.println("Böyle bir kitap mevcut değil.");
        }

        oduncKitapMap.put(kitapAd, kitaplarMap.get(kitapAd));
        kitaplarMap.remove(kitapAd);

        System.out.println(kitapAd + " kitabını ödünç aldınız.");

        kitapMenu();
        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap ödünç alma metodunu tamamlayın...
        //NOT: Veritabanı'nda ödünç almayla alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Ödünç alındığında kitaplarMap 'ten düşüp bu map e eklenecek...
    }


    public static void kitapIadeEt() throws InterruptedException {  //çalıştı
        System.out.println("Iade etmek istediginiz kitabin ismini giriniz: ");
        String kitapAd = scan.nextLine();

        if (oduncKitapMap.containsKey(kitapAd))
        {
            kitaplarMap.put(kitapAd, oduncKitapMap.get(kitapAd));
            oduncKitapMap.remove(kitapAd);
            System.out.println(kitapAd + " kitabını iade ettiniz.");

        }else
            System.out.println("Yanlış isim girdiniz.");
        kitapMenu();
        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap iade etme metodunu tamamlayın...
        //NOT: Veritabanı'nda iade etmeyle alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Kitap iade edildiğinde,  kitaplarMap 'e geri eklenmeli...
    }


    private static void isimIleKitapSilme() throws InterruptedException {//İPUCU METODU... Bu metodu değiştirmenize gerek yok.
        System.out.println("Silinecek kitabin ismini giriniz");
        String silinecekKitap = scan.nextLine();

        String silinecekValue = kitaplarMap.get(silinecekKitap);
        String sonucValue = kitaplarMap.remove(silinecekKitap);

        System.out.print(silinecekKitap + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        //ARTIK UYGUN YERLERDE BEKLEMEDİĞİNİZ SONUÇLAR İÇİN TRY CATCH KULLANABİLİRSİNİZ...
        //////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Istediginiz kitap ismi bulunamadi");
        }/////////////////////////////////////////////////////////////////////////////////////////////////
        kitapMenu();
    }

    private static void kitapEkle() throws InterruptedException {   //çalıştı
        //"A Tale of Two Cities", "Charles Dickens, Tarih, 1859" >> Kitap key,value su buna benzer şekilde...

        //TODO Kitap Adını, Yazar Adını, Kitap Türünü ve Yayınlanma Yılını Kullanıcıdan alarak,
        //TODO kitaplarMap'e ekleme yapınız...

        //TODO MÜMKÜNSE, kitap türünü, Enum olarak tanımlanan KitapTuru sınıfını kullanarak alınız...
        //KİTAP TÜRLERİ >>     TARIH, POLISIYE, KURGU, ROMAN, DESTAN, TANIMLANMAMIS_TUR
        //TODO Kullanıcı kitap türünü yanlış girdiği sürece , kullanıcıya
        //TODO "Hatali giris! Lutfen kitap turunu tekrar giriniz: " mesajı verin...
        //TODO while ve try-catch kullanılabilir... Giriş başarılı olursa try-catch bloğu kırılarak konsoldan
        //TODO yayınlama yılı alıp kitap ekleme işlemine devam edilebilir...
        //Kullanıcı tarafından girilen stringi, KitapTuru sınıfında tanımlanan türlerden birine çevirmeniz gerkecek...
        // kitapTuru değişkeni artık geçerli bir değere sahipse...
        // Diğer işlemlere devam edebilirsiniz.
        //System.out.println("Yayinlanma Tarihi: ");
        //String yayinTarihi = scan.nextLine();

        //TODO Ekleme işlemini tamamlayın...
        System.out.println("Kitap Adını giriniz: ");
        String kitapAdi = scan.nextLine();

        System.out.println("Yazar Adını giriniz: ");
        String yazarAdi = scan.nextLine();

        System.out.println("Kitap Türünü giriniz: ");
        String kitapTuru = scan.nextLine();

        System.out.println("Yayınlanma Yılını giriniz: ");
        String yayinTarihi = scan.nextLine();

        kitaplarMap.put(kitapAdi, yazarAdi + "," + kitapTuru + "," + yayinTarihi);
        System.out.println(kitapAdi + " kitabını eklediniz.");

        kitapMenu();
    }




    public static void turVeyaYayinTarihiIleKitapBulma() throws InterruptedException { // çalıştı

        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();

        // TODO Kullanıcıdan kitap türünü veya yayınlanma yılını alın...

        System.out.println("İstediğiniz kitabın türünü veya yayınlanma yılını yazın: ");
        System.out.println("(Tarih, Polisiye, Kurgu, Roman, Destan)");
        Scanner oku = new Scanner(System.in);
        String turVeyaYayinTarihi = oku.nextLine();

        // TODO Kitap türünü veya yayınlanma tarihini kullanarak kitap bulun...

        for (Map.Entry<String, String> entry : myEntrySet) {
            String kitapTuru = entry.getValue().split(",")[1];
            String yayinTarihi = entry.getValue().split(",")[2];

            if (kitapTuru.contains(turVeyaYayinTarihi)|| yayinTarihi.contains(turVeyaYayinTarihi)) {
                System.out.println(
                        " " + entry.getKey() + ", "
                                + entry.getValue().split(",")[0] + ", "
                                + entry.getValue().split(",")[1] + ", "
                                + entry.getValue().split(",")[2]
                );
            }
        }
        kitapMenu();
    }


    public static void yazardanKitapBulma() throws InterruptedException {   // çalıştı

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();

        // TODO Kullanıcıdan yazar adını alın...
        System.out.println("İstediğiniz yazarın adını yazın: ");
        String yazarAdi = scan.nextLine();

        // TODO Yazarın adını kullanarak kitap bulun...
        for (Map.Entry<String, String> entry : myEntrySet) {
            String kitapAdi = entry.getKey();
            String yazar = entry.getValue().split(",")[0];
            String kitapTuru = entry.getValue().split(",")[1];
            String yayinTarihi = entry.getValue().split(",")[2];

            if (yazarAdi.equals(yazar)) {
                System.out.println(
                        "\nKitap Adı    :   " + kitapAdi + ",  "+yazar+ ", " + kitapTuru +", "+ yayinTarihi);
            }
        }
        kitapMenu();
    }

    public static void kitapListesiYazdir() throws InterruptedException { //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Adı , Kitap Turu , Yayınlanma Yılı");

        // TODO Kitapları listeleyecek metodu oluşturun...
        // Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...
        for (Map.Entry<String, String> entry : myEntrySet) {
            String kitapAdi = entry.getKey();
            String yazar = entry.getValue().split(",")[0];
            String kitapTuru = entry.getValue().split(",")[1];
            String yayinTarihi = entry.getValue().split(",")[2];

            System.out.println(
                    "\nKitap Adı    :   " + kitapAdi + ",  "+yazar+ ", " + kitapTuru +", "+ yayinTarihi);
        }
    kitapMenu();}
}
