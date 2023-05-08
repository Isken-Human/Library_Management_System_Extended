# Library_Management_System
### This is an extended version of Library Management System Web Application Project for final. 

This is a responsive web-based application for managing a library system. The application has user authentication and registration features.
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

## Home Page
The home page displays a list of all books in the library. You can add, edit or delete books from here.

![This is an image](/Photos/list_of_books.png)
![This is an image](/Photos/add_new_book.png)


## Search Page
You can search for books by title, author, or ISBN on the search page. The results will be displayed in a table. To clear the search results, click on the "Clear Results" button.

![This is an image](/Photos/search_result.png)



## Borrower Management
On the borrower management page, you can view a list of all borrowers and add new ones.

![This is an image](/Photos/list_of_borrowers.png)
![This is an image](/Photos/add_new_borrower.png)
![This is an image](/Photos/update_borrower.png)





