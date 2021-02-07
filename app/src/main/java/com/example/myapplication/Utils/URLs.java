package com.example.myapplication.Utils;

public class URLs {
    public static final String URL = "http://tigadara.site/tiga_dara";

    //user
    public static final String LOGIN = URL+"/user/login.php";
    public static final String CREATEUSER = URL+"/user/create_user.php";
    public static final String READUSER = URL+"/user/read_user.php";
    public static final String DELETEUSER = URL+"/user/delete_user.php";

    //meja
    public static final String CREATEMEJA = URL+"/meja/create_meja.php";
    public static final String READMEJA = URL+"/meja/read_meja.php";
    public static final String DELETEMEJA = URL+"/meja/delete_meja.php";

    //menu
    public static final String CREATEMENU = URL+"/menu/create_menu.php";
    public static final String GETMAKANAN = URL+"/menu/read_makanan.php";
    public static final String GETMINUMAN = URL+"/menu/read_minuman.php";
    public static final String GETPAKET = URL+"/menu/read_paket.php";
    public static final String DELETEMENU = URL+"/menu/delete_menu.php";

    //pesanan
    public static final String CREATEPESANAN = URL+"/pesanan/create_pesanan.php";
    public static final String GETCART = URL+"/pesanan/read_pesanan_cart.php";
    public static final String GETNOTRANS = URL+"/pesanan/get_notrans.php";
    public static final String TOTALBAYAR = URL+"/pesanan/total_bayar.php";
    public static final String TOTALBAYARKASIR = URL+"/pesanan/total_bayar_kasir.php";
    public static final String KONFIRMASI = URL+"/pesanan/edit_status_cart.php";
    public static final String BAYAR = URL+"/pesanan/edit_status_bayar.php";
    public static final String SELESAI = URL+"/pesanan/edit_status_selesai.php";
    public static final String ANTAR = URL+"/pesanan/edit_status_antar.php";
    public static final String GETKASIR = URL+"/pesanan/read_pesanan_kasir.php";
    public static final String GETBAYAR = URL+"/pesanan/read_pesanan_bayar.php";
    public static final String GETKEMBALIAN = URL+"/pesanan/read_pesanan_kembalian.php";
    public static final String GETDAPUR = URL+"/pesanan/read_pesanan_dapur.php";
    public static final String GETLAPORAN = URL+"/pesanan/read_pesanan_laporan.php";
    public static final String GETLAPORANDETAIL = URL+"/pesanan/read_pesanan_laporan_detail.php";
    public static final String GETANTAR = URL+"/pesanan/read_pesanan_antar.php";
    public static final String GETANTARDETAIL = URL+"/pesanan/read_pesanan_antar_detail.php";
    public static final String DELETECART = URL+"/pesanan/delete_cart.php";
}
