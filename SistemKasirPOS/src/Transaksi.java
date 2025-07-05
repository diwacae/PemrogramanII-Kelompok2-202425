import java.util.HashMap;
import java.util.Map;

public class Transaksi {
    private final Map<Barang, Integer> pembelian = new HashMap<>();

    public void tambahBarang(Barang barang, int jumlah) {
        pembelian.put(barang, pembelian.getOrDefault(barang, 0) + jumlah);
    }

    public double hitungTotal() {
        double total = 0;
        for (Map.Entry<Barang, Integer> entry : pembelian.entrySet()) {
            total += entry.getKey().getHarga() * entry.getValue();
        }
        return total;
    }

    public double hitungDiskon() {
        double total = hitungTotal();
        return total > 100000 ? total * 0.10 : 0;
    }

    public String cetakStruk() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== STRUK PEMBELIAN =====\n");
        for (Map.Entry<Barang, Integer> entry : pembelian.entrySet()) {
            Barang b = entry.getKey();
            int jumlah = entry.getValue();
            sb.append(b.getNama()).append(" x").append(jumlah)
              .append(" = Rp ").append(b.getHarga() * jumlah).append("\n");
        }
        double total = hitungTotal();
        double diskon = hitungDiskon();
        sb.append("----------------------------\n");
        sb.append("Total       : Rp ").append(total).append("\n");
        sb.append("Diskon      : Rp ").append(diskon).append("\n");
        sb.append("Total Bayar : Rp ").append(total - diskon).append("\n");
        sb.append("============================");
        return sb.toString();
    }
}
