# language: en
@purchase @e2e
Feature: Purchase flow on Demoblaze
  Como usuario registrado de Demoblaze
  Quiero agregar productos al carrito y completar una compra.
  Para que pueda comprar productos de la tienda online

  Background:
    Given el usuario navega a la página de inicio de Demoblaze.

  @smoke @single_purchase
  Scenario: Flujo de compra de dos productos
    When el usuario agrega el producto "Samsung galaxy s6" al carrito
    And  el usuario agrega el producto "Nokia lumia 1520" al carrito
    And  el usuario visualiza el carrito de compras
    Then el carrito debe contener 2 productos
    When el usuario realiza un pedido con los siguientes datos:
      | field   | value         |
      | name    | Juan Perez    |
      | country | Ecuador       |
      | city    | Cuenca        |
      | card    | 4111111111111 |
      | month   | 05            |
      | year    | 2026          |
    Then la compra debe ser confirmada exitosamente

  @regression @outline_purchase
  Scenario Outline: Completar compra con diferentes datos de cliente desde JSON
    When el usuario agrega el producto "<product1>" al carrito
    And  el usuario agrega el producto "<product2>" al carrito
    And  el usuario visualiza el carrito de compras
    Then el carrito debe contener 2 productos
    When el usuario realiza un pedido con los datos: name "<name>", country "<country>", city "<city>", card "<card>", month "<month>" and year "<year>"
    Then la compra debe ser confirmada exitosamente

    Examples: Purchase data from testdata/purchase_data.json
      | product1          | product2         | name          | country | city  | card          | month | year |
      | Samsung galaxy s6 | Nokia lumia 1520 | Pamela Torres | Ecuador | Quito | 4111111111111 | 06    | 2027 |
      | Sony xperia z5 | Iphone 6 32gb | Carlos Lopez  | Peru    | Lima  | 5500005555555 | 08    | 2026 |
      | Samsung galaxy s6    | Iphone 6 32gb        | Maria Gomez   | Argentina | Buenos     | 3714496353984   | 12    | 2025 |