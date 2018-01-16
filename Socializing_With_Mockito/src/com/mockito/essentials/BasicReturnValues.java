package com.mockito.essentials;

public class BasicReturnValues implements Comparable {
	
	public String getValue() {
		return "Patrick";
	}
	
	public String getValueIfTrue(boolean bool) {
		if(bool) {
			return "TRUE";
		} else {
			return "FALSE";
		}
	}
	
	public String getValueIfFalse(boolean bool) {
		if(bool) {
			return "FALSE";
		} else {
			return "TRUE";
		}
	}
	
	public String throwException(boolean bool) {
		if(bool) {
			try {
				throw new IllegalStateException();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "test";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
