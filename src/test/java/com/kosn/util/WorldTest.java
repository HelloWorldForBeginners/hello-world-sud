package com.kosn.util;

import org.testng.annotations.Test;

public class WorldTest {
	
	@Test(description="Hello World TestNG Passing" )
	public void helloWorldTestShouldPass() {
		assert "DERP".equals("DERP");
	}

	@Test(description="Hello World TestNG Failing" )
	public void helloWorldTestShouldFail() {
		System.out.println("should fail");
		assert "DERP".equals("asdf");
	}
}
