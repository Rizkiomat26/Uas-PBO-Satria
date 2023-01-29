import com.program.*;
import com.google.gson.Gson;
import java.io.File;
import java.util.Scanner;

public class App {
    // Mengambil data dari file json untuk diinsertkan ke table Category
    public static void insertCategoryFromJson() throws Exception {
        Gson gson = new Gson();
        File json = new File("src/com/file/Category.json");
        Scanner output = new Scanner(json);
        String data = "";
        while (output.hasNextLine()) {
            data += output.nextLine();
        }
        DBGudang db = gson.fromJson(data, DBGudang.class);
        for (Category category : db.getProducts()) {
            db.insertCategory(category.getId(), category.getName());
        }
        output.close();
    }

    // Menghapus isi console
    public static void clearConsole() throws Exception {
        System.out.print("Tekan Enter!");
        System.in.read();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        insertCategoryFromJson();/*
                                  * Insert data dari file json ke table Category, jika program sudah pernah
                                  * dijalankan maka sebaiknya function insertCategoryFromJson() di beri comment
                                  * supaya tidak terjadi error pada program dan database
                                  */
        Scanner input = new Scanner(System.in);
        DBGudang db = new DBGudang();
        while (true) {
            System.out.println("Menu");
            System.out.println("1. Input product");
            System.out.println("2. Tampilkan product");
            System.out.println("3. Update product");
            System.out.println("4. Hapus product");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int menu = Integer.parseInt(input.nextLine());
            if (menu == 1) {
                db.input(true);
            } else if (menu == 2) {
                db.selectProducts();
            } else if (menu == 3) {
                db.input(false);
            } else if (menu == 4) {
                db.deleteProducts();
            } else if (menu == 5) {
                System.out.println("Terima kasih");
                input.close();
                System.exit(0);
                break;
            } else {
                System.out.println("Menu tidak tersedia");
            }
            clearConsole();
        }
    }
}
