package util.retry;

public class Notes {
	
	/*
	 if u want to apply retry logic universally over suit level then go with RetryTransformer
	 
		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
		<suite name="Suite">
		
			<listeners>
				<listener class-name="util.retry.RetryTransformer"/>
			    <listener class-name="util.listeners.TestExecutionListener"/>
			</listeners>
		
		  <test thread-count="5" name="Test">
		  <parameter name="browser" value="chrome"></parameter>
		    <classes>
		      <class name="test.testcases.LoginTest"/>
		    </classes>
		  </test> <!-- Test -->
		</suite> <!-- Suite -->

	if u want to apply retry logic individually over test method you can add it on method like this
		import org.testng.Assert;
		import org.testng.annotations.Test;
		
		public class TestclassSample {
		
		  @Test(retryAnalyzer = MyRetry.class)
		  public void test2() {
		    Assert.fail();
		  }
		}
		
		
	Here’s a clear and correct version of your sentence, keeping it simple and accurate:
	Note: Retry logic will re-execute the @BeforeMethod and @AfterMethod along with the retried @Test method.
	If a dependent method fails, this test will be marked as SKIPPED and retry will not run.
	Retry executes only for the failed @Test method when all its dependencies have passed.

	 
	*/

}
