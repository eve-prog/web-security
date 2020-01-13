package ro.sda.websecurity.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.websecurity.persistence.book.BookEntity;
import ro.sda.websecurity.persistence.book.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void save(String author, String title){
        BookEntity entity = new BookEntity();
        entity.setAuthor(author);
        entity.setTitle(title);
        bookRepository.save(entity);
    }
    @Transactional(readOnly = true)
    public List<BookEntity>findAll(){
        return bookRepository.findAll();
    }
}
