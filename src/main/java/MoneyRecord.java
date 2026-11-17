public class MoneyRecord{
	private int id;		//编号记录
	private String date;	//日期，格式为2026-11-06
	private String type;	//income 收入/expense 支出/transfer 转账
	private String category;	//分类
	private String account;		//账户
	private double amount;		//金额
	private String note;	//备注，可空
	
	//构造方法：新建一笔记录时一次把7个值都传进来
	public MoneyRecord(int id,String date,String type,String category,String account,double amount,String note) {
		this.id=id;
		this.date=date;
		this.type=type;
		this.category=category;
		this.account=account;
		this.amount=amount;
		this.note=note;
	}
	
	//先读取，再保存
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date=date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type=type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account=account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount=amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note=note;
	}
	
	
	//记录打印
	public String toString() {
		return id+"|"+date+"|"+type+"|"+category+"|"+account+"|"+amount+"|"+note;
	}
	
}
