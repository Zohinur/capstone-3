package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService {
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId) {

        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = new ShoppingCart();

        for (CartItem c : cartItems) {
            Product product = productService.getById(c.getProductId());
//
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setProduct(product);
            shoppingCartItem.setQuantity(c.getQuantity());
            shoppingCartItem.setDiscountPercent(shoppingCartItem.getDiscountPercent());
            shoppingCartItem.getLineTotal();
            shoppingCart.add(shoppingCartItem);
        }
        // load the user's cart rows, look up each product, and build the ShoppingCart
        return shoppingCart;
    }

    // add additional methods here
    public ShoppingCart addProduct(int userID, int productID, int quantity) {
        CartItem shoppingCart = shoppingCartRepository.findByUserIdAndProductId(userID, productID);

        if (shoppingCart != null) {
            shoppingCart.setQuantity(shoppingCart.getQuantity() + quantity);
            shoppingCartRepository.save(shoppingCart);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userID);
            cartItem.setProductId(productID);
            cartItem.setQuantity(quantity);
            shoppingCartRepository.save(cartItem);
        }

        return getByUserId(userID);

    }

    public ShoppingCart updateProduct(int userId, int productId, int quantity) {
        CartItem cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        if (cartItem != null) {
            if (quantity > 0) {
                cartItem.setQuantity(quantity);
                shoppingCartRepository.save(cartItem);
            } else {
                shoppingCartRepository.delete(cartItem);
            }
        }
        return getByUserId(userId);
    }
}
