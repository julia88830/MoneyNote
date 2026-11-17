import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MoneyManager{
	private ArrayList<MoneyRecord>records;
	private int nextId;
	private String filePath;		//CSV文件的路径，如data/records
	
	
	//构造方法：传进文件路径，先建空列表，再读取文件已有文件
	public MoneyManager(String filepath) {
		this.filePath=filepath;
		records = new ArrayList<MoneyRecord>();
		nextId=1;
		loadFromFile();
	}
	
	 public void addRecord(String date, String type, String category, String account, double amount, String note) {
	        MoneyRecord r = new MoneyRecord(nextId, date, type, category, account, amount, note); // 用 nextId 新建一笔记录
	        records.add(r);
	        nextId++; 
	        saveToFile(); // 马上存进文件,程序关了也不丢
	    }
	
	
	public ArrayList<MoneyRecord> getAllRecords(){
		return records;
	}
	
	//按分类筛选
	public ArrayList<MoneyRecord> getRecordsByCategory(String category) {
	    ArrayList<MoneyRecord> result = new ArrayList<MoneyRecord>(); // 新建一个空列表装结果
	    for (MoneyRecord r : records) { 
	        if (r.getCategory().equals(category)) {
	            result.add(r); // 就加进结果列表
	        }
	    }
	    return result; // 返回筛出来的列表
	}

	
	//按月份筛选
	public ArrayList<MoneyRecord> getRecordsByMonth(String month) {
        ArrayList<MoneyRecord> result = new ArrayList<MoneyRecord>(); // 新建一个空列表装结果
        for (MoneyRecord r : records) {
            if (r.getDate().startsWith(month)) {
                result.add(r);
            }
        }
        return result; // 返回筛出来的列表
    }
	public double getTotalIncome(String month) {
        double total = 0; 
        for (MoneyRecord r : records) {
            if (r.getDate().startsWith(month) && r.getType().equals("income")) {
                total = total + r.getAmount(); // 把这笔金额加进总数
            }
        }
        return total; // 返回总收入
    }
	
	public double getTotalExpense(String month) { // 算某个月的总支出
	    double total = 0; // 总数先定为 0
	    for (MoneyRecord r : records) { // 逐笔看列表里的每笔记录
	        if (r.getDate().startsWith(month) && r.getType().equals("expense")) { // 日期是这个月,并且类型是支出
	            total = total + r.getAmount(); // 把这笔金额加进总数
	        }
	    }
	    return total; // 返回总支出
	}

	
	// 算某个月、某个分类、某种类型的总数,如 11 月 Food 的支出
    public double getTotalByCategory(String month, String category, String type) {
        double total = 0; 
        for (MoneyRecord r : records) {
            if (r.getDate().startsWith(month) && r.getCategory().equals(category) && r.getType().equals(type)) { // 月份、分类、类型三个都对上
                total = total + r.getAmount(); // 把这笔金额加进总数
            }
        }
        return total;
    }

    // 算某个月、某个账户、某种类型的总数,如 11 月 Alipay 的支出
    public double getTotalByAccount(String month, String account, String type) {
        double total = 0;
        for (MoneyRecord r : records) {
            if (r.getDate().startsWith(month) && r.getAccount().equals(account) && r.getType().equals(type)) { 
                total = total + r.getAmount(); 
            }
        }
        return total; 
    }

    // 列出某个月出现过的所有分类,不重复,统计时按它一个一个算
    public ArrayList<String> getCategories(String month) {
        ArrayList<String> result = new ArrayList<String>(); // 新建一个空列表装分类名
        for (MoneyRecord r : records) { // 逐笔看列表里的每笔记录
            if (r.getDate().startsWith(month) && !result.contains(r.getCategory())) { // 是这个月,并且这个分类还没加过
                result.add(r.getCategory()); // 就把分类名加进去
            }
        }
        return result; // 返回分类名列表
    }

    // 列出某个月出现过的所有账户,不重复,统计时按它一个一个算
    public ArrayList<String> getAccounts(String month) {
        ArrayList<String> result = new ArrayList<String>(); // 新建一个空列表装账户名
        for (MoneyRecord r : records) { // 逐笔看列表里的每笔记录
            if (r.getDate().startsWith(month) && !result.contains(r.getAccount())) { // 是这个月,并且这个账户还没加过
                result.add(r.getAccount());
            }
        }
        return result; // 返回账户名列表
    }

    // 把列表里所有记录写进 CSV 文件,每次加记录后都调一次
    public void saveToFile() {
        File file = new File(filePath); // 用路径新建一个文件对象
        File parent = file.getParentFile(); // 取它的父文件夹,如 data
        if (parent != null) { // 如果有父文件夹
            parent.mkdirs(); // 没有就建出来,有就不动
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) { // 打开文件准备写,用完自动关
            for (MoneyRecord r : records) {
                out.println(r.getId() + "," + r.getDate() + "," + r.getType() + "," + r.getCategory() + "," + r.getAccount() + "," + r.getAmount() + "," + r.getNote()); 
            }
        } catch (IOException e) { // 写文件出错时进这里
            System.out.println("Save failed: " + e.getMessage()); // 在控制台打印保存失败和原因
        }
    }

    // 从 CSV 文件读记录,只在构造方法里调一次
    private void loadFromFile() {
        File file = new File(filePath); // 用路径新建一个文件对象
        if (!file.exists()) { // 如果文件还不存在
            return; // 直接返回,列表保持为空,第一次运行就是这样
        }
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) { // 打开文件准备按行读,用完自动关
            String line; // 用来装每次读到的一行
            while ((line = in.readLine()) != null) { // 一行一行读,读到空就是读完了
                String[] parts = line.split(",", -1); // 按逗号切成 7 段,-1 是让末尾的空备注也留着
                if (parts.length < 7) { // 如果切出来不到 7 段,说明这行写坏了
                    continue; // 跳过这一行,看下一行
                }
                int id = Integer.parseInt(parts[0]); // 第 1 段是编号,字符串转 int
                double amount = Double.parseDouble(parts[5]); // 第 6 段是金额,字符串转 double
                MoneyRecord r = new MoneyRecord(id, parts[1], parts[2], parts[3], parts[4], amount, parts[6]); // 用 7 段拼回一笔记录
                records.add(r); // 加进列表
                if (id >= nextId) { // 如果读到的编号比 nextId 大
                    nextId = id + 1; // nextId 就定为它加 1,避免新记录和旧记录编号重复
                }
            }
        } catch (IOException e) { // 读文件出错时进这里
            System.out.println("Load failed: " + e.getMessage()); // 在控制台打印读取失败和原因
        }
    }
	
}