package util;




public class FBConstants {
	public static final String Browser = "Firefox";
	
	// locators
	public static final String Trasction = "//*[@id='ctl00_TabbedPanels1']/ul/li[2]";
	public static final String Calendar = "//*[@id='ctl00_CphBody_txtDate']";
	public static final String Search_Button = "//*[@id='ctl00_CphBody_btnFilter']"; 
	public static final String Received_EditBox=	"//*[@id='ctl00_CphBody_GrdDailyUpdates_ctl02_txtReceived']";
	
	public static final String Received_EditBox2="//*[@id='ctl00_CphBody_GrdDailyUpdates_ctl03_txtReceived']";
	public static final String Coded_EditBox = "//*[@id='ctl00_CphBody_GrdDailyUpdates_ctl02_txtCoded']";
	public static final String Save_Button=	"//*[@value='Save' and @type='submit']";
	public static final String Status=	"//*[@id='ctl00_CphBody_GrdMonthlyResults']/tbody/tr[1]/td[8]";
	public static final String From_Date=	"//*[@id='ctl00_CphBody_txtDate']";
	public static final String To_Date=	"//*[@id='ctl00_CphBody_txtToDate']";
	public static final String Record_List="//*[@id='ctl00_CphBody_GrdApproveEntries_length']/label/select";
	public static final String TL_Filter =	"//*[@id='ctl00_CphBody_GrdApproveEntries']/thead/tr/th[6]";
	public static final String Approved_Button = "//*[@id='ctl00_CphBody_BtnSubmit']";
	public static final String Reject_Button = "//*[@id='ctl00_CphBody_btnReject']";
	
	//List
	public static final String Admin="//*[@id='ctl00_TabbedPanels1']/ul/li[3]";
	

	// URLs-uat
	public static final String URL_APP = "http://10.1.2.85/aspire";
	public static final String Loginlink = "http://10.1.2.85/aspire/#/login";
	public static final String Data_Entry = "http://10.1.2.85/OMH/Transactions/DailyEntry.aspx";
	public static final String Data_View_Entry=	"http://10.1.2.85/OMH/Transactions/ViewDailyEntries.aspx";
	public static final String Approval=	"http://10.1.2.85/OMH/Transactions/Approvals.aspx"; 
	public static final String ApplicationURL=	"http://PreetiK:Happy@123@10.1.2.85/OMH/";
	public static final String unitRate ="//*[@id='ctl00_CphBody_txtUnitRate']";
	public static final String Submit ="//*[@id='ctl00_CphBody_BtnSubmit']";
	public static final String Records=	"//*[@id='ctl00_CphBody_GrdUnitRateNotNull_length']/label/select";
	

	
	
	//Account Configuration Page
	public static final String AccountConfig="http://10.1.2.85/OMH/Configuration/UnitRate.aspx";
	public static final String Account_Name="//*[@id='ctl00_CphBody_ddlAccount']";
	public static final String Billing_Type="//*[@id='ctl00_CphBody_ddlBillingType']";
    public static final String Revenue_Type="//*[@id='ctl00_CphBody_ddlRevenueType']";
    public static final String Show_button="//*[@id='ctl00_CphBody_BtnShow']";
    public static final String SearchBox = "//*[@id='ctl00_CphBody_GrdUnitRateNotNull_filter']/label/input";
	public static final String AccountError = "//*[@id='ctl00_CphBody_lblAccError']";
	public static final String BillingError ="//*[@id='ctl00_CphBody_lblBiilingTypeError']";
	public static final String RevenueError ="//*[@id='ctl00_CphBody_lblRevenueTypeError']";
	public static final String CancelButton = "//*[@id='ctl00_CphBody_btnCancel']";
			
	
	//TL Configuration
	public static final String TLConfig="//*[@id='ctl00_TabbedPanels1']/div/div[3]/a[2]";
	public static final String ADDTL_Button="//*[@id='ctl00_CphBody_btnAdd']";
	public static final String Vertical = "//*[@id='ctl00_CphBody_ddlVertical']";
	public static final String Client = "html/body/form/div[5]/div/div[2]/div/div[1]/div[2]/select";
	public static final String Project = "//*[@id='ctl00_CphBody_ddlProject']";
	public static final String Process = "//*[@id='ctl00_CphBody_ddlProcess']";
	public static final String SDH = "//*[@id='ctl00_CphBody_ddlServiceDeliveryHead']";
	public static final String SM = "//*[@id='ctl00_CphBody_ddlSeniorManager']";
	public static final String OM = "//*[@id='ctl00_CphBody_ddlOperationManager']";
	public static final String TL = "//*[@id='ctl00_CphBody_ddlTeamLead']";
	public static final String Location = "//*[@id='ctl00_CphBody_ddlLocation']";
	public static final String Submit_Button ="//*[@id='ctl00_CphBody_BtnSubmit']";

			

	//paths
	public static final String REPORTS_PATH = System.getProperty("user.dir")+"\\reports\\";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\data\\Data.xlsx";
	public static final String TESTDATA_SHEET = "TestData";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";

	

	



	



	



	



	
	


		 
	}




	


	


	




	



	
