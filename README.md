 REST Endpoints

  ┌────────┬───────────────────────────────────────────┬────────────────────┐
  │ Method │                    URL                    │    Description     │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ GET    │ /api/products                             │ Get all products   │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ GET    │ /api/products/{id}                        │ Get by ID          │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ POST   │ /api/products                             │ Create product     │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ PUT    │ /api/products/{id}                        │ Update product     │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ DELETE │ /api/products/{id}                        │ Delete product     │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ GET    │ /api/products/category/{cat}              │ Filter by category │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ GET    │ /api/products/search?keyword=mac          │ Search by name     │
  ├────────┼───────────────────────────────────────────┼────────────────────┤
  │ GET    │ /api/products/price-range?min=100&max=500 │ Filter by price    │
  └────────┴───────────────────────────────────────────┴────────────────────┘

  H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:productdb)

  ---
  Run it

  cd springboot-product-crud
  mvn spring-boot:run
