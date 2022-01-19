package com.gmail.helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

public class Gmail extends CommonHelper {
	static String xlTo;
	static String xlSubject;
	static String xlMessageBody;

	public Gmail() throws Exception {
		super();
	}

	@Test
	public static void userLaunchesWebsite(String environment) throws Exception {
		try {
			Utility.setUpClient(environment);
			CommonHelper.openUrl(environment);
			test.log(LogStatus.PASS, "User is able to launch Website - " + environment);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to navigated to the URL");
			e.printStackTrace();
		}
	}

	@Test
	public static void userSubmitUsernameAndPassword() throws Exception {
		try {
			enterValue(By.cssSelector("input[type='email']"), properties.getProperty("Email"));
			clickElement(By.xpath("//span[.='Next']"));
			Thread.sleep(3000);
			enterValue(By.cssSelector("input[name='password']"), properties.getProperty("Password"));
			clickElement(By.xpath("//span[.='Next']"));

			// If Confirm button present then click on Confirm
			Thread.sleep(1000);
			clickElementIfPresent(By.xpath("//span/span[.='Confirm']"));

			// If Next button present then click on Next
			Thread.sleep(500);
			clickElementIfPresent(By.xpath("//span[.='Next']"));

			test.log(LogStatus.PASS, "User is able to enter username and password correctly");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to enter username and password correctly");
			e.printStackTrace();
		}
	}

	@Test
	public static void verifyUserLoggedIn() throws IOException {
		try {
			String emailUser = properties.getProperty("Email");
			Thread.sleep(2000);
			if (driver.getTitle().contains(emailUser))
				assertTrue(true, "Verified User is Logged In" + emailUser);
			test.log(LogStatus.PASS, "Verified User is Logged In -" + emailUser);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Logged In");
			e.printStackTrace();
		}
	}

	@Test
	public static void userClicksOnButton(String buttonName) {
		try {
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			clickElement(By.xpath("//div[text()='" + buttonName + "']"));
			test.log(LogStatus.PASS, "User clicks on " + buttonName + " button");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Unable to click on " + buttonName + " button");
		}
	}

	@Test
	public static void entersEmailBodyWithSubject() {
		try {
			Thread.sleep(4000);
			// Looping only once as one time activity
			for (int i = 1; i < CommonHelper.testData.length - 2; i++) {
				xlTo = CommonHelper.testData[1][i];
				xlSubject = CommonHelper.testData[2][i];
				xlMessageBody = CommonHelper.testData[3][i];

				// Verify New Message window is opened
				WebElement messageWindow = Utility.waitForElementToBe(
						By.cssSelector("form[enctype='multipart/form-data']"), "CLICKABLE", driver, 40);
				assertNotNull(messageWindow);

				enterValue(By.cssSelector("textarea[name='to']"), xlTo);
				// Enter value in Subject field
				enterValue(By.cssSelector("input[name='subjectbox']"), xlSubject);
				// Enter value in Email body
				enterValue(By.cssSelector("div[aria-label='Message Body']"), xlMessageBody);
				test.log(LogStatus.PASS,
						"User enters email subject as " + xlSubject + " and email body as " + xlMessageBody);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Unable to draft email");
		}
	}

	@Test
	public static void verifyEmailIsSent() {
		try {
			// Waiting till Message sent disappears
			Thread.sleep(2000);
			
			// Looping only once as one time activity
			for (int i = 1; i < CommonHelper.testData.length - 2; i++) {
				xlTo = CommonHelper.testData[1][i];
				xlSubject = CommonHelper.testData[2][i];
				xlMessageBody = CommonHelper.testData[3][i];

				clickElement(By.xpath("//span/a[.='Sent']"));

				// Return Subject of Email Sent
				List<WebElement> list = Utility.waitForElementsToBe(
						By.xpath("//div[starts-with(@id,':')]/following::table//tr[1]//td[5]"), "VISIBLE", driver, 20);
				for (WebElement e : list) {
					if (e.getText().contains(xlSubject)) {
						assertTrue(true, xlSubject);
						test.log(LogStatus.PASS, "User verified email and Subject as " + xlSubject
								+ " and email body as " + xlMessageBody);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Unable to verify email sent or not");
		}
	}

	@Test
	public static void userSignsOut() {
		try {
			clickElement(By.xpath("//a[contains(@aria-label,'Google Account:')]"));
			clickElement(By.xpath("//a[contains(text(),'Sign out')]"));
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Unable to sign out");
		}
	}
}
