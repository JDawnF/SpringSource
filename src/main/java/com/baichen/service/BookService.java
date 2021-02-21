package com.baichen.service;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.baichen.dao.BookDao;

@Service
public class BookService {

	@Qualifier("bookDao")	// 指定装配的组件id,这里指定装配组件id为bookDao的bean
	//@Autowired(required=false)   // 是否必须装配这个bean，为false时则找不到这个bean时不会报错
	//@Resource(name="bookDao2")   //  默认是按照组件名称进行装配的,可以通过name指定要装配的bean
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
