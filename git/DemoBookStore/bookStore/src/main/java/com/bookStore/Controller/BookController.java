package com.bookStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ use Spring's Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookStore.Entity.Book;
import com.bookStore.Repository.BookRepository;

import Model.IssuedBook;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book()); // Required for form binding
        return "newBook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/availeble_book";
    }

    @GetMapping("/availeble_book")
    public String availebleBook(Model model) {
        model.addAttribute("bookList", bookRepository.findAll());
        return "availeble_book";
    }
    
    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "newBook";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/availeble_book";
    }
    @GetMapping("/issuebook")
    public String showIssueForm(Model model) {
        model.addAttribute("issuedBook", new IssuedBook());
        return "issue_book";
    }
}
