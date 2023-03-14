package sg.edu.nus.iss.HttpSession.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import java.io.Serializable;

import jakarta.validation.constraints.Digits;

public class Item implements Serializable {

    @NotNull(message="Item name cannot be empty or null")
    @Size(min=3, message="Item name must not be less than 3 characters")
    private String name;
    
    @Min(value=1, message="Min quantity is 1")
    @Digits(integer=5, fraction=2, message="Quantity is 5 digits and 2 decimal points")
    private Integer quantity; // The @Digit annotation has two attributes, integer and fraction, for specifying the number of allowed digits in the integral part and fraction part of the number.

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
