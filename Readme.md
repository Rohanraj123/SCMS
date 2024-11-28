## Supply Chain Management System (SCMS)
### Overview
The Supply Chain Management System (SCMS) is a robust and scalable platform designed to manage various aspects of the supply chain process. Built with Spring Boot, this application streamlines supplier, inventory, order, customer, and shipment management while providing reporting, analytics, and audit features. Additionally, SCMS incorporates user authentication, email notifications, and DevOps practices using Docker and Kubernetes for containerization and orchestration.

### Features
1. `Supplier Management APIs`

   Add Supplier: Add a new supplier to the system.

   Endpoint: POST /api/suppliers

   Update Supplier: Update supplier details.

   Endpoint: PUT /api/suppliers/{id}

   Delete Supplier: Remove a supplier.

   Endpoint: DELETE /api/suppliers/{id}

   List Suppliers: Retrieve all suppliers.

   Endpoint: GET /api/suppliers


2. `Order Management APIs`

   Create Order: Create a new order.

   Endpoint: POST /api/orders

   Body: Order details including products, quantities, and shipping address.

   Get Order Details: Fetch details of a specific order.

   Endpoint: GET /api/orders/{id}

   List Orders: Retrieve all orders with filtering options.

   Endpoint: GET /api/orders

   Update Order Status: Modify the status of an order.

   Endpoint: PUT /api/orders/{id}/status


3. `Inventory Management APIs`

   View Inventory: Check the current stock of products.

   Endpoint: GET /api/inventory

   Update Stock: Adjust the stock level of a product.

   Endpoint: PUT /api/inventory/{productId}

   Track Stock Levels: Monitor low stock and trigger restocking alerts.

   Endpoint: GET /api/inventory/low-stock


4. `Customer Management APIs`

   Add Customer: Add a new customer to the system.

   Endpoint: POST /api/customers

   Update Customer: Update customer details.

   Endpoint: PUT /api/customers/{id}

   Delete Customer: Remove a customer.

   Endpoint: DELETE /api/customers/{id}

   Get Customer Details: Fetch details of a specific customer.

   Endpoint: GET /api/customers/{id}

   List All Customers: Retrieve all customers.

   Endpoint: GET /api/customers


5. `Shipment/Delivery Management APIs`

   Create Shipment: Create a new shipment for an order.

   Endpoint: POST /api/shipments

   Update Shipment Status: Modify the status of a shipment.

   Endpoint: PUT /api/shipments/{id}/status

   Track Shipment: Retrieve shipment progress.

   Endpoint: GET /api/shipments/{id}


6. `Transaction Management APIs`

   Create Transaction: Record a new transaction.

   Endpoint: POST /api/transactions

   Get Transaction Details: Retrieve details of a specific transaction.

   Endpoint: GET /api/transactions/{id}

   List Transactions: Retrieve all transactions with filtering options.

   Endpoint: GET /api/transactions


7. `Reporting and Analytics APIs`

   Sales Report: Generate sales reports by product, supplier, or time period.

   Endpoint: GET /api/reports/sales

   Inventory Report: Generate inventory reports on current stock and low stock items.

   Endpoint: GET /api/reports/inventory

   Order Report: Generate order reports by status, customer, or time period.

   Endpoint: GET /api/reports/orders


8. `User Management APIs (Admin Role)`

   Create User: Add new users (e.g., warehouse managers).

   Endpoint: POST /api/users

   Update User: Update user details.

   Endpoint: PUT /api/users/{id}

   Delete User: Remove a user.

   Endpoint: DELETE /api/users/{id}

   Get User Details: Fetch user information.

   Endpoint: GET /api/users/{id}


9. `Audit Trail APIs`

   Activity Logs: Retrieve logs of system activities for auditing purposes.

   Endpoint: GET /api/audit/logs

   Log Activities: Record system activities.

   Endpoint: POST /api/audit/logs


10. `Notifications`

    Send Notifications: Notify users about order status, stock updates, etc.

    Endpoint: POST /api/notifications

    Get Notifications: Retrieve user notifications.

    Endpoint: GET /api/notifications


11. `Mail Notification System`

    The system uses JavaMailSender to automatically send notifications when products are added.


12. `DevOps Integration`

    **Dockerized Deployment:** 

The application is containerized using Docker for consistent and reliable deployment.
   
 Dockerfile:

    dockerfile
    `FROM openjdk:17-jdk-slim
    WORKDIR /scms
    COPY target/scms-0.0.1-SNAPSHOT.jar app.jar
    EXPOSE 8080
    ENTRYPOINT ["java", "-jar", "app.jar"]`
    **Kubernetes Support:** Orchestrated using Kubernetes with plans to implement CI/CD pipelines.
    ### Setup Instructions
    Prerequisites
    Java 17
    Maven
    Docker (for containerization)
    Kubernetes (optional)
    Steps to Run Locally
    Clone the repository:

    `git clone https://github.com/your-repo/scms.git
    cd scms`
    Build the project:

    `mvn clean package`
    Run the application:

    `java -jar target/scms-0.0.1-SNAPSHOT.jar`
    **Access the API at:**

    http://localhost:8080

    Run with Docker
    Build the Docker image:

    `docker build -t scms .`
    Run the container:

    `docker run -p 8080:8080 scms`
    Access the API at:

    http://localhost:8080

**Future Enhancements**

    CI/CD integration for automated builds and deployments.

    Enhanced analytics with advanced filtering.
    User roles and permissions for granular access control.
    Contributing
    Contributions are welcome! Please create an issue or pull request on GitHub.