# Library_Management_System
### This is an extended version of Library Management System Web Application Project for final. 

This is a responsive web-based application for managing a library system. The application has user authentication and registration features, as well as password reset by email verification.
It allows librarians to add, edit, delete books and search for books, as well as manage borrowers and check out books to borrowers, while for users it allows to search for books by their title, ISBN or author, and see the details of found books.
### Built With:
- Spring Boot
- Spring Data JPA
- Spring Security
- Maven
- Thymeleaf
- Bootstrap
- PostgreSQL

### Build & Run 
```
  git clone https://github.com/Isken-Human/Library_Management_System_Extended.git
  cd Library_Management_System
  mvn clean package
  java -jar target/library-management-system-0.0.1-SNAPSHOT.jar

```
### Port
```
  http://localhost:8082
```

## Login, Registration and Email Verification
![This is an image](/Photos/Login.png)
![This is an image](/Photos/Registration.png)
![This is an image](/Photos/Forgot_Password.png)

## Librarian Access
![This is an image](/Photos/list_of_books.png)
![This is an image](/Photos/add_new_book.png)
![This is an image](/Photos/search_result.png)
![This is an image](/Photos/list_of_borrowers.png)
![This is an image](/Photos/update_borrower.png)

## User Access
![This is an image](/Photos/user_search.png)
![This is an image](/Photos/search-result.png)
![This is an image](/Photos/book-details.png)




