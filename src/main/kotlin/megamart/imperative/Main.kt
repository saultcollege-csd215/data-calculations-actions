package megamart.imperative

import megamart.*

fun calcCartTotal() {
    shoppingCartTotal = 0.0
    for ( item in shoppingCart ) {
        shoppingCartTotal += item.price                     // Updating a global variable is an action
    }
    setCartTotalDom()
    updateShippingIcons()
    updateTaxDom()
}

fun addItemToCart(name: String, price: Double) {
    shoppingCart.add(CartItem(name, price))                 // Updating of global variables is an action
    calcCartTotal()
}

fun updateShippingIcons() {
    val buyButtons = getBuyButtonsDom()                     // Reading from the DOM is an action

    for (button in buyButtons) {
        // There is a business rule embedded here (carts >= 20 get discounts)
        // This code also requires the shoppingCartTotal global to be initialized before testing
        if ( button.item.price + shoppingCartTotal >= 20 ) {
            // Can't test this without analyzing the DOM; there is no return statement that gives the answer
            button.showFreeShippingIcon()
        } else {
            button.hideFreeShippingIcon()                   // Modifying the DOM is an action
        }
    }
}

// PROBLEM: The only way to test that this works is to set the global variable
fun updateTaxDom() {
    setTaxDom(shoppingCartTotal * 0.13)                     // Modifying the DOM is an action
}