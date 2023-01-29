import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class DBCompany {
    static Scanner num = new Scanner(System.in);
    static final String JBDC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/company";
    static final String USER = "root";
    static final String PASS = "";
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public DBCompany() throws Exception {
        Class.forName(JBDC_DRIVER);
        this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        this.stmt = conn.createStatement();
    }

    public int showProduct() throws Exception {
        String sql = "SELECT * FROM barang";
        System.out.println("Data barang : ");
        System.out.print("no. id, barang(stok) => harga");
        this.rs = this.stmt.executeQuery(sql);
        int a = 0;
        while (this.rs.next()) {
            System.out.printf("\n%d. ", ++a);
            int id = this.rs.getInt("id");
            String nama = this.rs.getString("nama_barang");
            Long harga = this.rs.getLong("harga");
            int stok = this.rs.getInt("stok");
            System.out.printf("%d, %s (%d) => %d", id, nama, stok, harga);
        }
        if (a == 0) {
            System.out.println("Database Kosong!");
            return a;
        } else {
            return a;
        }
    }

    public void insertProduct() throws Exception {
        System.out.print("Nama Barang : ");
        String nama = input.readLine().trim();
        System.out.print("Harga : ");
        long harga = num.nextInt();
        System.out.print("Stok : ");
        int stok = num.nextInt();
        String sql = "INSERT INTO barang (nama_barang, harga, stok) value('%s', '%d', '%d')";
        sql = String.format(sql, nama, harga, stok);
        this.stmt.execute(sql);
    }

    public void deleteProduct() throws Exception {
        if (showProduct() != 0) {
            System.out.println("\nInput nama barang yang akan dihapus!");
            System.out.print("Nama Barang : ");
            String nama = input.readLine().trim();
            String sql = "DELETE FROM barang\nWHERE nama_barang = '%s'";
            sql = String.format(sql, nama);
            this.stmt.execute(sql);
        }
    }

    public void updateProduct() throws Exception {
        if (showProduct() != 0) {
            System.out.println("\nInput id barang yang akan diubah!");
            System.out.print("Id : ");
            String id = input.readLine().trim();
            System.out.print("Nama Barang : ");
            String nama = input.readLine().trim();
            System.out.print("Harga : ");
            int harga = num.nextInt();
            System.out.print("Stok : ");
            int stok = num.nextInt();
            String sql = "UPDATE barang\nSET nama_barang = '%s', harga = '%d', stok = '%d'\n WHERE id = %s";
            sql = String.format(sql, nama, harga, stok, id);
            this.stmt.execute(sql);
        }
    }
}
