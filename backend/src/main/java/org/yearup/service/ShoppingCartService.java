package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {

        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = new ShoppingCart();

        for (CartItem c: cartItems){
                Product product = productService.getById(c.getProductId());
                if (product == null){
                    throw new RuntimeException("not exist null" + c.getProductId());
                }
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setProduct(product);
                shoppingCartItem.setQuantity(c.getQuantity());
                shoppingCartItem.setDiscountPercent(0);
                shoppingCart.add(shoppingCartItem);
        }
        // load the user's cart rows, look up each product, and build the ShoppingCart
        return shoppingCart;
    }

    // add additional methods here
    public ShoppingCart addProduct(int userID, int productID, int quantity){
        CartItem shoppingCart = shoppingCartRepository.findByUserIdAndProductId(userID, productID);

        if(shoppingCart != null){
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
}
