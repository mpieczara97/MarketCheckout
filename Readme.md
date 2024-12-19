# Checkout system
CheckoutService is a REST service that allows you to scan products, 
calculate the price, calculate discounts, and generate a receipt after 
completed purchases.
### **Technology stack:**
1. Java 17
2. SpringBoot 3.4.0
3. Maven 4.0.0

### Running the project and tests:

1. Clone repository. 
2. In the terminal use the command to build the application: mvn clean install. 
3. In the terminal use the command to launch the application: mvn spring-boot:run.
4. In the terminal use the command to run tests: mvn test. 

You can also run the application in your IDE, to do so, build the project with Maven using Lifecycle 'clean' and 'install'. Then run the application in the 'MarketCheckoutApplication.java' class. You can run tests in 'CheckoutServiceTest.test' and in 'MarketCheckoutIntegrationTest.test'

### Logic of price calculation

The logic for calculating discounts is in the 'pricing' package, where I used the startegy design pattern to handle different pricing rules. The 'PricingStrategy' interface defines a common contract for all pricing calculation strategies.

### Controller description
1. A**dding an item to the cart:**
   Endpoint: POST /checkout/scan.
   Allows you to add an item to your cart based on its unique ID.
2. **Calculation of the amount to be paid:**
Endpoint: GET /checkout/total
Returns the total price of the products in the shopping cart, including discounts and pricing rules.
3. **Finalizing purchases and generating a receipt:**
Endpoint: POST /checkout/complete
Closes the shopping process, generates a receipt with a summary of the products and their prices, and clears the shopping cart.