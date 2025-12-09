import java.util.ArrayList;
public class Wallet {
    private String pemilik;
    private double saldo;
    private String pin;
    private ArrayList<Transaction> riwayatTransaksi;
    private int transactionCounteer;

    public Wallet(String pemilik, String pin){
        this.pemilik = pemilik;
        this.pin = pin;
        this.saldo = 0;
        this.riwayatTransaksi = new  ArrayList<>();
        this.transactionCounteer = 1;
    }

    public String getPemilik(){
        return pemilik ;
    }

    public double getSaldo (){
        return saldo;
    }

    public boolean validatePin(String inputPin){
        return this.pin.equals(inputPin);
    }

    public boolean gantiPin(String pinLama, String pinBaru){
        if(validatePin(pinLama)){
            this.pin = pinBaru;
            System.out.println("/n Pin Berhasil Di Ganti");
            return true;
        }
        System.out.println("/n Pin Lama Salah");
        return false;
    }

    public boolean topUp(double jumlah, String inputPin){
        if (!validatePin(inputPin)){
            System.out.println("PIN Salah");
            return false;
        }

        if (jumlah <= 0){
            System.out.println("/n Jumlah harus lebih dari 0");
            return false;
        }

        this.saldo += jumlah;
        String transId = "TRX" + transactionCounteer++;
        Transaction trx = new Transaction(transId, "TOP_UP", jumlah, "Top UP Saldo");
        riwayatTransaksi.add(trx);
        System.out.println("/n Top Up Berhasil");
        System.out.println("Jumlah : Rp " + jumlah);
        System.out.println("Saldo Sekarang : Rp " + saldo);
        return true;
    }

    public boolean tarikTunai(double jumlah, String inputPin){
        if(!validatePin(inputPin)){
            System.out.println("/n PIN salah");
            return false;
        }

        if(jumlah <=0){
            System.out.println("/n Jumlah Harus Lebih dari 0");
            return false;
        }

        if (jumlah>saldo){
            System.out.println("/n Saldo tidak cukup");
            System.out.println("Saldo anda: Rp " + saldo);
            return false;
        }

        this.saldo -= jumlah;

        String transId = "TRX" + transactionCounteer++;
        Transaction trx = new Transaction(transId, "TARIK_TUNAI", jumlah, "Tarik Tunai");
        riwayatTransaksi.add(trx);

        System.out.println("/n Tarik tunai berhasil");
        System.out.println("Jumlah : Rp " + jumlah);
        System.out.println("Saldo Sekarang: Rp " + saldo);
        return true;
    }

    public boolean transfer(String nomorRekening, double jumlah, String inputPin){
        if(!validatePin(inputPin)){
            System.out.println("/n PIN Salah");
            return false;
        }

        if(jumlah <=0){
            System.out.println("/n Jumlah harus lebih dari 0");
            return false;
        }

        if(jumlah > saldo) {
            System.out.println("/n Saldo tidak cukup");
            System.out.println("Saldo Anda: Rp " + saldo);
            return false;
        }

        this.saldo -= jumlah;

        String transId = "TRX" + transactionCounteer++;
        Transaction trx = new Transaction(transId,"TRANSFER", jumlah, "Transfer Ke" + nomorRekening);
        riwayatTransaksi.add(trx);
        System.out.println("/n Transfer berhasil");
        System.out.println("Tujuan: " + nomorRekening);
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Saldo sekarang: Rp " + saldo);
        return true;
    }

    public boolean bayar(String namaMerchant, double jumlah, String inpuPin){
        if(!validatePin(inpuPin)){
            System.out.println("/n PIN Salah");
            return false;
        }

        if (jumlah<=0){
            System.out.println("/n Jumlah harus lebih dari 0");
            return false;
        }

        if (jumlah > saldo){
            System.out.println("/n Saldo tidal cukup");
            System.out.println("Saldo Anda: Rp " + saldo);
            return false;
        }

        this.saldo -= jumlah;
        String transId = "TRX" + transactionCounteer++;
        Transaction trx = new Transaction(transId, "PEMBAYARAN", jumlah, "Bayar Ke" + namaMerchant);
        riwayatTransaksi.add(trx);
        System.out.println("/n Pembayaran Berhasil");
        System.out.println("Merchant: " + namaMerchant);
        System.out.println("Jumlah: Rp " + jumlah);
        System.out.println("Saldo Sekarang: Rp " + saldo);
        return true;
    }

    public void cekSaldo(){
        System.out.println("     INFORMASI SALDO     ");
        System.out.println("=========================");
        System.out.println("pemilik" + pemilik);
        System.out.println("Saldo: Rp" + saldo);
    }

    public void lihatRiwayat(){
        System.out.println("     RIWAYAT TRANSAKSI     ");
        if (riwayatTransaksi.isEmpty()){
            System.out.println("Belum ada transaksi");
        }
        else {
            for (Transaction trx : riwayatTransaksi){
                trx.display();
            }
        }
    }

    public void displayInfo(){
        System.out.println("/n====================");
        System.out.println("     INFO DOMPET     ");
        System.out.println("======================");
        System.out.println("Pemilik" + pemilik);
        System.out.println("Saldo" + saldo);
        System.out.println("Total Transaksi: " + riwayatTransaksi.size());
    }


}
