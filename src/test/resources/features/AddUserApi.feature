@smoke
Feature: Add user end point test


  @add_user @api
  Scenario: add student using add user service
    Given new student is added using the add_user endpoint
    And I am on the login page
    When I login as the new user created using add_user endpoint
    Then "Books" page should be displayed



  Scenario: books table
    Given new student is added using the add_user endpoint
    And I am on the login page
    When I login as the new user created using add_user endpoint
    And I navigate to "Books" page
    When I edit book The kite runner
    Then I verify book information
      | name            | author          | year |
      | The kite runner | Khaled Hosseini | 2003 |


  @db
  Scenario: verify book information using get_book_by_id endpoint
    Given I am on the login page
    When I login as a librarian
    And I navigate to "Books" page
    When I open book The kite runner
    Then book information must match the api for The kite runner


