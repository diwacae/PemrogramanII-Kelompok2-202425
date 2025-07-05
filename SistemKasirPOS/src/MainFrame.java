import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JComboBox<Barang> comboBoxBarang;
    private JTextField textJumlah;
    private JTextArea areaStruk;
    private Transaksi transaksi;

    public MainFrame() {
        setTitle("Sistem Kasir Sederhana");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        transaksi = new Transaksi();

        // Daftar barang
        Barang[] daftarBarang = {
                new Barang("Indomie", 3000),
                new Barang("Susu Ultra", 7000),
                new Barang("Roti Tawar", 10000),
                new Barang("Minyak Goreng", 25000),
                new Barang("Sabun Mandi", 5000)
        };

        // Panel Atas
        JPanel panelAtas = new JPanel(new GridLayout(3, 2));
        panelAtas.add(new JLabel("Pilih Barang:"));
        comboBoxBarang = new JComboBox<>(daftarBarang);
        panelAtas.add(comboBoxBarang);

        panelAtas.add(new JLabel("Jumlah Beli:"));
        textJumlah = new JTextField();
        panelAtas.add(textJumlah);

        JButton btnTambah = new JButton("Tambah");
        panelAtas.add(btnTambah);

        JButton btnCetak = new JButton("Cetak Struk");
        panelAtas.add(btnCetak);

        add(panelAtas, BorderLayout.NORTH);

        // Area Struk
        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        add(new JScrollPane(areaStruk), BorderLayout.CENTER);

        // Action Tambah
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Barang barang = (Barang) comboBoxBarang.getSelectedItem();
                    int jumlah = Integer.parseInt(textJumlah.getText());
                    if (jumlah <= 0) throw new NumberFormatException();
                    transaksi.tambahBarang(barang, jumlah);
                    JOptionPane.showMessageDialog(null, "Barang ditambahkan!");
                    textJumlah.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Jumlah harus angka > 0");
                }
            }
        });

        // Action Cetak Struk
        btnCetak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaStruk.setText(transaksi.cetakStruk());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
