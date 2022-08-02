Feature: Validate User Details

  @Smoke @All
  Scenario: Validate schema for Users EndPoint
    Given A user on social media platform
    When hits GET  call to users api
    Then validate status code of response
    And validate schema of users api
    And validate user details in response body

  @Regression @All
  Scenario Outline: Validate user details for a specific userId "<userId>"
    Given A user with a specific userId "<userId>"
    When hits GET  call to users api with path params
    Then validate status code of response
    And validate schema of the user demographic details
    And validate name "<name>",userName "<userName>",email "<email>",address_street "<address_street>", phone "<phone>", website "<website>",company_name "<company_name>"

    Examples:
      | userId | name               | userName       | email                  | address_street  | phone              | website       | company_name |
      | 2      | Ervin Howell       | Antonette      | Shanna@melissa.tv      | Victor Plains   | 010-692-6593 x0912 | anastasia.net | Deckow-Crist |
      | 10     | Clementina DuBuque | Moriah.Stanton | Rey.Padberg@karina.biz | Kattie Turnpike | 024-648-3804       | ambrose.net   | Hoeger LLC   |