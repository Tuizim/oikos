# Documentação: **POST /api/residence**

## 1. Resumo

* **Endpoint:** `POST /api/residence`
* **Descrição:** Cria uma nova residência associada a um proprietário.
  O endpoint recebe um `ResidenceCreateDTO`, executa validações de campo (JSR-380) e validações de negócio (domínio) antes de persistir a entidade `Residence`.

---

## 2. Request

### **Content-Type**

```
application/json
```

### **Body**

Estrutura correspondente a `ResidenceCreateDTO`.

### **Campos (campo : tipo — validações / códigos de erro)**

| Campo            | Tipo                   | Validações            | Código          |
| ---------------- | ---------------------- | --------------------- | --------------- |
| **ownerId**      | UUID                   | `@NotNull`            | `RESIDENCE-001` |
| **name**         | String                 | `@NotBlank`           | `RESIDENCE-002` |
| **propertyType** | PropertyType (enum)    | `@NotNull`            | `RESIDENCE-003` |
| **bedrooms**     | Integer                | `@NotNull`, `@Min(0)` | `RESIDENCE-004` |
| **bathrooms**    | Integer                | `@NotNull`, `@Min(0)` | `RESIDENCE-005` |
| **garageSpots**  | Integer                | `@NotNull`, `@Min(0)` | `RESIDENCE-006` |
| **usableArea**   | BigDecimal             | `@Min(0)`             | `RESIDENCE-007` |
| **totalArea**    | BigDecimal             | `@Min(0)`             | `RESIDENCE-008` |
| **status**       | ResidenceStatus (enum) | `@NotNull`            | `RESIDENCE-009` |
| **description**  | String                 | `@NotBlank`           | `RESIDENCE-019` |
| **address**      | AddressCreateDTO       | `@NotNull`, `@Valid`  | `RESIDENCE-010` |

### Exemplo de Request JSON

```json
{
  "ownerId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "Apartamento Jardim",
  "propertyType": "APARTMENT",
  "bedrooms": 2,
  "bathrooms": 1,
  "garageSpots": 1,
  "usableArea": 55.5,
  "totalArea": 60.0,
  "status": "AVAILABLE",
  "description": "Apartamento confortável próximo ao metrô",
  "address": {
    "street": "Rua A, 100",
    "zipCode": "00000-000",
    "number": "100",
    "neighborhood": "Centro",
    "complement": "Apto 101",
    "cityId": 10
  }
}
```

> Observe que `propertyType` e `status` devem corresponder aos enums disponíveis em `com.oikos.api.enums`.

---

## 3. Validações

### **3.1. Validações JSR-380 (DTO)**

Falhas retornam **400 Bad Request**, com corpo definido por `OikosExceptionHandler`.

Incluem:
`@NotNull`, `@NotBlank`, `@Min`, etc.
Os códigos utilizados são exatamente os definidos em `ResidenceErrorCatalog`.

---

### **3.2. Validações de Negócio (ResidenceValidator)**

Executadas pelo `ResidenceService`.

| Regra                                     | Descrição                                              | Código |
| ----------------------------------------- | ------------------------------------------------------ | --------------- |
| **validateOwnerIsRequester**              | Garante que `ownerId` pertence ao usuário autenticado. | `RESIDENCE-012` |
| **validateResidenceNameIsUniqueForOwner** | Verifica duplicidade: `existsByOwnerIdAndName(...)`.   | `RESIDENCE-013` |
| **validateUsableAreaIsLessThanTotalArea** | Se `usableArea > totalArea`.                           | `RESIDENCE-014`|
| **validatePropertyTypeRules**             | Regras específicas por tipo de propriedade.            | múltiplos  |

#### **Regras por PropertyType**

| Tipo                         | Regra                          | Código          |
| ---------------------------- | ------------------------------ | --------------- |
| **APARTMENT**                | `usableArea` deve ser > 0      | `RESIDENCE-016` |
| **HOUSE**                    | `totalArea` deve ser > 0       | `RESIDENCE-015` |
| **KITNET / STUDIO / DUPLEX** | Regras residenciais se aplicam | —               |

#### **Regras residenciais gerais**

| Condição        | Código          |
| --------------- | --------------- |
| `bedrooms > 0`  | `RESIDENCE-017` |
| `bathrooms > 0` | `RESIDENCE-018` |

---

### **3.3. Validação de Cidade (AddressService)**

* Se `cityId` não existir → erro `RESIDENCE-011` (**CITY_NOT_FOUND**).

---

## 4. Códigos de erro (ResidenceErrorCatalog)

Aqui reorganizados conforme sua classe atual:

| Código          | Significado                                |
| --------------- | ------------------------------------------ |
| `RESIDENCE-001` | OWNER_REQUIRED                             |
| `RESIDENCE-002` | NAME_REQUIRED                              |
| `RESIDENCE-003` | PROPERTY_TYPE_REQUIRED                     |
| `RESIDENCE-004` | BEDROOMS_NEGATIVE                          |
| `RESIDENCE-005` | BATHROOMS_NEGATIVE                         |
| `RESIDENCE-006` | GARAGE_SPOT_NEGATIVE                       |
| `RESIDENCE-007` | USABLE_AREA_NEGATIVE                       |
| `RESIDENCE-008` | TOTAL_AREA_NEGATIVE                        |
| `RESIDENCE-009` | STATUS_REQUIRED                            |
| `RESIDENCE-010` | ADDRESS_REQUIRED                           |
| `RESIDENCE-011` | CITY_NOT_FOUND                             |
| `RESIDENCE-012` | CANNOT_CREATE_RESIDENCE_FOR_ANOTHER_USER   |
| `RESIDENCE-013` | DUPLICATE_RESIDENCE_NAME_FOR_OWNER         |
| `RESIDENCE-014` | USABLE_AREA_IS_BIGGER_THAN_TOTAL_AREA      |
| `RESIDENCE-015` | TOTAL_AREA_MUST_BE_POSITIVE_FOR_HOUSE      |
| `RESIDENCE-016` | USABLE_AREA_MUST_BE_POSITIVE_FOR_APARTMENT |
| `RESIDENCE-017` | BEDROOM_MUST_BE_POSITIVE_FOR_RESIDENTIAL   |
| `RESIDENCE-018` | BATHROOM_MUST_BE_POSITIVE_FOR_RESIDENTIAL  |
| `RESIDENCE-019` | DESCRIPTION_REQUIRED                       |

Tudo compatível agora.

---

## 5. Respostas

### **Sucesso**

* **HTTP 201 Created**
* **Body:** `ResidenceResponseDTO`
  Inclui:
  `id`, `ownerId`, `name`, `propertyType`, `bedrooms`, `bathrooms`, `garageSpots`,
  `usableArea`, `totalArea`, `status`, `description`, `address`,
  `createdAt`, `updatedAt`.

### **Erro de validação (DTO)**

* **HTTP 400 Bad Request**
* Corpo contendo código + timestamp (ex.: `RESIDENCE-002`).

---

## 6. Exemplo (curl)

```bash
curl -X POST "http://localhost:8080/api/residence" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
        "ownerId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "name": "Apartamento Jardim",
        ...
      }'
```