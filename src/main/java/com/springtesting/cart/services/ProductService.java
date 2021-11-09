package com.springtesting.cart.services;

import com.springtesting.cart.models.Cart;
import com.springtesting.cart.models.Product;
import com.springtesting.cart.repository.CartRepository;
import com.springtesting.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(){

    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Integer id, String name, String description){
        Product product = new Product();
        return productRepository.save(product);
    }

    public Product getProduct(Integer id){
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product with id "+id+ " not found!"));
    }

    public Product updateProduct(Integer id, Product product){
        productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id"+ id));

        product.setId(id);

        return productRepository.save(product);

    }


    public void deleteProduct(Integer id){
        productRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Product not found with id"+ id));
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();

        for( Product product:products) {
            product.setTotalCost(product.getQuantity()*product.getPrice());
        }

        return products;
    }

}