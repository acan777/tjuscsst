package cn.tjucic.st;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMoney {
	
	Money mon = new Money();
	
	@Test
	public void testIsExist() {
		//assertEquals(true, mon.isExist(83));
		//assertEquals(false, mon.isExist(83));
		assertEquals(false, mon.isExist(84));
	}
	
}
