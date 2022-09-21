package megamart.functional

import megamart.*

import megamart.imperative.updateShippingIcons
import megamart.imperative.updateTaxDom
import megamart.setCartTotalDom

// Action
fun calcCartTotal() {
    shoppingCartTotal = calcTotal(shoppingCart)                 // Reading global is an action
    setCartTotalDom()                                           // setCartTotalDom is an action
    updateShippingIcons()                                       // updateShippingIcons is an action
    updateTaxDom()                                              // updateTaxDom is an action
}

// Calculation: only explicit inputs and outputs are used
// We can now use this calculation for any purpose; not just in calcCartTotal, which does some additional work
fun calcTotal(cart: List<CartItem>): Double {
    var total = 0.0
    for ( item in cart ) {
        total += item.price
    }
    return total
}

// Action
fun addItemToCart(name: String, price: Double) {
    addItem(shoppingCart, name, price)                      // Reading global is an action
    calcCartTotal()                                         // calcCartTotal is an action
}

// Calculation: only explicit inputs and outputs
// This is much easier to test than addItemToCart
fun addItem(cart: List<CartItem>, name: String, price: Double) = cart + CartItem(name, price)

// Action
fun updateTaxDom() {
    setTaxDom(calcTax(shoppingCartTotal ))                  // Modifying DOM is an action
}

// Calculation: only explicit inputs and outputs
// This now encodes a business rule that can be used at any time, not just in updateTaxDom
fun calcTax(total: Double) = total * 0.13

// Action
fun updateShippingIcons() {
    val buyButtons = getBuyButtonsDom()                     // Reading from the DOM is an action

    for (button in buyButtons) {
        // There is a business rule embedded here (carts >= 20 get discounts)
        // This code also requires the shoppingCartTotal global to be initialized before testing
        if ( getsFreeShipping(addItem(shoppingCart, button.item.name, button.item.price)) ) {
            // Can't test this without analyzing the DOM; there is no return statement that gives the answer
            button.showFreeShippingIcon()
        } else {
            button.hideFreeShippingIcon()                   // Modifying the DOM is an action
        }
    }
}

// Calculation: only explicit inputs and outputs
// This now encodes a business rule that can be used at any time, not just in updateShippingIcons
fun getsFreeShipping(cart: List<CartItem>) =  calcTotal(cart) >= 20