Feature: Create, Validate and Update Post on Social media Platform

  @Sanity @Smoke @All
  Scenario Outline: Create a post with userId "<userid>" and postId "<postId>" and validate it
    Given A user with details as postId "<postId>",userId "<userId>",title "<title>",body "<body>"
    When hits POST call with request payload to create a new Post
    Then validate status code after creating a new post
    And validate schema of the json response after creating a new post
    And validate postId "<postId>",userId "<userId>",title "<title>", body "<body>"

    Examples:
      | postId | userId | title         | body           |  |
      | 99     | 101    | sankalpgrover | sang@email.com |  |
      | 102    | 209    | testUser      | test@email.com |  |


  @Smoke @All
  Scenario Outline: Update an existing post with "<userid>" and postId "<postId>"
    Given A user with details as postId "<postId>",userId "<userId>",title "<title>",body "<body>"
    When hits PATCH request with request payload to update an existing post with id "<postId>"
    Then validate status code after updating an existing post
    And validate postId "<postId>",userId "<userId>",title "<title>", body "<body>"

    Examples:
      | postId | userId | title                     | body                      |
      | 100    | 10     | PATCH update by test user | Updated post by test user |