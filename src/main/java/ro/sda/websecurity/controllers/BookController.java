package ro.sda.websecurity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ro.sda.websecurity.persistence.book.BookEntity;
import ro.sda.websecurity.service.book.BookService;

import java.util.List;

@RestController
@RequestMapping("book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody BookSaveBody body){
        bookService.save(body.getAuthor(),body.getTitle());
    }

    @GetMapping("all")
    public List<BookEntity> all(){
        logUser();
        return bookService.findAll();
    }

    private void logUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            User authenticatedUser = (User) principal;
            System.out.println("User: " + authenticatedUser.getUsername());
        }else{
            System.out.println("Could not obtain username");
        }
    }
}
