# 🍹 Juice Stand - Technical Challenge (Formos)

## 🇬🇧 English

### 💡 Overview

This project is a console-based simulation of a juice stand, created as part of a technical challenge for Formos. It demonstra**object-oriented design**, use of **inheritance**, **polymorphism**, **composition**, **dynamic pricing**, and **inventory management**, all in Java 21 usadon.

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
- Maven 3+
- Terminal

### ▶️ How to Run

```bash
git clone https://github.com/chitorlando/juice-app.git
mvn clean compile
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
```

### If you watch '?' sign at Menu, do not worry, there are emojis here., from MacOS.

## 🇪🇸 Español

### 💡 Resumen

Este proyecto es una simulación por consola de un puesto de jugos, desarrollada como parte de una prueba técnica requerida por FormDemuestra el uso de **programación orientada a objetos, herencia, polimorfismo, composición, precios dinámicos y gestión de inventario**, todo en Java 21 usado

### 🧱 Funcionalidades

- Venta de jugos por sabor y tamaño.
- Cr### eación de bebidas mixtas con dos sabores.
- Precios calculados dinámicamente según los costos de ingredientes.
- S```istema de inventario con alertas por bajo stock.
- Código limpio, mantenible y listo para pruebas.
- Proyecto estructurado con Maven.

### 🧃 Composición de las bebidas

Cada bebida contiene:
- Una fruta (o dos en bebidas mixtas)
- Leche condensada
- Hielo
- Azúcar

Las cantidades varían según el **tamaño**: Pequeño, Mediano o Grande.

### 💰 Precios y ganancias

Los precios se calculan dinámicamente aplicando un margen de ganancia del **50% sobre el costo real** de los ingredientes. Esto asegura consistencia y rentabilidad.

### 📦 Ingredientes (desde JSON)

Los ingredientes se cargan desde un archivo `ingredients.json`, el cual incluye:
- Nombre
- Cantidad
- Tipo (`solido` o `liquido`)
- Costo por unidad

Esto permite escalar fácilmente agregando o modificando ingredientes.

### 🛠️ Requisitos

Terminal de línea de comandos
- Java 17+
- Maven 3.8 o superior
- Terminal

### ▶️ Cómo ejecutar
```bash
git clone https://github.com/chitorlando/juice-app.git
mvn clean compile
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
```

## 👨‍💻 Autor / Author
## Luis Salazar
## Ecuador 🇪🇨

### Si vez '?' signo en el Menú, no te preocupes, hay emojis aqui, de MacOS.