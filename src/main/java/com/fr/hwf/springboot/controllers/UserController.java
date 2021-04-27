package com.fr.hwf.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.hwf.springboot.dtos.UserDto;
import com.fr.hwf.springboot.entites.Book;
import com.fr.hwf.springboot.entites.Role;
import com.fr.hwf.springboot.entites.User;
import com.fr.hwf.springboot.service.BookService;
import com.fr.hwf.springboot.service.RoleService;


@Controller
@RequestMapping(UserController.BASE_ROUTE)
public class UserController extends BaseCrudController<User, UserDto> {

	public static final String TEMPLATE_NAME = "user";
	public static final String BASE_ROUTE = "user";

	public UserController() {
		super(TEMPLATE_NAME );
	}

	@Autowired
	private RoleService roleService;

	@Autowired
	private BookService bookService;


	@Override
    protected void preCreateGet(final Model model) {

        model.addAttribute("roles", this.roleService.getTemplateList());
        model.addAttribute("books", this.bookService.getTemplateListNoUser());
    }

    @Override
    protected User preCreatePost(UserDto dto) throws Exception {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());

        if (dto.getRoleId() != null) {
            Role role = this.roleService.findRole(dto.getRoleId());
            if (role == null) {
                throw new Exception("Role cannot be found");
            }
            user.setRole(role);
        }

        if (dto.getListOfBookIds() != null) {
        	List<Book> books = this.bookService.findBooks(dto.getListOfBookIds());
        	try {
        		for (Book book : books) {
        			book.setUser(user);
        		}

        	}catch (Exception e) {
        		throw new Exception("Book cannot be found");
        	}
        }
        return user;
    }

}
