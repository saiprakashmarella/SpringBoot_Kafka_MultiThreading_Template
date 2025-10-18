# Kafka Multi-threading Spring Boot Project 🚀

## Project Description 📄

This project is a **template for Spring Boot Kafka with multi-threading** designed to efficiently process millions of records. It provides a ready-to-use setup for both **Producer** and **Consumer** microservices.

* **Producer:** Publishes transactions to Kafka topic in batches with multi-threading.
* **Consumer:** Consumes transactions from Kafka topic efficiently.

This template is ideal for scenarios where large-scale data processing is required with high throughput and low latency.

## Prerequisites ✅

* Java 17+
* Maven 3.8+
* Apache Kafka installed and running locally 🐘
* Postman (optional) to execute API requests 🖥️

## Postman Collection 📬

You can use the following Postman collection to test the APIs:
[Postman Collection Link](#)
*(Replace `#` with actual link)*

## Producer Application ⚡

**Application Properties (application.properties or application.yml)**

```properties
spring.application.name=Producer
transactions.batch.size=10
transactions.threads.corePoolSize=2
transactions.threads.maxPoolSize=4
transactions.threads.queueCapacity=100
transactions.threads.namePrefix=asyncTransactionExecutor
spring.kafka.topic.name=producer_topic
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.security.user.name=admin
spring.security.user.password=admin

# Optional: Add DB properties if required
```

## Consumer Application 🛠️

**Application Properties (application.properties or application.yml)**

```properties
spring.application.name=Consumer
server.port=8081
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.group-id=consumer
spring.kafka.consumer.properties.spring.json.trusted.packages=*
spring.kafka.topic.name=producer_topic
spring.kafka.consumer.auto-offset-reset=earliest

# Optional: Add DB properties if required
```

## How It Works ⚙️

1. **Producer** batches incoming transactions using Apache Commons Collections.
2. Each batch is processed asynchronously using **CompletableFuture** with a configurable thread pool.
3. **Transaction events** are sent to Kafka topic (`producer_topic`).
4. **Consumer** reads messages from Kafka topic and processes them efficiently.

## Multi-threading & Scalability 🔥

* Configurable thread pool size for batching and asynchronous processing.
* CompletableFuture ensures non-blocking, parallel processing.
* Easily extendable for downstream API calls, validations, or additional business logic.

## Security 🔐

* Basic authentication enabled using Spring Security.
* Default credentials:

  * Username: `admin`
  * Password: `admin`

## Running the Project ▶️

1. Start **Kafka** locally.
2. Run **Producer** Spring Boot application.
3. Run **Consumer** Spring Boot application.
4. Use Postman collection to send transaction payloads.

## Example Transaction Payload 💰

```json
[
  {
    "transactionId": 1,
    "userId": "user1",
    "transactionType": "credit",
    "transactionAmount": "1000",
    "transactionDate": "2025-10-20"
  }
]
```

## Notes 📝

* Adjust `transactions.batch.size` and thread pool sizes in `application.properties` based on expected load.
* Ensure Kafka is running on `localhost:9092` or update properties accordingly.
* This template can be reused for any high-volume Kafka-based Spring Boot microservice project.

---


## License

This project is **open-source** and can be freely used as a template for Kafka + Spring Boot projects.

Author details: saiprakashmarella3277@gmail.com

Made with ❤️ for scalable Kafka + Spring Boot applications.
