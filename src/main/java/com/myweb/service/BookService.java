package com.myweb.service;

import com.myweb.cloudinary.CloudinaryService;
import com.myweb.dto.BookDTO;
import com.myweb.entity.Book;
import com.myweb.entity.Image;
import com.myweb.mapper.BookMapper;
import com.myweb.repository.BookRepository;
import com.myweb.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class BookService {
    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public BookMapper bookMapper;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(String bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("Can not find book with id: "+bookId));
    }

    public Book createBook(BookDTO bookDTO, List<MultipartFile> multipartFiles) throws IOException {
        Book book = bookMapper.toBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        List<Image> imageList = new ArrayList<>();

        //tai anh len cloudinary va luu vao csdl
        for (MultipartFile file : multipartFiles) {
            String fileName = UUID.randomUUID().toString();
            String url = cloudinaryService.uploadImage(file, fileName);

            Image image = new Image();
            image.setUrl(url);
            image.setBook(savedBook);
            imageRepository.save(image);

            imageList.add(image);

        }
        savedBook.setImages(imageList);

        return savedBook;
    }
    public Book updateBook(String bookId, BookDTO bookDTO){
        Book book = getBookById(bookId);

        bookMapper.updateBook(bookDTO, book);

        return bookRepository.save(book);
    }

    public void deleteBook(String bookId){
        if(bookRepository.findById(bookId).isPresent()){
            bookRepository.deleteById(bookId);
        }
        else{
            throw new RuntimeException("Can not find book with id: "+ bookId);
        }
    }

    public List<Book> getBooksByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return bookRepository.getBooksByPriceRange(minPrice, maxPrice);
    }

    public List<Book> getBooksSortedBy(String sortBy, String order){
        Sort.Direction direction = order.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        return bookRepository.findAll(Sort.by(direction, sortBy));
    }

}
