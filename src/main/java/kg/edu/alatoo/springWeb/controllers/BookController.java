package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.dto.BookDTO;
import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.AuthorRepository;
import kg.edu.alatoo.springWeb.repos.BookDTORepository;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import kg.edu.alatoo.springWeb.repos.PublisherRepository;
import kg.edu.alatoo.springWeb.services.BookImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final BookDTORepository bookDTORepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    private final BookImageService bookService;

    private static ModelMapper mapper = new ModelMapper();




    public BookController(BookRepository bookRepository, BookDTORepository bookDTORepository, PublisherRepository publisherRepository, AuthorRepository authorRepository, BookImageService bookService) {
        this.bookRepository = bookRepository;
        this.bookDTORepository = bookDTORepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }



    @GetMapping("/books/details/{id}")
    public String viewBookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "details";
    }

    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "edit_book";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());

        return "book-form";
    }




    /*
    @PostMapping("/books/{id}")
    public String updateBook (@PathVariable Long id, @ModelAttribute("book") Book book,
                                 Model model) {


        Optional<Book> existingBook = bookRepository.findById(id);
        existingBook.get().setId(id);
        existingBook.get().setTitle(book.getTitle());
        existingBook.get().setIsbn(book.getIsbn());
        existingBook.get().setPublishedYear(book.getPublishedYear());
        existingBook.get().setPublisher(book.getPublisher());
        existingBook.get().setAuthors(book.getAuthors());

        bookRepository.save(existingBook.get());
        return "redirect:/";

    }*/

    @PostMapping("/books/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute("book") Book book,
                             @RequestParam("file") MultipartFile file,
                             Model model) {

        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book updatedBook = existingBook.get();
            updatedBook.setId(id);
            updatedBook.setTitle(book.getTitle());
            updatedBook.setIsbn(book.getIsbn());
            updatedBook.setPublishedYear(book.getPublishedYear());
            updatedBook.setPublisher(book.getPublisher());
            updatedBook.setAuthors(book.getAuthors());

            if (!file.isEmpty()) {
                try {
                    updatedBook.setImage(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            bookRepository.save(updatedBook);
        }

        return "redirect:/";
    }


    @PostMapping("/save")
    public String saveBook(@RequestParam("file") MultipartFile file,
                           @ModelAttribute Book book) {
        bookService.saveBookToDB(file, book.getTitle(), book.getIsbn(), book.getAuthors(), book.getPublisher(), book.getPublishedYear());


        return "redirect:/";
    }


    /*
    @GetMapping("/search")
    public String searchBooks(@RequestParam("q") String searchTerm,
                              @RequestParam("category") String category,
                              Model model) {
        List<Book> books = null;
        if (category.equals("title")) {
            books = bookRepository.findByTitleContainingIgnoreCase(searchTerm);
        } else if (category.equals("author")) {
            books = bookRepository.findByAuthorsNameContainingIgnoreCase(searchTerm);
        } else if (category.equals("isbn")) {
            books = bookRepository.findByIsbnContainingIgnoreCase(searchTerm);
        }
        model.addAttribute("books", books);
        return "search-results";
    }


     */
    /*
    @GetMapping("/search")
    public String searchBooks(@RequestParam("q") String searchTerm,
                              @RequestParam("category") String category,
                              Model model) {
        List<BookDTO> books = null;
        if (category.equals("title")) {
            books = findByTitleContainingIgnoreCase(searchTerm);
        } else if (category.equals("author")) {
            books = findByAuthorsNameContainingIgnoreCase(searchTerm);
        } else if (category.equals("isbn")) {
            books = findByIsbnContainingIgnoreCase(searchTerm);
        }
        model.addAttribute("book", books);
        return "search-results";
    }
    */

    @GetMapping("/search")
    public String searchBooks(@RequestParam("q") String searchTerm,
                              @RequestParam("category") String category,
                              Model model) {
        List<BookDTO> books = null;
        if (category.equals("title")) {
            books = bookDTORepository.findByTitleContainingIgnoreCase(searchTerm);
        } else if (category.equals("author")) {
            books = bookDTORepository.findByAuthorsNameContainingIgnoreCase(searchTerm);
        } else if (category.equals("isbn")) {
            books = bookDTORepository.findByIsbnContainingIgnoreCase(searchTerm);
        }
        model.addAttribute("book", books);
        return "search-results";

    }



//
//    public List<BookDTO> findByAuthorsNameContainingIgnoreCase(String searchTerm) {
//        return allBookWithoutPhotos().stream()
//                .filter(book -> book.getAuthors().stream().anyMatch(author ->
//                        author.getName().toLowerCase().contains(searchTerm.toLowerCase())))
//                .collect(Collectors.toList());
//    }
//    List<BookDTO> findByTitleContainingIgnoreCase(String searchTerm){
//        List<BookDTO> booksWithTitle = allBookWithoutPhotos().stream()
//                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
//                .toList();
//        return booksWithTitle;
//
//    }
//    List<BookDTO> findByIsbnContainingIgnoreCase(String searchTerm){
//        List<BookDTO> filteredBooks = allBookWithoutPhotos().stream()
//                .filter(book -> book.getIsbn().toLowerCase().contains(searchTerm.toLowerCase()))
//                .sorted(Comparator.comparing(BookDTO::getIsbn))
//                .collect(Collectors.toList());
//
//        return filteredBooks;
//    }
//
//
//    List<BookDTO> allBookWithoutPhotos(){
//        List<BookDTO> results = new ArrayList<>();
//        for (Book book :
//                bookRepository.findAll()) {
//            results.add(mapper.map(book, BookDTO.class));
//        }
//
//        return results;
//    }
//






    @DeleteMapping("/delete/{bookId}")
    @ResponseBody
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
    }



}
