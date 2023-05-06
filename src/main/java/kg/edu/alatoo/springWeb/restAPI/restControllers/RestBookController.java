package kg.edu.alatoo.springWeb.restAPI.restControllers;


import kg.edu.alatoo.springWeb.restAPI.restModules.*;
import kg.edu.alatoo.springWeb.restAPI.restRepos.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api2/books")
public class RestBookController {

    private final RestBookRepository bookRepository;
    private final RestBookRepository publisherRepository;
    private final RestBookRepository authorRepository;

    public RestBookController(RestBookRepository bookRepository, RestBookRepository publisherRepository, RestBookRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/1234")
    public List<RestBook> allBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new RestBook());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "book-form";
    }


    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        RestBook book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "edit_book";
    }





    @PostMapping("/books/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") RestBook book,
                             Model model) {


        Optional<RestBook> existingBook = bookRepository.findById(id);
        existingBook.get().setId(id);
        existingBook.get().setTitle(book.getTitle());
        existingBook.get().setIsbn(book.getIsbn());
        existingBook.get().setPublishedYear(book.getPublishedYear());
        existingBook.get().setPublisher(book.getPublisher());
        existingBook.get().setAuthors(book.getAuthors());

        bookRepository.save(existingBook.get());
        return "redirect:/";

    }


    @PostMapping("/save")
    public String saveBook(@ModelAttribute RestBook book) {
        bookRepository.save(book);
        return "redirect:/";
    }
//
//    @GetMapping("/search")
//    public String searchBooks(@RequestParam("q") String searchTerm,
//                              @RequestParam("category") String category,
//                              Model model) {
//        List<Book> books = null;
//        if (category.equals("title")) {
//            books = bookRepository.findByTitleContainingIgnoreCase(searchTerm);
//        } else if (category.equals("author")) {
//            books = bookRepository.findByAuthorsNameContainingIgnoreCase(searchTerm);
//        } else if (category.equals("isbn")) {
//            books = bookRepository.findByIsbnContainingIgnoreCase(searchTerm);
//        }
//        model.addAttribute("book", books);
//        return "search-results";
//
//    }


    @DeleteMapping("/delete/{bookId}")
    @ResponseBody
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
    }


}
