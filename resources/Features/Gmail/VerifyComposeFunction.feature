Feature: Verify Compose function in Gmail

Scenario: Login to Gmail account
	Given user launches "Gmail" website 
	When user read the test data sheet "Scenario_1" from file "GmailTestdata" 
	And user submit username and password 
	Then user should be logged in 
	#
	When user clicks on "Compose" button
	And enters email body with subject
	And user clicks on "Send" button
	Then verify email is sent 
	#
	Then user signs out 
	And user closes the browser 
	
	