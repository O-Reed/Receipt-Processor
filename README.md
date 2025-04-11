# Receipt Processor

This is a Java Spring Boot application that implements a receipt processing API as specified in the requirements.

## Requirements

- Java 11 or higher
- Maven (for building without Docker)
- Docker (optional, for containerized deployment)

## Project Structure

```
src/main/java/com/receipts/processor/
├── ReceiptProcessorApplication.java     - Main application class
├── controller/                           - REST API controllers
├── dto/                                  - Data Transfer Objects
├── exception/                            - Exception handlers
├── model/                                - Data models
├── repository/                           - In-memory storage
└── service/                              - Business logic
```

## Architecture

This application implements the DTO (Data Transfer Object) pattern to separate the API contracts from internal domain models:

### Data Transfer Objects (DTOs)

- **ItemDTO** - Represents item data for API transfers
- **ReceiptDTO** - Represents receipt data for API transfers
- **PointsResponseDTO** - Represents points response for API transfers
- **ProcessResponseDTO** - Represents process response for API transfers

## Running the Application

### Option 1: Using Maven

```bash
# Build the application
mvn clean package

# Run the application
java -jar target/receipt-processor-0.0.1-SNAPSHOT.jar
```

### Option 2: Using Docker

```bash
# Build the Docker image
docker build -t receipt-processor .

# Run the Docker container
docker run -p 8080:8080 receipt-processor
```

## API Endpoints

### 1. Process Receipt
- **URL**: `/receipts/process`
- **Method**: POST
- **Request Body**: Receipt JSON
- **Response**: JSON with an ID for the processed receipt

### 2. Get Points
- **URL**: `/receipts/{id}/points`
- **Method**: GET
- **Path Parameter**: ID of the receipt
- **Response**: JSON with points awarded for the receipt

## Points Calculation Rules

The application calculates points based on the following rules:

1. One point for every alphanumeric character in the retailer name
2. 50 points if the total is a round dollar amount with no cents
3. 25 points if the total is a multiple of 0.25
4. 5 points for every two items on the receipt
5. If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up
6. 5 points if the total is greater than $10.00
7. 6 points if the day in the purchase date is odd
8. 10 points if the time of purchase is after 2:00pm and before 4:00pm
