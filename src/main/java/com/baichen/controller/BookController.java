package com.baichen.controller;

import com.baichen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Program: BookController
 * @Author: baichen
 * @Description: controller
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
