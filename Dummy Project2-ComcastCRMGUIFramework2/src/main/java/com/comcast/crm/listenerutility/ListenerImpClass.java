package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.comcast.crm.basetest.BaseClass;

public class ListenerImpClass implements ITestListener,ISuiteListener{

	@Override
	public void onStart(ISuite suite) {          //To get all these @Override Methods,we should Rightclick-select source-click on override/implement methods option-select for which we need to override-click on OK.
		System.out.println("Report configuration");
	}

	@Override
	public void onFinish(ISuite suite) {
      System.out.println("Report backUp");
	}

	@Override
	public void onTestStart(ITestResult result) {
    System.out.println("=============="+result.getMethod().getMethodName()+"=========START==========");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    System.out.println("=============="+result.getMethod().getMethodName()+"=========END==========");

	}

	@Override //This below code of ScreenShot is very older one,so we can use the program which is done by Sandeep sir only.
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();  //Here instead of using getMethod().getMethodName(),we can use directly getName()also.
		 EventFiringWebDriver edriver=new EventFiringWebDriver(BaseClass.sdriver); 
		 File srcFile = edriver.getScreenshotAs(OutputType.FILE);
			String time = new Date().toString().replace(" ", "_").replace(":", "_"); //Here we are using replace methods,otherwise we get the output with space&colon like this(Tue May 07 13:07:51 IST 2024) which can't be used as our file name) 

		 try {
			FileUtils.copyFile(srcFile, new File("./screenshot/"+testName+"+"+time+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
}

