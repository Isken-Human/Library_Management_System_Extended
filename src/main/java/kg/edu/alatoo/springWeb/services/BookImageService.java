package kg.edu.alatoo.springWeb.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import kg.edu.alatoo.springWeb.controllers.MainController;
import kg.edu.alatoo.springWeb.dto.BookDTO;
import kg.edu.alatoo.springWeb.modules.Author;
import kg.edu.alatoo.springWeb.modules.Publisher;
import kg.edu.alatoo.springWeb.repos.BookDTORepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.BookRepository;

@Service
public class BookImageService {
    @Autowired
    private BookRepository bookRepo;
    private static ModelMapper mapper = new ModelMapper();

    @Autowired
    private BookDTORepository bookDTORepository;


    public void getImage(Long bookId) {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }

        byte[] image = bookRepo.findImageById(bookId);


    }

    public void saveBookToDB(MultipartFile file, String title, String isbn, Set<Author> authors, Publisher publisher, int publishedYear) {
        Book book = new Book();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("Not a valid file");
        }
        try {
            book.setImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthors(authors);
        book.setPublisher(publisher);
        book.setPublishedYear(publishedYear);
        bookRepo.save(book);

        BookDTO bookDTO = new BookDTO(book.getId(), book.getTitle(),book.getIsbn(),book.getPublishedYear(), book.getAuthors(),book.getPublisher());
        bookDTORepository.save(bookDTO);

    }

    public List<BookDTO> foundRes() {

        List<BookDTO> results = new ArrayList<>();
        for (Book book :
                bookRepo.findAll()) {
            results.add(mapper.map(book, BookDTO.class));
        }

        return results;
    }


}