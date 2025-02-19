
## Race Bet API

This API allows users to place bets on car races and retrieve information about the bets placed.

### Endpoints

#### POST `/api/place`

- **Parameters:**
  - `car` (String): The brand of the car to place a bet on. Allowed brands are "Ferrari", "BMW", "Audi", "Honda".
  - `amount` (double): The amount to bet. Must be greater than 0.

- **Response:**
  - **Success (200):** Returns a string message indicating the bet was placed successfully, or an error message if the car brand is invalid or the amount is not greater than 0.

#### GET `/api/info`

- **Parameters:**
  - `car` (String, optional): The brand of the car to retrieve bet information for. If not provided, information for all cars will be returned.

- **Response:**
  - **Success (200):** Returns a string message with the total bets placed on a specific car, or all cars if no car brand is specified. Returns an error message if the car brand is invalid or no bets have been placed.

### Running the Application

1. **Build the application:**

```sh
mvn clean package
```

2. **Run the application:**

```sh
mvn spring-boot:run
```

### Example Requests

**Place bet:**

```sh
curl  POST "http://localhost:8080/api/place?car=Ferrari&amount=100"
```

**Fetch bet info:**

*Fetch bet info with car parameter:*
```sh
curl "http://localhost:8080/api/info?car=Ferrari"
```
*Fetch all bet info:*
```sh
curl "http://localhost:8080/api/info"
```