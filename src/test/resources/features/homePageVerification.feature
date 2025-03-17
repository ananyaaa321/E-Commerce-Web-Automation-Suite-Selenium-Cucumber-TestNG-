@test
Feature: Home Page Verification

Scenario: Verify elements on the home page after login
Given User is on the login page
  When User logs in with "test0123@example.com" and "Test123" and lands on the home page
 Then The home page elements should be visible
