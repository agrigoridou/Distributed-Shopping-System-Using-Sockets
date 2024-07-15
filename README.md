# Distributed-Shopping-System-Using-Sockets

## Introduction
This project is a distributed system designed to handle client requests efficiently using a client-server-server model. The system includes authentication, product viewing, and purchasing capabilities, all managed through a user-friendly graphical interface.

## Key Design Decisions
1. **Architecture**: We chose the client-server-server model to manage multiple client requests efficiently, ensuring the system's effective operation.
2. **Communication Protocol**: A common communication protocol was established between clients and servers before implementation. This protocol includes messages and functions requested by the client, ensuring compatibility and cooperation among system components.
3. **Data Structure**: An in-memory data structure was used to store products available in the store, enabling quick access and updates, thus improving system performance.
4. **User Interface**: A user-friendly graphical environment was provided for the client, allowing authentication, product viewing, and purchasing.
5. **Connection Management**: Mechanisms were implemented to manage connections between clients and servers, ensuring efficient use and release of system resources after a connection is completed, improving performance and reliability.
6. **Security and Integrity**: Efficient checks were implemented on both the client and server sides to ensure the integrity and security of data and actions taken. This includes user authentication, product availability checks, and purchase request verification.

## Project Structure
- **Client Application**: Provides a graphical interface for users to log in, view products, and make purchases.
- **Customer Management Server**: Handles client authentication and forwards product requests to the inventory management server.
- **Inventory Management Server**: Manages product data and processes purchase requests.

## Execution Scenarios

### Failed Connection Scenario
- User inputs credentials on the login screen and clicks "Login."
- If the provided information is invalid or the server connection fails, an error message is displayed, informing the user of the failed connection attempt.

### Successful Connection Scenario
- Upon successful login, the user sees a list of products and can refresh the list, input product codes and quantities, and purchase products using respective buttons.

### Failed Purchase Scenario
- User refreshes the product list, checks availability, and attempts to purchase a product.
- If the purchase is unsuccessful due to lack of stock or other reasons, an error message is displayed.

## Communication Protocol Diagram
The diagram below presents the interactions between the client, customer management server, and inventory management server in a distributed system.

- The client initiates interaction by requesting authentication.
- After successful authentication, the client requests the list of available products from the customer management server.
- The customer management server validates the client’s credentials and requests the product list from the inventory management server.
- The inventory management server responds with the product list, which is then forwarded to the client.
- The client can then make purchase requests, which are processed by the inventory management server. If the purchase is successful, the client is notified accordingly.

## Running the Project
To run the client application, follow the steps below:
1. Open the client application.
2. Enter your credentials on the login screen and click "Login."
3. Upon successful login, use the interface to view products, refresh the product list, and make purchases.

