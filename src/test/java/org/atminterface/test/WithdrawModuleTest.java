package org.atminterface.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import org.atminterface.main.dao.AtmOperation;
import org.atminterface.main.exception.InvalidAmountException;

import org.junit.jupiter.api.Test;

public class WithdrawModuleTest {
	

	@Test
	public void testCase1() throws ClassNotFoundException, SQLException, InvalidAmountException
	{
		
		assertEquals(1500, AtmOperation.withdraw(345678908,5000));
	}

//	
//	@Test
//	public void testCase2() throws ClassNotFoundException, SQLException, InvalidAmountException
//	{
//		
//		assertEquals(5000, AtmOperation.withdraw(342343242,15000));
//	}
}
