package org.atminterface.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AtmOperation {


	
	
	public static boolean login(String userName, String password) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDbConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from Atmbankdb where username=?");
		statement.setString(1, userName);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
		
			if(result.getString("pass_word").equals(password))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	
	
	public static double balanceCheck(long accountId) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDbConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from Atmbankdb where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double balance=result.getDouble("balance");
		return balance;
		
	}
	
	@SuppressWarnings("removal")
	public static double withdraw(long accountId, double withdrawalAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDbConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from Atmbankdb where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		
		if(withdrawalAmount<avilableBalance) //7000<8000
		{
		   double remainingBalance=avilableBalance-withdrawalAmount;
		   statement=connection.prepareStatement("update Atmbankdb set balance=? where accid=?");
		   statement.setDouble(1, new Double(remainingBalance));
		   statement.setLong(2, accountId);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return remainingBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		}
		else
		{
			   return 0;
		}
	}
	public static String pinchange(long accountId,String password,String newPin) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDbConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from User where accid=?");
		statement.setLong(1, accountId);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			if(result.getString("pass_word").equals(password))
			{
				if(password!=newPin) 
				{
					   
					   statement=connection.prepareStatement("update User set pass_word=? where accid=?");
					   statement.setString(1, new String(newPin));
					   statement.setLong(2, accountId);
					   
					   if(statement.executeUpdate()>0)
					   {
						   return newPin;  
					   }
					   else
					   {
						   return null;
					   }
					}
					else
					{
						   return null;
					}
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}			
	}
	
	
	
	@SuppressWarnings("removal")
	public static double deposit(long accountId, double depositAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDbConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from Atmbankdb where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		double newBalance=avilableBalance+depositAmount;
		
		  statement=connection.prepareStatement("update Atmbankdb set balance=? where accid=?");
		   statement.setDouble(1, new Double(newBalance));
		   statement.setLong(2, accountId);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return newBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		
	}
	
	
	public static ResultSet checkAccountInfo(long accountId) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDbConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from Atmbankdb where accid=?");
		statement.setLong(1, accountId);
		
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			return result;
		}
		else
		{
			return null;
		}
	}
	
}
