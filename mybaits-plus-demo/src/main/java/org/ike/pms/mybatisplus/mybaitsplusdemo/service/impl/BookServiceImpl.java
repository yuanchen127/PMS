package org.ike.pms.mybatisplus.mybaitsplusdemo.service.impl;

import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.Book;
import org.ike.pms.mybatisplus.mybaitsplusdemo.dao.BookMapper;
import org.ike.pms.mybatisplus.mybaitsplusdemo.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ike
 * @since 2019-04-11
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
