package atm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User
{
	private String firstname;
	private String lastname;
	private String uuid;
	private byte[] pinHash;
	
	private ArrayList<Account> accounts;

	public User(String firstname, String lastname,String pin, Bank theBank)
	{
		
		this.firstname = firstname;
		this.lastname = lastname;
		try
		{
		MessageDigest md=MessageDigest.getInstance("MD5");
		this.pinHash=md.digest(pin.getBytes());
	    }
		catch(NoSuchAlgorithmException e) 
		{
			System.err.println("error,caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		//get a new,unique user ID for the user
		this.uuid= theBank.getNewUserUUId();
		
	    //create empty list of accounts
		this.accounts=new ArrayList<Account>();
		//print login message
		System.out.printf("New User %s, %s with ID %s created. \n", lastname,firstname,this.uuid);
  }
	public void addAccount(Account anAcc)
	{
		this.accounts.add(anAcc);
	}


    
	public String getUUID()
	{
		public boolean validatePin(String aPin) {
			try
			{
				MessageDigest md= MessageDigest.getInstance("MD5");
				return MessageDigest.isEqual(md.digest(aPin.getBytes()),this. pinHash);
		
			}
			catch(NoSuchAlgorithmException e)
			{
				System.err.println("error , caught NoSuchAlgorithmException");
				e.printStackTrace();
				System.exit(1);
			}
		    return false;
		}
	
	
		    public String getFirstName()
		    {
		    	this.getFirstName();
		    }
		    public void printAccountAummary()
		    {
		    	System.out.printf("\n\n %s's accounts summary\n", this.firstname);
		    	for(int a=0;a<this.accounts.size();a++)
		    	{
		    		System.out.printf("%d %s \n",a+1,this.accounts.get(a).getSummaryLine());
		    	}
		    	System.out.println();
		    }
		    public int numAccounts() 
		    {
		    	return this.accounts.size();
		    }
		    public void printAcctTransHistory(int accIdx)
		    {
		    	this.accounts.get(accIdx).printTransHistory();
		    }
		    public double getAcctBalance(int acctIdx)
		    {
		    	return this.accounts.get(acctIdx).getBalance();
		    }
		    public String getAcctUUID(int acctIdx)
		    {
		    	int accIdx;
				return this.accounts.get(accIdx).getUUID();
		    }
		    public void addAcctTransaction(int acctIdx,double amount,String memo)
		    {
		    	this.accounts.get(acctIdx).addTransaction(amount,memo);
		    }
		    
	}

	

	
		
        
	
	