package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Borrower;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import kg.edu.alatoo.springWeb.repos.BorrowerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
public class BorrowerController {

    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;


    public BorrowerController(BorrowerRepository borrowerRepository, BookRepository bookRepository) {
        this.borrowerRepository = borrowerRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/borrowers")
    public String listBorrower(Model model) {
        List<Borrower> borrower = borrowerRepository.findAll(Sort.by("id").ascending());
        model.addAttribute("borrower", borrower);

        return "borrowers";
    }


    @GetMapping("/borrowers/create")
    public String createBorrower(Model model) {
        Borrower borrower = new Borrower();
        model.addAttribute("borrower", borrower);
        model.addAttribute("book", bookRepository.findAll());
        return "borrower-form";
    }


    @PostMapping("/borrowers")
    public String saveBorrower(@ModelAttribute Borrower borrower) {
        borrowerRepository.save(borrower);
        return "redirect:/borrowers";
    }

    @GetMapping("/borrowers/edit/{id}")
    public String editBorrower(@PathVariable Long id, Model model) {
        model.addAttribute("borrower", borrowerRepository.findById(id));
        model.addAttribute("book", bookRepository.findAll());
        return "edit_borrower";

    }


    @PostMapping("/borrowers/{id}")
    public String updateBorrower(@PathVariable Long id, @ModelAttribute("borrower") Borrower borrower,
                                 Model model) {
        Borrower existingBorrower = borrowerRepository.getById(id);
        existingBorrower.setId(id);
        existingBorrower.setName(borrower.getName());
        existingBorrower.setEmail(borrower.getEmail());
        existingBorrower.setPhone_number(borrower.getPhone_number());
        existingBorrower.setBooks(borrower.getBooks());

        borrowerRepository.save(existingBorrower);
        return "redirect:/borrowers";

    }



    @GetMapping("/borrowers/{id}")
    public String deleteBorrower(@PathVariable Long id) {
        borrowerRepository.deleteById(id);
        return "redirect:/borrowers";
    }


}
