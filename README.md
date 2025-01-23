
# Simple CRUD API with Authentication
## Opis Projektu

Projekt realizuje aplikację **CRUD** z autoryzacją użytkowników, która umożliwia:

- **Rejestrację użytkowników**  
- **Logowanie użytkowników**  
- **Przeglądanie listy użytkowników**  
- **Usuwanie użytkowników po ID**  
- **Aktualizowanie danych użytkowników**  

Dostęp do endpointów służących do **wyświetlania listy użytkowników**, **usuwania użytkownika** oraz **aktualizowania danych użytkownika** wymaga **autoryzacji** przy użyciu tokena **JWT** (JSON Web Token).

Aplikacja korzysta z bazy danych **PostgreSQL**, która działa w kontenerze **Docker**, co umożliwia łatwe uruchomienie i zarządzanie środowiskiem.



## Wymagania
- **Docker**: do uruchamiania kontenerów aplikacji i bazy danych.  
- **Postman**: do testowania endpointów API.  
- **Java 17**: wymagana wersja Javy.  
- **Maven**: do budowy aplikacji.  
- -**Spring Boot**: framework do tworzenia aplikacji backendowej.

---

## Instrukcja Uruchomienia

### Krok 1: Przygotowanie środowiska
1. Upewnij się, że masz zainstalowane:
   - Docker  
   - Java 17  
   - Maven  
   - Postman  
2. Skopiuj projekt do wybranego katalogu na swoim komputerze.

---
### Krok 2: Uruchomienie bazy danych PostgreSQL w Dockerze 
1. Otwórz terminal i przejdź do katalogu projektu(tam gdzie jest plik pom.xml): 

```bash
cd "ścieżka/projektu"
```
2.  Uruchom kontener z bazą danych PostgreSQL:
    
 ```bash
docker-compose up -d db
```
Jeśli kontener uruchomi się poprawnie, baza danych będzie dostępna.

---
### Krok 2: Uruchomienie bazy danych PostgreSQL w Dockerze 

1. Będąc w katalogu projektu, zbuduj aplikację za pomocą Maven(tam gdzie jest plik pom.xml):
 ```bash
./mvnw clean install -DskipTests
```
2. Przejdź do katalogu `target` (wewnątrz projektu) i uruchom aplikację komendą poniżej.

 
 ```bash
java -jar AuthCrudApiApp-0.0.1-SNAPSHOT.jar
```

---
### Krok 4: Testowanie API w Postman

1. Otwórz Postman i wejdź na adres http://localhost:8080 pod tym adresem będziesz mógł używać różnych endpointów.
#### Endpoint rejestracji użytkownika:
http://localhost:8080/register
W body umieść następujące dane:
 ```bash
    {
      "username": "nazwa_uzytkownika",
      "password": "haslo"
    }
```

#### Endpoint logowania użytkownika:
POST http://localhost:8080/register
W body umieść następujące dane:
 ```bash
    {
      "username": "nazwa_uzytkownika",
      "password": "haslo"
    }
```
Po pomyślnym zalogowaniu otrzymasz token JWT, który jest niejakim biletem wstępu do innych komend.

#### Endpoint logowania użytkownika:
POST http://localhost:8080/register
W body umieść następujące dane:
 ```bash
    {
      "username": "nazwa_uzytkownika",
      "password": "haslo"
    }
```
Po pomyślnym zalogowaniu otrzymasz token JWT, który jest niejakim biletem wstępu do innych endpointów.


#### Endpoint wyświetlania wszystkich użytkowników:
GET http://localhost:8080/users

#### Endpoint usuwania użytkownika o danym id:
 DELETE http://localhost:8080/users/3
Usuń użytkownika o id 3 (przykładowo)

#### Endpoint aktualizowania użytkownika z danym id:
 PUT  http://localhost:8080/users/3
W body umieść następujące dane:
 ```bash
    {
      "username": "nazwa_uzytkownika",
      "password": "haslo"
    }
```

---
### Konfiguracja

 Zmienianie zmiennych, takich jak token JWT, w pliku konfiguracyjnym W pliku konfiguracyjnym aplikacji (`application.properties` )możesz łatwo zmieniać zmienne, takie jak dane połączenia z bazą danych czy klucz dla tokenu JWT. 
Plik `application.properties`  znajduje się w /target/classes

---
### Użyte technologie
-   **Spring Boot** do stworzenia aplikacji webowej i zarządzania backendem.
-   **JWT** do autoryzacji i uwierzytelniania użytkowników.
-   **PostgreSQL** jako baza danych do przechowywania danych.
-   **Docker** do uruchomienia aplikacji i bazy danych w kontenerach.
-   **Maven** do zarządzania zależnościami i kompilacji projektu.

