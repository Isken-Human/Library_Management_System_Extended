package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller + ResponseBody = RestController
@Controller
public class MainController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping({"/","/index"})
    public String main(Model model) {

        model.addAttribute("word", "Hello World!");
        model.addAttribute("books", bookRepository.findAll());

        return "main";
    }

    @RequestMapping(value = "/books/{bookId}/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> serveImage(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null); // Find the book by ID
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] image = book.getImage();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    @ResponseBody
    public Book getByIsbn(@PathVariable(name = "isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

}
