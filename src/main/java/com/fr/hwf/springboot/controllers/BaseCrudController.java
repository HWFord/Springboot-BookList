package com.fr.hwf.springboot.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fr.hwf.springboot.entites.BaseEntity;

public abstract class BaseCrudController<T extends BaseEntity, DTO> {

	protected static final String FLASH_ERRORS = "errors";
	protected static final String INDEX_ROUTE = "/index";
	protected static final String CREATE_ROUTE = "/create";
	protected static final String DETAILS_ROUTE = "/details/{id}";

	protected static final String DETAILS_TEMPLATE = "/details";


	protected final String TEMPLATE_NAME;
	protected final String REDIRECT_INDEX;
	protected final String REDIRECT_CREATE;

	public BaseCrudController(String templateName) {
		this.TEMPLATE_NAME = templateName;
		this.REDIRECT_INDEX= "redirect:" + "/" + this.TEMPLATE_NAME + INDEX_ROUTE;
		this.REDIRECT_CREATE= "redirect:" + "/" + this.TEMPLATE_NAME + CREATE_ROUTE;
	}

	@Autowired
	private JpaRepository<T, Long> repository;

	@GetMapping(value = {"","/",INDEX_ROUTE})
	public String index(final Model model, final HttpServletResponse response) {

		model.addAttribute("items", repository.findAll());

		return "/" + this.TEMPLATE_NAME + INDEX_ROUTE;
	}

	@GetMapping(value = {CREATE_ROUTE})
	public String createGet(final Model model, final RedirectAttributes attributes, final HttpServletRequest request) {

		if(attributes.getFlashAttributes().containsKey(FLASH_ERRORS)) {
			model.addAttribute(FLASH_ERRORS, attributes.getFlashAttributes().get(FLASH_ERRORS));
		}

		this.preCreateGet(model);

		return  "/" + this.TEMPLATE_NAME + CREATE_ROUTE;
	}

    protected void preCreateGet(final Model model) {

    }

    @PostMapping(value = {CREATE_ROUTE})
    public String createPost(final DTO dto, final RedirectAttributes attributes) {
        String result = this.REDIRECT_INDEX;

        try {
            T item = this.preCreatePost(dto);
            this.repository.save(item);
        } catch (Exception e) {
            attributes.addFlashAttribute(FLASH_ERRORS, e.getMessage());
            result = this.REDIRECT_CREATE;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
	protected T preCreatePost(DTO dto) throws Exception {
        return (T)dto;
    }

    @GetMapping(value = {DETAILS_ROUTE})
    public String details(@PathVariable final Long id, final Model model) {
        String result = this.REDIRECT_INDEX;

        T item = this.repository.findById(id).orElse(null);

        if (item != null) {
            model.addAttribute("item", item);
            result = "/" + this.TEMPLATE_NAME + DETAILS_TEMPLATE;
        }

        return result;
    }

	public String index(Model model, HttpServletResponse response, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
