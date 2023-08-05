import java.util.*;

public class UyeManager extends Veritabani {
    static Scanner scan = new Scanner(System.in);

    public static void uyeMenu() throws InterruptedException {
        String tercih = "";

        do { //TODO Kullanıcı Çıkış Yapmadığı Sürece, Menüyü Görmeye Devam Etsin...

            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                            "================= UYE MENU =================\n" +
                            "\n" +
                            "\t   1- Uye Listesi Yazdir\t\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Bilgilerini Girerek Uye Ekleme\n" +
                            "\t   5- Kimlik No Ile Kayit Silme \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS\n");
            tercih = scan.next();
            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...
            switch (tercih) {

                // Uye Listesi Yazdir
                case "1":
                    uyeListesiYazdir();
                    break;
                // Soyisimden Uye Bulma
                case "2":
                    soyisimdenUyeBulma();
                    break;
                // Sehre Gore Uye Bulma
                case "3":
                    sehreGoreUyeBulma();
                    break;
                // Bilgilerini Girerek Uye Ekleme
                case "4":
                    uyeEkleme();
                    break;
                // Kimlik No Ile Kayit Silme
                case "5":
                    tcNoIleUyeSil();
                    break;
                case "A":
                    Helper.anaMenu();
                    break;
                case "Q":
                    Helper.projeDurdur();
                    break;
                default:
                    System.out.println("Lutfen gecerli tercih yapiniz: ");
            }
        } while (!tercih.equalsIgnoreCase("q"));
        Helper.projeDurdur();
    }

    private static void tcNoIleUyeSil() throws InterruptedException { // çalıştı
        System.out.print("Silinecek uyeye ait kimlik no giriniz: ");
        Scanner oku = new Scanner(System.in);
        String silinecekKisi = oku.nextLine();

        System.out.print(silinecekKisi + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        String silinecekValue = uyelerMap.get(silinecekKisi);
        String sonucValue = uyelerMap.remove(silinecekKisi);
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Istediginiz Tc numarasi ile uye bulunamadi.");
        }
        uyeMenu();
    }

    public static void uyeEkleme() throws InterruptedException {    // çalıştı

        //TODO Kullanıcıdan Tc no , Isim, Soyisim, Sehir, Dogum Yili alınız...
        //TODO Aldığınız değeri UyelerMap mapine uygun şekilde ekleyiniz...
        Scanner oku = new Scanner(System.in);

        System.out.println("TC NO giriniz: ");
        String tcNo = oku.nextLine();

        System.out.println("Isim giriniz: ");
        String ad = oku.nextLine();

        System.out.println("Soyad giriniz: ");
        String soyad = oku.nextLine();

        System.out.println("Şehir giriniz: ");
        String sehir = oku.nextLine();

        System.out.println("Doğum tarihi giriniz: ");
        String dogumT = oku.nextLine();


        uyelerMap.put(tcNo, ad + "," + soyad + "," + sehir + "," + dogumT);
        uyeMenu();

    }

    public static void sehreGoreUyeBulma() throws InterruptedException { // çalıştı

        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde şehir araması yapın;
        //TODO Girilen şehire sahip tüm üyeleri map veya liste olarak döndürünüz...
        System.out.println("Aradiginiz Uyenin Sehrini Giriniz:");
        Scanner oku = new Scanner(System.in);
        String arananSehir =oku.nextLine();

        System.out.println(
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                        "=============== SEHIR ILE UYE ARAMA ===============\n" +
                        "TcNo :         Isim ,  Soyisim ,   Sehir,  D.Yili");
        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir... Zorunlu değil...
        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        for (Map.Entry<String, String> entry : uyelerEntrySet) {
            String sehir = entry.getValue().split(",")[2];

            if (sehir.contains(arananSehir)) {
                System.out.println(
                        " " + entry.getKey() + " "
                                + entry.getValue().split(",")[0] + " "
                                + entry.getValue().split(",")[1] + " "
                                + entry.getValue().split(",")[2] + " "
                                + entry.getValue().split(",")[3]);
            }
        }

    }

    public static void soyisimdenUyeBulma() throws InterruptedException { // çalıştı
        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde soyisim araması yapın;
        //TODO Girilen soyismine sahip tüm üyeleri map veya liste olarak döndürünüz...
        //TODO Syisminin bir kısmı girilse bile bulunan üyeler listelensin...

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.println("Aradiginiz uyenin soyisminin tamamini ya da birkismini giriniz: ");
        Scanner oku = new Scanner(System.in);
        String arananSoyisim = oku.nextLine();

        for (Map.Entry<String, String> entry : uyelerEntrySet) {
            String tcNo = entry.getKey();
            String isim = entry.getValue().split(",")[0];
            String soyisim = entry.getValue().split(",")[1];
            String sehir = entry.getValue().split(",")[2];
            String dogumT = entry.getValue().split(",")[3];

            if (soyisim.contains(arananSoyisim)) {
                System.out.println(
                        "\nKitap Adı    :   " + tcNo + ",  " + isim + ", " + soyisim + ", " + sehir + ", " + dogumT);
            }
        }
        uyeMenu();
    }

    public static void uyeListesiYazdir() throws InterruptedException { // çalışıyor
        //İPUCU METODU: Bu metodu değiştirmenize gerek yok...

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print("Uye Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                        "=============== UYE LISTESI ================\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir...
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
        uyeMenu();
    }
}
