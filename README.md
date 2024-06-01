# :floppy_disk: Topic of the project:

XML to JSON

----------------------------------

### Requirements: 
1.	Endpoint which triggers the process of reading the file and respond with the number of records found in the file
2.	Endpoint returning a list of all products in json format
3.	Endpoint returning the product by the given name (in json format)
4.	Tests
5.	Documentation

------------------------------------------

### Contents:

#### 1.Overview


#### 2.Endpoints

•	2.1 Load Products from File

•	2.2 Get Product Count

•	2.3 Get All Products

•	2.4 Get Product by Name

#### 3.Exception Handling
	
 •	3.1 FileNotFound
	
 •	3.2 FileParsingException
	
 •	3.3 Endpoint not found
	
 •	3.4 Product not found

#### 4. Tests
	
 •	4.1 testLoadProducts
	
 •	4.2 testGetProductCount
	
 •	4.3 testGetAllProducts
	
 •	4.4 testGetProductByName
	
 •	4.5 testGetProductByName_NotFound
	
 •	4.6 testGetProductCountWhenEmpty

#### 5. Project structure
	
 •	5.1 ProductController
	
 •	5.2 Model 
	
 •	5.3 Service
	
 •	5.4 exceptions
	
 •	5.5 Resources
 
####  6. Code explenation
	
 •	6.1 ProductController
	
 •	6.2 ProductService
	
 •	6.3 GlobalExceptionHandler

 --------------------------------------

 ### 1.Overview

 This application is a Spring Boot-based solution for managing products stored in an XML file. It provides endpoints to load products, retrieve product counts, fetch all products, and get a product by its name. Additionally, the application includes robust exception handling and unit tests to ensure reliability.

 ### 2.Endpoints

#### 2.1 Load Products from File
 
Endpoint: /products/load

Method: GET
Description: Loads products from an XML file specified in the application properties and stores them in memory. It is loaded automatically at Spring Boot applications start.

Response:

•	200 OK - Products loaded successfully.

•	500 Internal Server Error - Failed to load products.

#### 2.2 Get Product Count

Endpoint: /products/count

Method: GET

Description: Returns the number of products loaded.

Response:

•	200 OK - Number of products.

Result:

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/62473bc9-6915-4a87-abac-7d67dbd02b41)
 
#### 2.3 Get All Products

Endpoint: /products

Method: GET

Description: Returns a list of all products in JSON format.

Response:

•	200 OK - List of products.

Result:

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/1b393258-352f-44ce-b588-cc32724b9f4c)

#### 2.4 Get Product by Name

Endpoint: /products/{name}

Method: GET

Description: Returns the product with the specified name.

Response:

•	200 OK - Product details.

•	404 Not Found - Product not found.

Result:

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/ba6d8dda-45ff-4830-b9a8-9ca0bb660ff8)

 --------------------------------------------------------------------------------------

### 3.Exception Handling

#### 3.1 FileNotFound

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/19195688-8a60-48de-99b2-e9f5a6041213)

Result:

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/570d4332-84bf-4d4e-af9b-43ed43e88c8d)

#### 3.2 FileParsingException

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/dd7bb7ca-b2b0-4fba-8a98-8cd2fa0b87af)

Result:

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/f2ffcbce-fa2b-4f81-906f-bb0164c47bd5)

#### 3.3 Endpoint not found

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/0d52a055-0b72-4dae-9dc7-778924582b15)

#### 3.4 Product not found

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/e320441c-f597-4ba8-961f-81fef511f1e1)

---------------------------------------------------------------------------------------------

### 4. Tests


#### 4.1 testLoadProducts

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/654b19ef-fc3c-41b6-b2b4-e831df14d8e6)

 
•	Purpose: To verify that the /products/load endpoint returns the response "Products loaded successfully" after loading the products.

•	Scenario: Call the /products/load endpoint using the GET method.

•	Expectation: The response status is 200 (OK) and the response content is "Products loaded successfully".

#### 4.2 testGetProductCount

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/a5f843fe-5dcc-4c43-a3a5-76320550f5a1)

 
•	Purpose: To check if the /products/count endpoint returns the correct number of products.

•	Scenario: Call the getNumberOfProducts method of the ProductService, which returns 3.

•	Expectation: The response status is 200 (OK) and the response content is "3".

#### 4.3 testGetAllProducts

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/c5159bb2-0ba6-41c7-b540-42bd6ee9ee5b)

•	Purpose: To verify that the /products endpoint returns all products in JSON format.

•	Scenario: The getProducts method of the ProductService returns a list of three products: apple, orange, and glass.

•	Expectation: The response status is 200 (OK), the content type is application/json, and the JSON response contains the products with names "apple", "orange", and "glass".

#### 4.4 testGetProductByName

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/51f6816e-e043-4783-ac69-27dda7d06e9d)

•	Purpose: To verify that the /products/{name} endpoint returns the correct product when a valid name is provided.

•	Scenario: The getProductByName method of the ProductService returns the product with the name "apple".

•	Expectation: The response status is 200 (OK), the content type is application/json, and the JSON response contains the product with the name "apple".

#### 4.5 testGetProductByName_NotFound

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/f0448be5-aef7-48e9-ade7-fdadf51b4ca5)

•	Purpose: To check if the /products/{name} endpoint returns a 404 status when the product name is not found.

•	Scenario: The getProductByName method of the ProductService throws a ProductNotFoundException for the name "apple2".

•	Expectation: The response status is 404 (Not Found) and the JSON response contains the message "Not found: apple2".

#### 4.6 testGetProductCountWhenEmpty

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/459930b4-2193-4313-b8a3-4e87069ac0ab)

•	Purpose: To verify that the /products/count endpoint returns 0 when there are no products.

•	Scenario: The getNumberOfProducts method of the ProductService returns 0.

•	Expectation: The response status is 200 (OK) and the response content is "0".

----------------------------------------------------------------------------------------

### 5.Project structure

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/2a62bc28-8d1c-4d5b-9269-6abd83090a1e)

#### 5.1 ProductController

•	This is the main controller that handles HTTP requests related to products.

•	It includes endpoints for loading products, getting product counts, retrieving all products, and fetching products by name.

#### 5.2 Model 

Product.java

•	This is a model class representing a single product.

•	Annotated with JAXB annotations for XML binding.

Products.java

•	Wrapper class containing a list of Product objects.

•	Used for unmarshalling the XML file.


#### 5.3 Service

ProductService.java

•	Contains logic for loading products from an XML file and providing product-related services.

#### 5.4 exceptions

FileNotFoundException.java

•	Custom exception thrown when a specified XML file is not found.

FileParsingException.java

•	Custom exception thrown when there is an error parsing the XML file.

GlobalExceptionHandler.java

•	Centralized exception handling class annotated with @ControllerAdvice.

•	Defines methods to handle specific exceptions and return appropriate HTTP responses.

ProductNotFoundException.java

•	Custom exception thrown when a requested product is not found.

#### 5.5 Resources

application.properties

•	Configuration file for Spring Boot application properties.

•	Contains properties like file.path to specify the location of the XML file.

------------------------------------------------------------------------------

### 6 Code explenation

#### 6.1 ProductController

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/3fa43097-05c6-4500-b463-af5ac848f31b)

@Autowired private ProductService productService;: This line is injecting a ProductService instance into the controller. The ProductService is presumably a class that contains business logic related to products.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/fb24ead2-9689-4544-ab56-661c63e55aab)

@Value("${file.path}") private String filePathRes;: This line is injecting a value from the application's properties file. The value corresponds to the key file.path.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/cbe580a7-b695-4b40-ab2a-4fcc1f4258ac)

@GetMapping("/load") public ResponseEntity<String> loadProducts() {...}: This method is a GET endpoint at "/products/load". It calls the loadProductsFromFile method from ProductService with filePathRes as the argument. Depending on the outcome of the method call, it returns a different HTTP status and message.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/7ab29d6d-7edf-4fc2-bac7-04406454b589)

@GetMapping("/count") public ResponseEntity<Integer> getProductCount() {...}: This method is a GET endpoint at "/products/count". It returns the number of products, which it gets by calling the getNumberOfProducts method from ProductService.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/49f8e3ca-1170-4f1b-bda8-cbe0a94497d9)

@GetMapping public ResponseEntity<List<Product>> getAllProducts() {...}: This method is a GET endpoint at "/products". It returns a list of all products, which it gets by calling the getProducts method from ProductService.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/e08b5587-1248-4be3-bfcf-cf88245e2078)

@GetMapping("/{name}") public ResponseEntity<Product> getProductByName(@PathVariable String name) {...}: This method is a GET endpoint at "/products/{name}", where "{name}" is a variable part of the URL. It returns a product with the given name, which it gets by calling the getProductByName method from ProductService. If the product is not found, it throws a ProductNotFoundException.

#### 6.2 ProductService

![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/f9edb6b6-f148-43f5-88d8-91b5c0fcbb33)

@PostConstruct public void init() {...}: This method is annotated with @PostConstruct, which means it's called after the service is constructed and dependency injection is complete. It calls the loadProductsFromFile method with filePath as the argument.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/47349e55-c414-46ca-86d5-ae119b9b4605)

public void loadProductsFromFile(String filePath) throws JAXBException, IOException {...}: This method takes a file path as an argument, reads the file, unmarshals the XML content into Products objects, and assigns the list of products to the products variable. If the file doesn't exist or there's an error parsing the file, it throws an exception.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/b6a2be4d-be49-492b-a5f3-e1d37e98b7f5)

public Product getProductByName(String name) {...}: This method takes a product name as an argument, searches the list of products for a product with that name, and returns the product. If the product is not found, it throws a ProductNotFoundException.  

#### 6.3 GlobalExceptionHandler

GlobalExceptionHandler is used to handle exceptions globally across the application. It's annotated with @ControllerAdvice, which means it's a centralized point for exception handling.

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/01a99537-f8dd-4d3f-bcdf-4168895f6594)

@ExceptionHandler(FileNotFoundException.class): This annotation indicates that the following method handleFileNotFoundException should be used to handle exceptions of type FileNotFoundException. The method creates an ErrorResponse object with the current time, HTTP status code for "Not Found" (404), and the exception message, then returns it in a ResponseEntity with the same status code.  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/0a1155b2-ac01-405d-aee4-e1b4f522a483)

@ExceptionHandler(FileParsingException.class): Similar to the previous method, but this one handles FileParsingException. It returns a ResponseEntity with an HTTP status code for "Internal Server Error" (500).  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/bf2dd8fd-c0b1-4c5a-9684-9177d90b2cc6)

@ExceptionHandler(ProductNotFoundException.class): This method handles ProductNotFoundException. It returns a ResponseEntity with an HTTP status code for "Not Found" (404).  

 ![image](https://github.com/LukaszKrolicki/XMLproject/assets/54467678/e6b68424-a694-4564-bc4c-c367b42710b6)

@ExceptionHandler(Exception.class): This method is a catch-all for any other type of Exception that hasn't been handled by the previous methods. It returns a ResponseEntity with an HTTP status code for "Internal Server Error" (500) and a generic error message.



















 
