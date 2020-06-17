package com.ediro.domain;



public enum BookStatus{
		
	    GOOD("정상"),
		SOLDOUT("품절"),
		TMPSOLDOUT("일시품절"),
		CLOSE("절판");
	
		private String code;
		
		private BookStatus(String code)
		{
			this.code = code;
		}
		
		public String getCode() {
			return code;
		}
		
}

