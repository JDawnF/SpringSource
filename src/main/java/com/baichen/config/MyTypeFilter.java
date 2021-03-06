package com.baichen.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * ComponentScan注解过滤时，自定义过滤规则
 */
public class MyTypeFilter implements TypeFilter {

    /**
     * metadataReader：读取到的当前正在扫描的类的信息
     * metadataReaderFactory:可以获取到其他任何类信息的
     */
//	@Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
            throws IOException {
        //获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类资源（如类的路径）
        Resource resource = metadataReader.getResource();
        //获取类名
        String className = classMetadata.getClassName();
        System.out.println("className--->" + className);
        // 指定一个规则，只扫描类名包含er的bean
        if (className.contains("er")) {
            return true;        // 匹配成功
        }
        return false;
    }

}
