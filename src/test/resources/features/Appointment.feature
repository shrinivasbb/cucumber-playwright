Feature: Appointment Feature

  Background: Open Browser
    Given I am on Appointment Page

  @bookappointment @scenario @positive
  Scenario: Book an Appointment
    When I select facility as "Seoul CURA Healthcare Center"
    And I set Apply for hospital readmission to "true"
    And I select Healthcare Program as "Medicaid"
    And I select Visit Date as "02/12/2025"
    And I enter comments as "This is a Comment"
    Then I click on Book Appointment button
    Then Save should be successful
    And Verify Facility is "Seoul CURA Healthcare Center"
    And Verify ReAdmission is "true"
    And Verify Program is "Medicaid"
    And Verify Visit Date is "02/12/2025"
    And Verify comment is "This is a Comment"

  @bookappointment @scenario @negative 
  Scenario: Book an Appointment with empty fields
    Then I click on Book Appointment button
    And I am on Appointment Page

  @bookappointment @outline
  Scenario Outline: Book Appointment(s)
    When I select facility as <facility>
    And I set Apply for hospital readmission to <readmission>
    And I select Healthcare Program as <program>
    And I select Visit Date as <visitdate>
    And I enter comments as <comment>
    Then I click on Book Appointment button
    Then Save should be successful
    And Verify Facility is <facility>
    And Verify ReAdmission is <readmission>
    And Verify Program is <program>
    And Verify Visit Date is <visitdate>
    And Verify comment is <comment>

    Examples: 
      | facility                          | readmission | program    | visitdate    | comment                 |
      | "Hongkong CURA Healthcare Center" | "true"      | "None"     | "11/11/2030" | "Comment for Line No.1" |
      | "Tokyo CURA Healthcare Center"    | "false"     | "Medicare" | "05/12/2028" | "Comment for Line No.2" |
