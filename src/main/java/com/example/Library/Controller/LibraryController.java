package com.example.Library.Controller;

import com.example.Library.Model.BookModel;
import com.example.Library.Model.BorrowModel;
import com.example.Library.Model.CustomerModel;
import com.example.Library.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")

public class LibraryController {

    @Autowired
    LibraryService libraryService;
    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody BookModel bookModel) {
        return libraryService.createBook(bookModel);
    }

    @PostMapping("/addAllBooks")
    public List<BookModel> addAllBooks(@RequestBody List<BookModel> bookModel) {
        return libraryService.createAllBooks(bookModel);
    }

    @GetMapping("/getBook/{id}")
    public BookModel getBook(@PathVariable int id) {
        return libraryService.readBook(id);
    }

    @GetMapping("/getAllBooks")
    public List<BookModel> getAllBooks() {
        return libraryService.readAllBooks();
    }

    @PutMapping("/updateBook/{id}")
    public BookModel updateBook(@PathVariable int id, @RequestBody BookModel bookModel) {
        return libraryService.updateBookDetails(id, bookModel);
    }

    @DeleteMapping("/delete/{ISBN}")
    public String deleteBook(@PathVariable int ISBN) {
        return libraryService.deleteBookDetails(ISBN);
    }

    @GetMapping("/findByIsbn/{isbn}")
    public BookModel findByIsbn(@PathVariable int isbn){
        return libraryService.findByIsbn(isbn);
    }
    @GetMapping("/findByTitle/{Title}")
    public List<BookModel> findByTitle(@PathVariable String Title){
        return libraryService.findByTitle(Title);
    }
    @GetMapping("/findByGenre/{Genre}")
    public List<BookModel> findByGenre(@PathVariable String Genre){
        return libraryService.findByGenre(Genre);
    }

    @PostMapping("/addBorrow")
    public ResponseEntity<?> addBorrow(@RequestBody BorrowModel borrowModel) {
        return libraryService.createBorrow(borrowModel);
    }

    @PostMapping("/addAllBorrow")
    public List<BorrowModel> addAllBorrow(@RequestBody List<BorrowModel> borrowModel) {
        return libraryService.createAllBorrow(borrowModel);
    }

    @GetMapping("/getBorrow/{customerId}")
    public List<BorrowModel> getBorrow(@PathVariable int customerId) {
        return libraryService.getBorrow1(customerId);
    }

    @GetMapping("/getAllBorrow")
    public List<BorrowModel> getAllBorrow() {
        return libraryService.getAllBorrow();
    }

    @GetMapping("/getPopular")
    public List<String> getPopular() {
        return libraryService.popular();
    }

    @GetMapping("/returnBook/{customerId}/{ISBN}")
    public String returnBook(@PathVariable int customerId, @PathVariable int ISBN) {
        return libraryService.returnBookDate(customerId,ISBN);
    }

    @GetMapping("/getOverDueBooks")
    public List<BorrowModel> getOverDueBooks() {
        return libraryService.overDue();
    }

    @PostMapping("/getCustomerTransaction")
    public ResponseEntity<List<BorrowModel>> getCustomerTransaction(@RequestBody CustomerModel customerModel) {
        List<BorrowModel> transactions = libraryService.getCustomerTransaction(
                customerModel.getFromDate(),
                customerModel.getToDate(),
                customerModel.getCustomerId()
        );
        return ResponseEntity.ok(transactions);
    }
}
