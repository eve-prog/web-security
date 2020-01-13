package ro.sda.websecurity.persistence.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.websecurity.persistence.book.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
