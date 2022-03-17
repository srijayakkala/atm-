package atm;

import java.sql.Date;

public class Transaction {
	
	private double amount;
	private Date timestamp;
	private String memo;
	
	private Account inAccount;
	
	public Transaction(double amount, Account inAccount)
	{
		this.amount=amount;
		this.inAccount= inAccount;
		this.timestamp=new Date(0);
		this.memo="";
		
	}
	public Transaction(double amount, String memo, Account inAccount)
	{
		this(amount,inAccount);
		this.memo=memo;
	}
	
	

}
