Feature: User Details

  Background:
    * url baseUrl

    @get-user
  Scenario: Get user details
    Given path '/users', id
    When method get
    Then status 200

    @check-email
  Scenario: Check user's email
    * print 'checking email for user ' + id
    * match email contains '@'

    @check-user-details
  Scenario Outline: Check user details are valid
    Given path '/users', id
    When method get
    Then status 200

    Examples:
      |read('classpath:reqres/users/userdata.json') |

  @user3
  Scenario Outline: Validate users.id 3 from YAML
    Given path '/users/<id>'
    When method get
    Then status 200
    * print "Here is actual response", response
    And match response.data.id == <id>
    And match response.data.email == '<email>'
    And match response.data.first_name == '<first_name>'


    Examples:
      | karate.filter(read('classpath:reqres/users/userdata.json'), function(x){ return x.id == 3 }) |
