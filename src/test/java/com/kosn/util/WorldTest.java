package com.kosn.util;

import org.testng.annotations.Test;

public class WorldTest {
	
	@Test(description="Hello World TestNG Passing" )
	public void helloWorldTestShouldPass() {
		assert "DERP".equals("DERP");
	}
}
