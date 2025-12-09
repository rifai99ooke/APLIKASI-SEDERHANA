import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionId;
    private String type;
    private double jumlah;
    private String keterangan;
    private LocalDateTime waktu;

    public Transaction(String transactionId, String type, double jumlah, String keterangan){
        this.transactionId = transactionId;
        this.type = type;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.waktu = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String geType() {
        return type;
    }

    public double getJumlah(){
        return jumlah;
    }

    public LocalDateTime getWaktu(){
        return waktu;
    }

   public String getFormattedWaktu(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return waktu.format(formatter);
   }

   public void display (){
    System.out.println("================================================");
    System.out.println("ID Transaksi : " + transactionId);
    System.out.println("Tipe : " + type);
    System.out.println("Jumlah : Rp " + jumlah);
    System.out.println("Keterangan : " + keterangan);
    System.out.println("Waktu : " + getFormattedWaktu());
   }
}   