# Streaming with Spring WebFlux

* Endpoints:
  * ```
    GET http://localhost:8080/api/payments
    Accept: application/json
    ```
  * ```
    GET http://localhost:8080/api/payments
    Accept: text/event-stream
    ```
  * ```
    POST http://localhost:8080/api/payments
    Content-Type: application/json
    
    {"id": integer, "reference": string, "amount": double}
    ```
