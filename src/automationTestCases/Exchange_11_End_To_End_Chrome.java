/* *******************************************************************
* Test Case Name: Exchange_11_End_To_End_Chrome
* Author: Iain Storrie
* Date: 07/07/2017
* Purpose: This test carries out the end to end Mobility workflow on 
* the Chrome browser for an Exchange 11 job
*
**********************************************************************
* Change Log:
* 
* Date:
* Author: 
* Details:
*
*********************************************************************/

package automationTestCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//Add for Grid session
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utility.*;
import webModule.*;
import pageObjectRepositories.Objects_Appointments_List_Page;
import pageObjectRepositories.Objects_Appointment_Details_Page;
import pageObjectRepositories.Objects_Doorstep_Protocol_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Initial_Risk_Assessment_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Risk_Assessment_Elec_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Capture_Initial_Photo_Of_Elec_Installation_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Initial_Polarity_Check_Martindale_Test_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Initial_Polarity_Check_At_Meter_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Current_Meter_Details_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Remove_Meter_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Remove_Asset_Page;
import pageObjectRepositories.Objects_Electricity_Meter_New_Meter_Details_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Additional_Electricity_Tests_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Initial_Meter_Reading_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Meter_Credit_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Commissioning_Page;
import pageObjectRepositories.Objects_Electricity_Meter_Post_Installation_Checks_Page;
import pageObjectRepositories.Objects_Job_Completion_Energy_Efficiency_Information_Page;
import pageObjectRepositories.Objects_Job_Completion_Summary_Page;
import pageObjectRepositories.Objects_Login_Page;
import pageObjectRepositories.Objects_Job_Completion_Capture_Customer_Signature_Page;


public class Exchange_11_End_To_End_Chrome {

	//Declare our test variables
	public WebDriver driver;
	//Add for Grid session
	//public RemoteWebDriver driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	
	//Get data and open the browser
	@BeforeMethod
	public void beforeMethod() throws Exception {
	
		DOMConfigurator.configure("log4j.xml");
	
	    sTestCaseName = this.toString();
	
	    sTestCaseName = Utils.getTestCaseName(this.toString());
	
	    Log.startTestCase(sTestCaseName);
	
	    ExcelUtils.setExcelFile(Constant.Path_TestData + "Mobility_Automation_Test_Data" + ".xlsm","Data");
		
	    iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_Test_Case_Name);
	
	    driver = Utils.openBrowser(iTestCaseRow);
	    //Add for Grid session
	    //driver = (RemoteWebDriver) Utils.openGridBrowser(iTestCaseRow);
	    
	    //Verify that we are on the correct page
	    Thread.sleep(2000);
	    Objects_Login_Page.btn_Login(driver).isDisplayed();
	    Log.info("Login button displayed");
		
	}
	
	//Run the test
	@Test
	public void main() throws Exception {
			
		Methods_Login.viewPage(driver, sTestCaseName);
		
		Methods_Login.addSuccessValues(driver, sTestCaseName);
		
		Methods_Appointments_List.viewPage(driver, sTestCaseName);
		
		//Select the correct appointment
		driver.findElement(By.xpath(".//*[@id='app']/div/div/workorderlistitem[151]/div/div[1]/div/div[1]/span[1]/span")).click();
		
		//Verify that we are on the Appointment Details page
		Objects_Appointment_Details_Page.btn_Call_Forward(driver).isDisplayed();
		Log.info("Appointment Details page displayed as expected");	
		
		Methods_Appointment_Details.viewPage(driver, sTestCaseName);
		
		//Verify correct Customer Details displayed
		Objects_Appointment_Details_Page.lnk_Customer_Details(driver).click();
		Methods_Appointment_Details.viewCustomerDetails(driver, sTestCaseName, 93);
				
		//Verify correct Job Details displayed
		Objects_Appointment_Details_Page.lnk_Job_Details(driver).click();
		Methods_Appointment_Details.viewJobDetails(driver, sTestCaseName, 93);
				
		//Verify correct Meter Details displayed
		Objects_Appointment_Details_Page.lnk_Meter_Details(driver).click();
		Methods_Appointment_Details.viewMeterDetails(driver, sTestCaseName, 93);
				
		//Click Call Forward to bring up Contact options
		Objects_Appointment_Details_Page.btn_Call_Forward(driver).click();
		
		//Complete Remaining Customer Contact questions
		Objects_Appointment_Details_Page.btn_Contact_Made_Yes(driver).click();
		Objects_Appointment_Details_Page.btn_Appointment_Confirm_Yes(driver).click();
		
		//Click Depart Button to progress to Doorstep Protocol Page
		Objects_Appointment_Details_Page.btn_Depart(driver).click();
		Objects_Appointment_Details_Page.btn_Depart_For_Appointment_OK(driver).click();
		
		//Verify that we are on the Doorstep Protocol page
		Objects_Doorstep_Protocol_Page.btn_Arrive(driver).isDisplayed();
		Log.info("Doorstep Protocol page displayed as expected");	
				
		//Verify that Doorstep Sidebar displayed
		Methods_Doorstep_Protocol.viewSidebar(driver, sTestCaseName);
			
		//Verify Arrive button displayed
		Methods_Doorstep_Protocol.viewPage(driver, sTestCaseName);
		Objects_Doorstep_Protocol_Page.btn_Arrive(driver).click();
			
		//Verify Arrive elements displayed
		Methods_Doorstep_Protocol.viewArrivePage(driver, sTestCaseName);
		Log.info("Doorstep Protocol page arrive elements displayed as expected");
			
		//Invoke Method to complete doorstep protocol questions for a success outcome 
		Methods_Doorstep_Protocol.addSuccessValues(driver, sTestCaseName);
		
		//Verify that we are on the Electricity Meter Initial Risk Assessment page
		Objects_Electricity_Meter_Initial_Risk_Assessment_Page.lbl_Initial_Risk_Assessment(driver).isDisplayed();
		Log.info("Electricity Meter Initial Risk Assessment page displayed as expected");	
				
		//Invoke Method to complete a successful initial risk assessment 
		Methods_Electricity_Meter_Initial_Risk_Assessment.addSuccessValues(driver, sTestCaseName);
		
		//Verify that we are on the Electricity Meter Risk Assessment - Elec page
		Objects_Electricity_Meter_Risk_Assessment_Elec_Page.lbl_Risk_Assessment_Elec(driver).isDisplayed();
		Log.info("Risk Assessment - Elec page displayed as expected");	
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Risk_Assessment_Elec.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Risk Assessment Elec initial elements displayed as expected");
		
		//Verify correct page elements displayed after Risk Assessment - Yes clicked
		Methods_Electricity_Meter_Risk_Assessment_Elec.viewRiskAssessmentYesPage(driver, sTestCaseName);
		Log.info("Electricity Meter Risk Assessment Yes elements displayed as expected");
		
		//Invoke Method to complete a successful risk assessment 
		Methods_Electricity_Meter_Risk_Assessment_Elec.addSuccessValues(driver, sTestCaseName);
	
		//Verify that we are on the Electricity Meter Capture Initial Photo Of Elec Installation page
		Objects_Electricity_Meter_Capture_Initial_Photo_Of_Elec_Installation_Page.lbl_Capture_Initial_Photo_Of_Elec_Installation(driver).isDisplayed();
		Log.info("Capture Initial Photo Of Elec Installation page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Capture_Initial_Photo_Of_Elec_Installation.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Suitable for Smart Installation initial elements displayed as expected");
				
		//Invoke Method to complete a successful photo capture 
		Methods_Electricity_Meter_Capture_Initial_Photo_Of_Elec_Installation.addSuccessValues(driver, sTestCaseName);
		
		//Verify that we are on the Electricity Meter Capture Initial Polarity Check - Martindale Test page
		Objects_Electricity_Meter_Initial_Polarity_Check_Martindale_Test_Page.lbl_Initial_Polarity_Check_Martindale_Test(driver).isDisplayed();
		Log.info("Initial Polarity Check - Martindale Test page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Initial_Polarity_Check_Martindale_Test.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Initial Polarity Check - Martindale Test initial elements displayed as expected");
						
		//Invoke Method to complete a successful Martindale Test 
		Methods_Electricity_Meter_Initial_Polarity_Check_Martindale_Test.addSuccessValues(driver, sTestCaseName);
		
		//Verify that we are on the Electricity Meter Capture Initial Polarity Check - At Meter page
		Objects_Electricity_Meter_Initial_Polarity_Check_At_Meter_Page.lbl_Initial_Polarity_Check_At_Meter(driver).isDisplayed();
		Log.info("Initial Polarity Check At Meter page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Initial_Polarity_Check_At_Meter.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Initial Polarity check At Meter initial elements displayed as expected");
								
		//Invoke Method to complete a successful polarity check 
		Methods_Electricity_Meter_Initial_Polarity_Check_At_Meter.addSuccessValues(driver, sTestCaseName);
	
		//Verify that we are on the Electricity Meter Current Meter Details page
		Objects_Electricity_Meter_Current_Meter_Details_Page.lbl_Current_Meter_Details(driver).isDisplayed();
		Log.info("Current Meter Details page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Current_Meter_Details.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Current Meter Details initial elements displayed as expected");
												
		//Invoke Method to complete current meter details page 
		Methods_Electricity_Meter_Current_Meter_Details.addSuccessValues(driver, sTestCaseName);
	
		//Verify that we are on the Electricity Meter Remove Meter page
		Objects_Electricity_Meter_Remove_Meter_Page.lbl_Remove_Meter(driver).isDisplayed();
		Log.info("Remove Meter page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Remove_Meter.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Remove Meter initial elements displayed as expected");
														
		//Invoke Method to complete remove meter page 
		Methods_Electricity_Meter_Remove_Meter.addSuccessValues(driver, sTestCaseName);
		
		//Verify that we are on the Electricity Meter Remove Asset page
		Objects_Electricity_Meter_Remove_Asset_Page.lbl_Remove_Asset(driver).isDisplayed();
		Log.info("Remove Asset page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Remove_Asset.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Remove Asset initial elements displayed as expected");
																
		//Invoke Method to complete remove asset page 
		Methods_Electricity_Meter_Remove_Asset.addSuccessValues(driver, sTestCaseName);
		
		//Verify that we are on the Electricity Meter New Meter Details page
		Objects_Electricity_Meter_New_Meter_Details_Page.lbl_New_Meter_Details(driver).isDisplayed();
		Log.info("New Meter Details page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_New_Meter_Details.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter New Meter Details initial elements displayed as expected");
																		
		//Invoke Method to complete new meter details page 
		Methods_Electricity_Meter_New_Meter_Details.addSuccessValues(driver, sTestCaseName);
				
		//Verify that we are on the Electricity Meter Additional Electricity Tests & Checks page
		Objects_Electricity_Meter_Additional_Electricity_Tests_Page.lbl_Additional_Electricity_Tests(driver).isDisplayed();
		Log.info("New Meter Details page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Additional_Electricity_Tests.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Additional Electricity Tests initial elements displayed as expected");
																				
		//Invoke Method to complete Additional Electricity Tests page 
		Methods_Electricity_Meter_Additional_Electricity_Tests.addSuccessValues(driver, sTestCaseName);
						
		//Verify that we are on the Electricity Meter Initial Meter Reading page
		Objects_Electricity_Meter_Initial_Meter_Reading_Page.lbl_Inital_Meter_Reading(driver).isDisplayed();
		Log.info("Initial Meter Reading page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Initial_Meter_Reading.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Initial Meter Reading initial elements displayed as expected");
																						
		//Invoke Method to complete Initial Meter Reading page 
		Methods_Electricity_Meter_Initial_Meter_Reading.addSuccessValues(driver, sTestCaseName);
			
		//Verify that we are on the Electricity Meter Meter Credit page
		Objects_Electricity_Meter_Meter_Credit_Page.lbl_Meter_Credit(driver).isDisplayed();
		Log.info("Meter Credit page displayed as expected");
				
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Meter_Credit.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Meter Credit initial elements displayed as expected");
																								
		//Invoke Method to complete Meter Credit page 
		Methods_Electricity_Meter_Meter_Credit.addSuccessValues(driver, sTestCaseName);
			
		//Verify that we are on the Electricity Meter Commissioning page
		Objects_Electricity_Meter_Commissioning_Page.lbl_Commissioning(driver).isDisplayed();
		Log.info("Initial Meter Reading page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Commissioning.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Commissioning initial elements displayed as expected");
																								
		//Invoke Method to complete Commissioning page 
		Methods_Electricity_Meter_Commissioning.addSuccessValues(driver, sTestCaseName);
									
		//Verify that we are on the Electricity Meter Post Installation Checks page
		Objects_Electricity_Meter_Post_Installation_Checks_Page.lbl_Post_Installation_Checks(driver).isDisplayed();
		Log.info("Initial Meter Reading page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Electricity_Meter_Post_Installation_Checks.viewPage(driver, sTestCaseName);
		Log.info("Electricity Meter Post Installation Checks initial elements displayed as expected");
																										
		//Invoke Method to complete Post Installation Checks page 
		Methods_Electricity_Meter_Post_Installation_Checks.addSuccessValues(driver, sTestCaseName);
																																					
		//Verify that we are on the Job Completion Energy Efficiency Information page
		Objects_Job_Completion_Energy_Efficiency_Information_Page.lbl_Energy_Efficiency_Information(driver).isDisplayed();
		Log.info("Job Completion Energy Efficient Information page displayed as expected");
	
		//Verify Initial page elements displayed
		Methods_Job_Completion_Energy_Efficient_Information.viewPage(driver, sTestCaseName);
		Log.info("Job Completion Enerfy Efficiency Information initial elements displayed as expected");
																																		
		//Invoke Method to complete Energy Efficiency information page 
		Methods_Job_Completion_Energy_Efficient_Information.addSuccessValues(driver, sTestCaseName);
																				
		//Verify that we are on the Job Completion Summary page
		Objects_Job_Completion_Summary_Page.lbl_Summary(driver).isDisplayed();
		Log.info("Job Completion Summary page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Job_Completion_Summary.viewPage(driver, sTestCaseName);
		Log.info("Job Completion Summary initial elements displayed as expected");
																																				
		//Invoke Method to complete Summary page 
		Methods_Job_Completion_Summary.addSuccessValues(driver, sTestCaseName);
																						
		//Verify that we are on the Job Completion Capture Customer Signature page
		Objects_Job_Completion_Capture_Customer_Signature_Page.lbl_Capture_Customer_Signature(driver).isDisplayed();
		Log.info("Job Completion Summary page displayed as expected");
		
		//Verify Initial page elements displayed
		Methods_Job_Completion_Capture_Customer_Signature.viewPage(driver, sTestCaseName);
		Log.info("Job Completion Capture Customer Signature initial elements displayed as expected");
																																						
		//Invoke Method to complete Customer Signature page 
		Methods_Job_Completion_Capture_Customer_Signature.addSuccessValues(driver, sTestCaseName);
	
		//Verify that we have returned to the Appointments list page
	    Objects_Appointments_List_Page.hdr_First_Appointment(driver).isDisplayed();
		Log.info("First Appointment Header displayed");
		
	}
	
	//Log out
	@AfterMethod
	public void afterMethod() {
	
	    driver.quit();
	    
	    Log.endTestCase(sTestCaseName);        
	
	}
}

