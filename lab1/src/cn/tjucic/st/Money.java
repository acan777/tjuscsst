package cn.tjucic.st;

public class Money {
	public boolean isExist(int sum) {	
		
		//x1 x2 x3 x4分别表示用到的50 20 5 1面额的数量
		for(int x1 = 1; x1 >= 0; x1--) {
			for(int x2 = 1; x2 >= 0; x2--) {
				for(int x3 = 2; x3 >= 0; x3--) {
					for(int x4 = 3; x4 >= 0; x4--) {
						if(sum == x1 * 50 + x2 * 20 + x3 * 5 + x4 * 1) {
							return true;
						}
					}
				}
			}
		}
		
		return false;	
	}
}
