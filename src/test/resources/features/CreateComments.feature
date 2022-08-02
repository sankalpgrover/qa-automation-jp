Feature: Post comments on Social media platform and validate them


  @Sanity @All
  Scenario Outline: Validate comments posted on a specific Post "<postId>"
    Given A user with details as postId "<postId>",id "<id>",name "<name>",email "<email>", body "<body>"
    When hits POST call to comments api with request payload
    Then validate status code after posting a comment
    And validate schema of the json response after posting a comment
    And validate postId "<postId>",id "<id>",name "<name>",email "<email>", body "<body>"

    Examples:
      | postId | id  | name          | email          | body                               |
      | 6565   | 101 | sankalpgrover | sang@email.com | test jp morgan @ 3534 context      |
      | 1234   | 209 | testUser      | test@email.com | 1213 svsv ndsd dnkfs sdfs @@##$^^8 |