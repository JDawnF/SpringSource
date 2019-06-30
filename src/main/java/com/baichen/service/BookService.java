package com.baichen.service;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.baichen.dao.BookDao;

@Service
public class BookService {

	@Qualifier("bookDao")	// 指定装配的组件id
	//@Autowired(required=false)
	//@Resource(name="bookDao2")
//	@Inject
	@Autowired
	private BookDao bookDao;
	
	public void print(){
		System.out.println(bookDao);
	}

	@Override
	public String toString() {
		return "BookService [bookDao=" + bookDao + "]";
	}

}
