//package kg.edu.alatoo.springWeb.controllers;
//
//import kg.edu.alatoo.springWeb.dto.BookDTO;
//import kg.edu.alatoo.springWeb.modules.Book;
//import kg.edu.alatoo.springWeb.repos.BookRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Isken {
//    public static void main(String[] args) {
//        Isken isken = new Isken(new BookRepository());
//        // Search for books with "Harry" in the title
//        List<BookDTO> books = isken.findByTitleContainingIgnoreCase("Harry");
//
//        // Print out the titles of the books
//        books.forEach(book -> System.out.println(book.getTitle()));
//    }
//
//    public Isken(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//
//    private final BookRepository bookRepository;
//    private static ModelMapper mapper = new ModelMapper();
//
//    public void searchBooks( String searchTerm,
//                              String category,
//                              Model model) {
//
//        List<BookDTO> books = null;
//        if (category.equals("title")) {
//            books = findByTitleContainingIgnoreCase(searchTerm);
//            System.out.println(books.toString());
//        } else if (category.equals("author")) {
//            books = findByAuthorsNameContainingIgnoreCase(searchTerm);
//            System.out.println(books.toString());
//
//        } else if (category.equals("isbn")) {
//            books = findByIsbnContainingIgnoreCase(searchTerm);
//            System.out.println(books.toString());
//
//        }
//        model.addAttribute("book", books);
//
//
//
//    }
//
//    public List<BookDTO> findByAuthorsNameContainingIgnoreCase(String searchTerm) {
//        return allBookWithoutPhotos().stream()
//                .filter(book -> book.getAuthors().stream().anyMatch(author ->
//                        author.getName().toLowerCase().contains(searchTerm.toLowerCase())))
//                .collect(Collectors.toList());
//    }
//
//    List<BookDTO> findByTitleContainingIgnoreCase(String searchTerm) {
//        List<BookDTO> booksWithTitle = allBookWithoutPhotos().stream()
//                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
//                .toList();
//        return booksWithTitle;
//
//    }
//
//    List<BookDTO> findByIsbnContainingIgnoreCase(String searchTerm) {
//        List<BookDTO> filteredBooks = allBookWithoutPhotos().stream()
//                .filter(book -> book.getIsbn().toLowerCase().contains(searchTerm.toLowerCase()))
//                .sorted(Comparator.comparing(BookDTO::getIsbn))
//                .collect(Collectors.toList());
//
//        return filteredBooks;
//    }
//
//
//    List<BookDTO> allBookWithoutPhotos() {
//        return bookRepository.findAll().stream()
//                .map(book -> mapper.map(book, BookDTO.class))
//                .collect(Collectors.toList());
//    }
//    }
//
