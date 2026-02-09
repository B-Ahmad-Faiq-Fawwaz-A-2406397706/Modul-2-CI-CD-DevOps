Name: Ahmad Faiq Fawwaz Abdussalam

NPM: 2406397706

Class: Adpro - B

## Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code
and evaluate the coding standards that you have learned in this module. Write clean code
principles and secure coding practices that have been applied to your code. If you find any
mistake in your source code, please explain how to improve your code. 

### Clean Code Principles Applied:
1. Meaningful Names: I have used descriptive and meaningful names for classes, methods, and variables to enhance code readability. For example, I named my variables and methods based on their functionality, making it easier to understand their purpose at a glance.
2. Function: I ensured that each function performs a single task or responsibility. This makes the code modular and easier to maintain. For instance, I created separate methods for handling different features, which helps in isolating functionality.
3. Comments: I avoid unnecessary comments by writing self-explanatory code.
4. Objects and Data Structures: The Product class serves as a clean data model, using private fields and public accessors to focus strictly on data storage. Meanwhile, the Product Repository abstracts the data access logic, providing a standardized interface for other layers to interact with the database.
5. Error Handling: I implemented proper error handling mechanisms to manage exceptions gracefully. In editProductPage, if a product is not found, a RuntimeException is thrown.

### Secure Coding Practices Applied:
1. Exception Handling: I have implemented proper exception handling to prevent the application from crashing due to unexpected errors. For example, in the editProductPage method, I check if the product exists before proceeding.
2. UUID for IDs: I used UUIDs for product IDs to ensures that product IDs are globally unique and reduces the risk of ID collisions.

### Areas for Improvement:
1. Input Validation: I could improve the code by adding more robust input validation to ensure that user inputs are sanitized and validated before processing. This would help prevent potential security vulnerabilities such as SQL injection or cross-site scripting (XSS) attacks.
2. Custom Exception Handling: Instead of using generic exceptions like RuntimeException, I could create custom exception classes to provide more specific error handling and improve code clarity.