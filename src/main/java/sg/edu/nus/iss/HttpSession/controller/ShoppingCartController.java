package sg.edu.nus.iss.HttpSession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.HttpSession.model.Cart;
import sg.edu.nus.iss.HttpSession.model.Item;

@Controller
@RequestMapping(path="/cart")
public class ShoppingCartController {
    
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        
        // 1. First thing when rendering the /cart page is to retrieve any items that may still exist in an HttpSession
        Cart c = (Cart) session.getAttribute("cart");
        if (c == null) {
            c = new Cart();
            session.setAttribute("cart", c);
        }

        model.addAttribute("item", new Item());
        model.addAttribute("cart", c);

        return "cart";
    }

    // Session 'memory' persists across multiple tabs within the same browser (even if the tabs are closed), but not across different browsers
    // Refreshing on a browser can cause the form to be posted again
    @PostMapping
    public String postCart(Model model, HttpSession session, @Valid Item item, BindingResult result) {
        Cart c = (Cart) session.getAttribute("cart");
        if (result.hasErrors()) {
            model.addAttribute("item", item);
            model.addAttribute("cart", c);
            return "cart";
        }
        if (c == null) {
            c = new Cart();
            session.setAttribute("cart", c);
        }
        c.addItemToCart(item);
        model.addAttribute("item", item);
        model.addAttribute("cart", c);
        return "cart";
    }

    @GetMapping(path="/checkout")
    public String checkout(Model model, HttpSession session) {
        // session.removeAttribute("cart"); // this simply removes the cart from the session
        session.invalidate(); // this 'destroys' the session
    
        // Cart c = (Cart)session.getAttribute("cart");
        // if(null == c){
        //     c = new Cart();
        //     session.setAttribute("cart", c);
        // }
        model.addAttribute("item", new Item());
        model.addAttribute("cart", new Cart());
        return "cart";
    }
}
