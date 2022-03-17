package atm;

import java.util.ArrayList;

public class Account {
	
	private String name;
	private double balance;
	private String uuid;
	private User holder;
	private ArrayList<Transaction> transactions;
	
	public Account(String name, User holder, Bank theBank)
	{
		this.name= name;
		this.holder= holder;
		//get account UUID
		
		this.uuid = theBank.getNewUserUUId();
		
		//init transactions
		this.transactions = new ArrayList<Transaction>();
		//ad holder and bankl lists
		
		
	}

	public String getUUID() {
		// TODO Auto-generated method stub
		return this.uuid;
	}
	public String getSummaryLine()
	{
		double balance=this.getBalance();
		if(balance>=0)
		{
			return String.format("%s : $%.02f : %s", this.uuid,balance);
		}
		else
		{
			return String.format("%s : $(%.02f) : %s", this.uuid,balance);
		}
	}
	
	public double getBalance()
	{
		double balance=0;
		for(Transaction t: this.transactions)
		{
			balance+=t.getAmount();
		}
		return balance;
	}

	public void printTransHistory() {
		System.out.printf("\nTransaction history for account %s\n",this.uuid);
		for(int t = this.transactions.size()-1;t>=0;t--)
		{
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}

	

}
