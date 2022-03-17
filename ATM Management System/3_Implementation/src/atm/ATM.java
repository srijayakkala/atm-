package atm;

import java.util.Scanner;

public class ATM {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		Bank theBank= new Bank("HDFC Bank");
		
		User aUser= theBank.addUser("Srija", "Shetty", "6281");
		
		Account newAccount = new Account("Checking",aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while(true)
		{
			curUser = ATM.mainMenuPrompt(theBank , sc);
			
			ATM.printUserMenu(curUser,sc);
		}
	}
	
	public static User mainMenuPrompt(Bank theBank,Scanner sc)
	{
		String userID;
		String pin;
		User authUser;
		
		do
		{
			System.out.printf("\n\nWelcome to %s\n\n",theBank.getName());
			System.out.print("Enter user ID: ");
			userID=sc.nextLine();
			System.out.print("Enter pin:");
			pin=sc.nextLine();
			
			authUser = theBank.userLogin(userID, pin);
			if(authUser==null)
			{
				System.out.println("Incorrect user ID/pin combination."+"Please try again.");
			}
			
		}
		while(authUser==null);
		return authUser;
	}
	public static void printUserMenu(User theUser, Scanner sc)
	{
		theUser.printAccountAummary();
		
		int choice;
		
		do
		{
			System.out.printf("Welcome %s, what would you like to do?\n ",theUser.getFirstName());
			System.out.println("1.Show account transaction history");
			System.out.println("2.Withdraw");
			System.out.println("3.Deposit");
			System.out.println("4.Transfer");
			System.out.println("5.Quit");
			
			System.out.println();
			System.out.println("Enter Choice");
			choice= sc.nextInt();
			
			if(choice<1||choice >5)
			{
				System.out.println("Invalid choice.Please choose from 1-5");
				
			}
			while(choice<1 || choice>5);
			switch(choice)
			{
			case 1:
				ATM.showTransHistory(theUser,sc);
				break;
			case 2:
				ATM.withdrawlFunds(theUser,sc);
				break;
			case 3:
				ATM.depositFunds(theUser,sc);
				break;
			case 4:
				ATM.transferFunds(theUser,sc);
				break;
			case 5:
				sc.nextLine();
				break;
			
				
			}
			if(choice!=5)
			{
				ATM.printUserMenu(theUser, sc);
			}
			
		 }
		public static void showTransHistory(User theUser,Scanner sc) {
			int theAcct;
			do
			{
				System.out.printf("Enter the number (1-%d) of the account\n"+"whose transactions you want to see:",theUser.numAccounts());
				theAcct= sc.nextInt()-1;
				if(theAcct<0 || theAcct>=theUser.numAccounts())
				{
					System.out.println("Invalid account.Please try again");
				}
				while(theAcct<0 || theAcct >= theUser.numAccounts());
				
				
					theUser.printAcctTransHistory(theAcct);
				
			}
			public static void transferFunds(User theUser,Scanner sc)
			{
				int fromAcct;
				int toAcct;
				double amount;
				double acctbal;
				do
				{
					System.out.printf("Enter the number (1-%d) of the account\n"+"to tranfer the form:");
					fromAcct=sc.nextInt()-1;
					if(fromAcct<0 || fromAcct >= theUser.numAccounts())
					{
						System.out.println("Invalid account.please try again");
					}
					
				}while(fromAcct <0 || fromAcct >=theUser.numAccounts());
				acctbal=theUser.getAcctBalance(fromAcct);
				
				do
				{
					System.out.printf("Enter the number (1-%d) of the account\n"+"to tranfer the form:",theUser.numAccounts());
					toAcct=sc.nextInt()-1;
					if(toAcct<0 || toAcct >= theUser.numAccounts())
					{
						System.out.println("Invalid account.please try again");
					}
					
				}while(toAcct <0 || toAcct >=theUser.numAccounts());
				
				do
				{
					System.out.printf("Enter the amount to transfer (max $%.02f):$ ", acctbal);
					amount= sc.nextDouble();
					if(amount<0)
					{
						System.out.println("Amount must be greater than zero");
					}
					else if(amount > acctbal)
					{
						System.out.printf("Amount must not be greater than\n"+"balance of $%0.2f.\n",acctbal);
					}
				}
				while(amount <0 || amount>acctbal);
				
				theUser.addAcctTransaction(fromAcct,-1*amount,String.format ("Tranfer to account %s",theUser.getAcctUUID(toAcct)));
				theUser.addAcctTransaction(toAcct,-1*amount,String.format ("Tranfer to account %s",theUser.getAcctUUID(fromAcct)));
			
			
		}
			public static void withdrawlFunds(User theUser,Scanner sc)
			{
				int fromAcct;
				double amount;
				double acctbal;
				String memo;
				
				do
				{
					System.out.printf("Enter the number (1-%d) of the account\n"+"to withdrawlfrom:",theUser.numAccounts());
					fromAcct=sc.nextInt()-1;
					if(fromAcct<0 || fromAcct >= theUser.numAccounts())
					{
						System.out.println("Invalid account.please try again");
					}
					
				}while(fromAcct <0 || fromAcct >=theUser.numAccounts());
				acctbal=theUser.getAcctBalance(fromAcct);
				
				do
				{
					System.out.printf("Enter the amount to transfer (max $%.02f):$ ", acctbal);
					amount= sc.nextDouble();
					if(amount<0)
					{
						System.out.println("Amount must be greater than zero");
					}
					else if(amount >acctbal)
					{
						System.out.printf("Amount must not be greater than\n"+"balance of $%0.2f.\n",acctbal);
					}
				}
				while(amount <0 || amount>acctbal);
				
				sc.nextLine();
				System.out.println("Enter a memo");
				memo= sc.nextLine();
				
				theUser.addAcctTransaction(fromAcct, -1*amount, memo);
				}
			public static void depositFunds(User theUser,Scanner sc)
			{
				int toAcct;
				double amount;
				double acctbal;
				String memo;
				
				do
				{
					System.out.printf("Enter the number (1-%d) of the account\n:"+"to deposit in:",theUser.numAccounts());
					toAcct=sc.nextInt()-1;
					if(toAcct<0 || toAcct >= theUser.numAccounts())
					{
						System.out.println("Invalid account.please try again");
					}
					
				}while(toAcct <0 || toAcct >=theUser.numAccounts());
				acctbal=theUser.getAcctBalance(toAcct);
				
				do
				{
					System.out.printf("Enter the amount to transfer (max $%.02f):$ ", acctbal);
					amount= sc.nextDouble();
					if(amount<0)
					{
						System.out.println("Amount must be greater than zero");
					}
			
				}
				while(amount <0 );
				
				sc.nextLine();
				System.out.println("Enter a memo");
				memo= sc.nextLine();
				
				theUser.addAcctTransaction(toAcct, -1*amount, memo);
				}
			
			

		}
		

