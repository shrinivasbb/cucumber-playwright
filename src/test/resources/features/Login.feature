Feature: Login Feature


	@login
  Scenario: Login to cura application
    And I navigate to Login Page
    When I enter valid "John Doe" and "ThisIsNotAPassword"
    And I Click on Login button 
    Then I am on Appointment Page
    
  #@login @negative
  #Scenario: Login to cura application with wrong creds
    #Given I navigate to Login Page
    #When I enter valid "John" and "ThisIsNotAPassword"
    #And I Click on Login button 
    #Then Login should be unsuccessful

  