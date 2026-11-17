import java.util.ArrayList; 
import java.util.Scanner;

public class MoneyNoteApp { // 入口类:显示菜单、读输入、调 MoneyManager 干活
    public static void main(String[] args) { 
        MoneyManager manager = new MoneyManager("data/records.csv"); // 新建管家,文件路径是 data/records.csv,旧记录会在构造方法里读回来
        Scanner sc = new Scanner(System.in); 
        int choice = -1; 
        while (choice != 0) { 
            System.out.println("=== MoneyNote ==="); 
            System.out.println("1. Add a record");
            System.out.println("2. View records");
            System.out.println("3. Monthly summary");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt(); 
            sc.nextLine(); 
            if (choice == 1) { // 选了 1
                addRecord(sc, manager); // 调加记录的方法
            } else if (choice == 2) { // 选了 2
                viewRecords(sc, manager); // 调查看的方法
            } else if (choice == 3) { // 选了 3
                showMonthlySummary(sc, manager); // 调月度统计的方法
            } else if (choice == 0) { // 选了 0
                System.out.println("Bye!"); // 打印再见
            } else { // 选了别的数字
                System.out.println("Invalid choice, please try again."); // 提示选项不对,重新选
            }
        }
        sc.close(); // 循环结束,关掉 Scanner
    }

    // 加一笔记录:一个一个问,再交给 manager 存起来
    private static void addRecord(Scanner sc, MoneyManager manager) {
        System.out.print("Date (yyyy-MM-dd): ");
        String date = sc.nextLine().trim();
        System.out.print("Type (income/expense/transfer): "); 
        String type = sc.nextLine().trim(); 
        System.out.print("Category: "); 
        String category = sc.nextLine().trim();
        System.out.print("Account: "); 
        String account = sc.nextLine().trim(); 
        System.out.print("Amount: "); 
        double amount = sc.nextDouble(); 
        sc.nextLine(); 
        System.out.print("Note: "); 
        String note = sc.nextLine().trim(); 
        manager.addRecord(date, type, category, account, amount, note); // 交给 manager 加记录并存文件
        System.out.println("Record added.");
    }

    // 查看记录:先选看全部、按月还是按分类,再打印出来
    private static void viewRecords(Scanner sc, MoneyManager manager) {
        System.out.println("1. View all"); 
        System.out.println("2. View by month"); 
        System.out.println("3. View by category"); 
        System.out.println("0. Back"); 
        System.out.print("Enter your choice: "); 
        int sub = sc.nextInt(); 
        sc.nextLine(); 
        if (sub == 1) { 
            printRecords(manager.getAllRecords()); 
        } else if (sub == 2) { 
            System.out.print("Month (yyyy-MM): ");
            String month = sc.nextLine(); 
            printRecords(manager.getRecordsByMonth(month)); 
        } else if (sub == 3) { 
            System.out.print("Category: "); 
            String category = sc.nextLine(); 
            printRecords(manager.getRecordsByCategory(category)); 
        } else if (sub == 0) { 
            return; // 直接返回主菜单
        } else { 
            System.out.println("Invalid choice, please try again.");
        }
    }

    // 打印一个记录列表:空就提示没有,非空就一行一笔打印
    private static void printRecords(ArrayList<MoneyRecord> list) {
        if (list.isEmpty()) {
            System.out.println("No records.");
            return; // 直接返回
        }
        for (MoneyRecord r : list) {
            System.out.println(r); // 打印这笔记录,会自动调它的 toString
        }
    }

    // 月度统计:输月份,打印总收入、总支出、结余,再按分类和按账户汇总支出
    private static void showMonthlySummary(Scanner sc, MoneyManager manager) {
        System.out.print("Month (yyyy-MM): "); 
        String month = sc.nextLine(); 
        double income = manager.getTotalIncome(month);
        double expense = manager.getTotalExpense(month); 
        System.out.println("Month: " + month); 
        System.out.println("Total income: " + income); 
        System.out.println("Total expense: " + expense);
        System.out.println("Balance: " + (income - expense));
        System.out.println("--- By category (expense) ---"); 
        for (String c : manager.getCategories(month)) {
            double total = manager.getTotalByCategory(month, c, "expense");
            if (total > 0) {
                System.out.println(c + ": " + total); 
            }
        }
        System.out.println("--- By account (expense) ---"); 
        for (String a : manager.getAccounts(month)) {
            double total = manager.getTotalByAccount(month, a, "expense"); 
            if (total > 0) {
                System.out.println(a + ": " + total); 
            }
        }
    }
}
