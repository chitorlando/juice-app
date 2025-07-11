# 🍹 Juice Stand - Technical Challenge (Formos)

## 🇬🇧 English

### 💡 Overview

This project is a console-based simulation of a juice stand, created as part of a technical challenge for Formos. It demonstrates **object-oriented design**, use of **inheritance**, **polymorphism**, **composition**, **dynamic pricing**, and **inventory management**, all in Java 18 using Maven.

### 🧱 Features

- Sell drinks by flavor and size.
- Create mixed drinks with any two flavors.
- Dynamic pricing based on real ingredient costs.
- Inventory system with low-ingredient warnings.
- Structured, maintainable, and testable code.
- Clean project structure using Maven.

### 🧃 Drink Composition

Each drink includes:
- A selected fruit (or two, in case of mixed drinks).
- Condensed milk
- Ice
- Sugar

The amounts vary depending on the selected **size** (Small, Medium, Large).

### 💰 Pricing & Profit

Prices are calculated dynamically based on ingredient cost, applying a consistent **50% profit margin**. This keeps the pricing responsive to ingredient fluctuations.

### 📦 Ingredients (from JSON)

Ingredients are loaded from a `ingredients.json` file, with:
- Name
- Quantity
- Type (`solid` or `liquid`)
- Unit cost

This allows for easy scalability by simply editing the JSON file.

### 🛠️ Requirements

- Java 17+
- Maven 3.8+
- Terminal

### ▶️ How to Run

```bash
git clone https://github.com/your-user/formos-juice-stand.git
cd formos-juice-stand
mvn compile
mvn exec:java -Dexec.mainClass="com.formos.test.App"

src/
├── main/
│   ├── java/
│   │   └── com.formos.test/
│   │       ├── model/
│   │       ├── service/
│   │       ├── dto/
│   │       └── App.java
│   └── resources/
│       └── ingredients.json
└── test/

## 🇪🇸 Español

### 💡 Resumen

Este proyecto es una simulación por consola de un puesto de jugos, desarrollada como parte de una prueba técnica requerida por Formos. Demuestra el uso de **programación orientada a objetos, herencia, polimorfismo, composición, precios dinámicos y gestión de inventario**, todo en Java 18 con Maven.

###🧱 Funcionalidades
