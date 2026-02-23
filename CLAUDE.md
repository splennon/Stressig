# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Stressig is a German language flashcard application. It serves a REST API backed by an in-memory H2 database (with a PostgreSQL configuration available), and a single-page HTML frontend for flashcard study sessions.

## Commands

### Build
```bash
./mvnw clean package
```

### Run
```bash
./mvnw spring-boot:run
```
The app starts on port **8090** (`http://localhost:8090`).

### Run Tests
```bash
./mvnw test
```

### Run a Single Test
```bash
./mvnw test -Dtest=StressigApplicationTests#contextLoads
```

## Architecture

### Tech Stack
- **Spring Boot 4.0.0-M3**, Java 25, Lombok
- **Spring Data JPA** + **Spring Data REST** (HAL explorer at `/explorer`)
- **H2 in-memory database** (default, PostgreSQL mode) — schema and seed data loaded from `src/main/resources/schema.sql` and `src/main/resources/data.sql` at startup
- **Spring Actuator** available at `/actuator`

### Database
All tables live in the `short` schema. `ddl-auto=validate` means Hibernate validates the schema against `schema.sql` — do not change column names/types without updating both the entity and `schema.sql`.

To switch to PostgreSQL, use `application.postgres.properties` (rename/activate as needed). It points to `jdbc:postgresql://localhost:5432/stressig` with credentials `postgres/postgres`.

### Layers
Each word type (Noun, Verb, Adjective, Adverb) follows the same three-layer pattern:
- **Entity** (`entity/`) — JPA entity mapped to `short.<table>`, uses Lombok `@Data`/`@NoArgsConstructor`/`@AllArgsConstructor`
- **Repository** (`repository/`) — `JpaRepository<T, Integer>` with custom JPQL queries for language-specific searches (gender filtering, conjugated-form lookup, etc.)
- **Controller** (`controller/`) — `@RestController` at `/api/<word-type-plural>` with `@CrossOrigin("*")`, full CRUD plus search/filter endpoints

### API Endpoints (summary)
All four word types expose: `GET /`, `GET /{id}`, `GET /search/german`, `GET /search/english`, `GET /rank/{maxRank}`, `GET /ordered`, `POST /`, `PUT /{id}`, `DELETE /{id}`, `GET /count`.

Additional per-type endpoints:
- **Nouns** `/api/nouns`: `/search/root`, `/search/plural`, `/article/{article}`, `/gender/masculine|feminine|neuter`
- **Verbs** `/api/verbs`: `/search/conjugated`, `/auxiliary/{hilfsverb}`, `/present-conjugation`, `/partizip`
- **Adjectives/Adverbs**: `/search/variation`

### Frontend
A single self-contained HTML/JS file at `src/main/resources/static/index.html`. It fetches word data from the REST API, shuffles the deck, and presents flip-style flashcards with reveal-on-click for individual fields. Keyboard shortcuts: `Space/Enter` reveal all fields, `→/n` next card, `c` correct, `x` incorrect.

### `rank` Field Convention
Every entity has a `rank` integer (1–100) indicating word frequency/importance. The frontend uses rank ranges to offer difficulty levels (Very Common 1–20, Uncommon 80–100, etc.).
