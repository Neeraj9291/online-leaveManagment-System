package Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IssuedBook {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String bookTitle;
	    private String studentName;
	    private String studentId;
	    private LocalDate issueDate;

	    // Constructors
	    public IssuedBook() {}

	    public IssuedBook(String bookTitle, String studentName, String studentId, LocalDate issueDate) {
	        this.bookTitle = bookTitle;
	        this.studentName = studentName;
	        this.studentId = studentId;
	        this.issueDate = issueDate;
	    }

	    // Getters and Setters (you can generate them from your IDE)
	}

