import java.util.Scanner;

public class App {
    public static void clearConsole(Scanner input) {
        System.out.print("\nKlik Enter!");
        input.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        DBCompany database = new DBCompany();
        while (true) {
            System.out.println("Menu : ");
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Show");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Pilih Menu : ");
            int menu = input.nextInt();
            input.nextLine();
            switch (menu) {
                case 1:
                    database.insertProduct();
                    break;
                case 2:
                    database.updateProduct();
                    break;
                case 3:
                    database.showProduct();
                    break;
                case 4:
                    database.deleteProduct();
                    break;
                case 5:
                    input.close();
                    System.exit(0);
                default:
                    break;
            }
            clearConsole(input);
        }
    }
}