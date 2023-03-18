package kg.edu.alatoo.springWeb;

import kg.edu.alatoo.springWeb.modules.Borrower;
import kg.edu.alatoo.springWeb.repos.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringWebApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

	@Autowired
	private BorrowerRepository borrowerRepository;

	@Override
	public void run(String... args) throws Exception{

		/*
		Borrower borrower2 =  new Borrower(2, "Ellada","ellada@alatoo.edu.kg", 770222112);
		borrowerRepository.save(borrower2);

		Borrower borrower3 =  new Borrower(3, "Aziret","aziret@alatoo.edu.kg", 770222113);
		borrowerRepository.save(borrower3);
		*/

	}

}
