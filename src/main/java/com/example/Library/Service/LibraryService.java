package com.example.Library.Service;

import com.example.Library.Model.BookModel;
import com.example.Library.Model.BorrowModel;
import com.example.Library.Model.CustomerModel;
import com.example.Library.Repository.BookRepository;
import com.example.Library.Repository.BorrowRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<?> createBook(BookModel bookModel) {
        if (bookRepository.findByISBN(bookModel.getISBN()) != null) {
            String errorMsg = "ISBN number already exists!";
            return ResponseEntity.badRequest().body(errorMsg);
        }
        BookModel createdBook = bookRepository.save(bookModel);
        return ResponseEntity.ok(createdBook);
    }

    public List<BookModel> createAllBooks(List<BookModel> bookModel) {
        return bookRepository.saveAll(bookModel);
    }

    public BookModel readBook(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<BookModel> readAllBooks() {
        return bookRepository.findAll();
    }

    public BookModel updateBookDetails(int id, BookModel bookModel) {
        BookModel oldData = null;
        oldData = bookRepository.findById(id).get();
        oldData.setAuthor(bookModel.getAuthor());
        oldData.setTitle(bookModel.getTitle());
        oldData.setGenre(bookModel.getGenre());
        return bookRepository.save(oldData);
    }

    public String deleteBookDetails(int ISBN) {
        Integer book = bookRepository.findByISBN(ISBN);
        if (book != null) {
            bookRepository.deleteByISBN(ISBN);
            return "Data Deleted!";
        } else {
            return "No data found for ISBN: " + ISBN;
        }
    }
    @Autowired
    BorrowRepository borrowRepository;

    public ResponseEntity<?> createBorrow(BorrowModel borrowModel) {
        if (bookRepository.findByISBN(borrowModel.getISBN()) == null) {
            String errorMessage = "The book you trying to borrow doesn't exists!";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        BorrowModel createdBorrow = borrowRepository.save(borrowModel);
        return ResponseEntity.ok(createdBorrow);
    }

    public List<BorrowModel> createAllBorrow(List<BorrowModel> borrowModel) {
        return borrowRepository.saveAll(borrowModel);
    }

    public List<BorrowModel> getBorrow1(int customerId) {
        return borrowRepository.borrowGet(customerId);
    }

    public List<BorrowModel> getAllBorrow() {
        return borrowRepository.findAll();
    }

    public List<String> popular() {
        return borrowRepository.popularBook();
    }

    @Transactional
    public String returnBookDate(int customerId, int ISBN) {
        BorrowModel borrowModel = borrowRepository.findByCustomerIdAndISBN(customerId, ISBN);
        if (borrowModel == null) {
            return "BorrowModel not found! for customerId: " + customerId + " and ISBN: " + ISBN;
        }
        if (borrowModel.getReturnDate() != null) {
            return "Book is already returned!";
        }
        borrowRepository.returnDate(customerId, ISBN);
        borrowModel = borrowRepository.findByCustomerIdAndISBN(customerId, ISBN);
        return "Book has been returned Successfully!";
    }

    public List<BorrowModel> overDue() {
        return borrowRepository.overDue();
    }

    public BookModel findByIsbn(int isbn) {
        return bookRepository.findBooksByISBN(isbn);
    }

    public List<BookModel> findByTitle(String Title) {
        return bookRepository.findBooksByTile(Title);
    }

    public List<BookModel> findByGenre(String Genre) {
        return bookRepository.findBooksByGenre(Genre);
    }

    public List<BorrowModel> getCustomerTransaction(Date fromDate, Date toDate, int customerId) {
        return borrowRepository.getCustomerTransaction(fromDate,toDate,customerId);
    }
}
