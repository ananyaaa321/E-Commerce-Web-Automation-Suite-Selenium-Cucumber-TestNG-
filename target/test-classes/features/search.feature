@test
Feature: Search and Filtering Products
  Background:
    Given User is on the landing page

  Scenario Outline: Search for a valid product
    When User searches for "<Product>"
    Then The relevant products should be displayed

    Examples:
      | Product    |
      | Laptop     |
      | Smartphone |

  Scenario Outline: Search for an invalid product
    When User searches for "<InvalidProduct>"
    Then an error message should be displayed

    Examples:
      | InvalidProduct   |
      | xyz123           |
      | RandomItem       |

  Scenario Outline: Search with sorting options
    When User searches for "<Product>"
    And User selects sorting option "<SortingType>"
    Then The products should be displayed in the selected order

    Examples:
      | Product    | SortingType          |
      | phone      | Price: Low to High   |
      | laptop     | Price: High to Low   |
      | Laptop     | Name: A to Z         |

  Scenario Outline: Search with display options
    When User searches for "<Product>"
    And User selects display option "<DisplayCount>"
    Then The relevant products should be displayed accordingly

    Examples:
      | Product    | DisplayCount |
      | Laptop     | 4    |
      | phone      | 12    |

 Scenario Outline: Advanced search with subcategories
    When User searches for "<Product>"
    And User enables advanced search
    And User enables subcategory search
    And User clicks the advsearch button
    Then The relevant products should be displayed

    Examples:
      | Product  |
      | Laptop   |
      | phone    |

