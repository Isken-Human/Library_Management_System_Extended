package kg.edu.alatoo.springWeb.restAPI.restControllers;

/*
import kg.edu.alatoo.springWeb.restAPI.restModules.*;
import kg.edu.alatoo.springWeb.restAPI.restRepos.*;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/borrower")
public class RestBorrowerController {

    private final RestBookRepository restborrowerRepository;
    private final RestBookRepository restbookRepository;



    public RestBorrowerController(RestBorrowerRepository restborrowerRepository, RestBookRepository restbookRepository) {
        this.restborrowerRepository = restborrowerRepository;
        this.restbookRepository = restbookRepository;
    }

    @GetMapping("/borrowers")
    public String listBorrower(Model model) {
        List<RestBorrower> borrower = restborrowerRepository.findAll(Sort.by("id").ascending());
        model.addAttribute("borrower", borrower);

        return "borrowers";
    }


    @GetMapping("/borrowers/create")
    public String createBorrower(Model model) {
        Borrower borrower = new Borrower();
        model.addAttribute("borrower", borrower);
        model.addAttribute("book", restbookRepository.findAll());
        return "borrower-form";
    }


    @PostMapping("/borrowers")
    public String saveBorrower(@ModelAttribute Borrower borrower) {
        restborrowerRepository.save(borrower);
        return "redirect:/borrowers";
    }

    @GetMapping("/borrowers/edit/{id}")
    public String editBorrower(@PathVariable Long id, Model model) {
        model.addAttribute("borrower", restborrowerRepository.findById(id));
        model.addAttribute("book", restbookRepository.findAll());
        return "edit_borrower";

    }


    @PostMapping("/borrowers/{id}")
    public String updateBorrower(@PathVariable Long id, @ModelAttribute("borrower") Borrower borrower,
                                 Model model) {
        Borrower existingBorrower = restborrowerRepository.getById(id);
        existingBorrower.setId(id);
        existingBorrower.setName(borrower.getName());
        existingBorrower.setEmail(borrower.getEmail());
        existingBorrower.setPhone_number(borrower.getPhone_number());
        existingBorrower.setBooks(borrower.getBooks());

        restborrowerRepository.save(existingBorrower);
        return "redirect:/borrowers";

    }



    @GetMapping("/borrowers/{id}")
    public String deleteBorrower(@PathVariable Long id) {
        restborrowerRepository.deleteById(id);
        return "redirect:/borrowers";
    }


}
*/