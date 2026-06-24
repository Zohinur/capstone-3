package org.yearup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories()
    {
       return categoryRepository.findAll();
        // get all categories>
    }

    public Category getById(int categoryId)
    {
       return categoryRepository.findById(categoryId)
               .orElse(null);

    }

    public Category create(Category category)
    {
        // create a new category
        return categoryRepository.save(category);
    }

    public Category update(int categoryId, Category category)
    {
        // update category and return the updated category
        Category existCategory = getById(categoryId);
        existCategory.setName(category.getName());
        existCategory.setDescription(category.getDescription());
        return categoryRepository.save(existCategory);
    }

    public void delete(int categoryId)
    {
        categoryRepository.deleteById(categoryId);
    }
}
