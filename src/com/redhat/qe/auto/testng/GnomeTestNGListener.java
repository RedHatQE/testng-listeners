package com.redhat.qe.auto.testng;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.testng.ITestResult;
import org.testng.Reporter;

import com.redhat.qe.auto.selenium.ITestNGScreenCapture;

public class GnomeTestNGListener extends ScreenShotTestNGListener implements ITestNGScreenCapture{
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmssS");


	public GnomeTestNGListener() {
		ScreenShotTestNGListener.setScreenCaptureUtility(this);
	}
	
	@Override
	public String screenCapture() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String testNGScreenCapture(String className, String methodName) throws Exception {
		String dirName = System.getProperty("selenium.screenshot.dir", System.getProperty("user.dir") + File.separator
				+ "test-output" + File.separator + "screenshots");
		
		try {
			new File(dirName).mkdirs();
		}catch (Exception e) { //already exist? 
		}
		
		
		Date rightNow = new Date();
		String outFileName = dateFormat.format(rightNow) + ".png";
		String fullpath = dirName + File.separator + outFileName;
		
		//instead of selenium screencap, do you own thing to get a screenshot in selenium.screenshot.dir, or wherever
		//pngRemoteScreenCapture(fullpath);
		
		//embed link in testng report
		//Reporter.setCurrentTestResult(result);
		String screenshotLinkUrl = System.getProperty("selenium.screenshot.link.path", "../screenshots");
		Reporter.log("<a href='" + String.format("%s/%s", screenshotLinkUrl, outFileName) + "'>Screenshot</a>");
		return "";
	}

}
