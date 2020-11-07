@TeamBlue 
Feature: Validate login function and verify the total price with shipping in MyStore 


Scenario: User able to click on the Sign-in with valid credentials and able to verify the dress prices with shipping cost
	Given user open web browser and navigate to MyStore login screen 
	Then user navigate to home page and verify the page title "My Store" 
	And user click the Sign in button on the right
	And user enter a valid username and password 
	And user click the Sign in button 
	And user click on the upper left corner Dresses link and display should show 5 dresses 
	And user print all the dress prices in descending order on the console 
	And user select the second dress on the current list and remember the price 
	Then user click on next page Proceed to checkout
	Then user navigate to home page and verify the page title "Order - My Store" 
	When user click on the next page to verify the total price with shipping 
	Then user click on sign out button for MyStore and close the window 

