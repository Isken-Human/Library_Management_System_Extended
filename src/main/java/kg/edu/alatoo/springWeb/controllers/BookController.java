package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.modules.Borrower;
import kg.edu.alatoo.springWeb.repos.AuthorRepository;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import kg.edu.alatoo.springWeb.repos.PublisherRepository;
import kg.edu.alatoo.springWeb.services.BookImageService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    private final BookImageService bookService;

    public BookController(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository, BookImageService bookService) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }


    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "book-form";
    }


    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "edit_book";
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
        model.addAttribute("book", books);
        return "search-results";

    }





    @DeleteMapping("/delete/{bookId}")
    @ResponseBody
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
    }



}
