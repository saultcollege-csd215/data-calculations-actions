package megamart

var shoppingCart = mutableListOf<CartItem>()                // Updating of global variables is an action
var shoppingCartTotal = 0.0;

data class CartItem(val name: String, val price: Double)

class Button(val item: CartItem) {

    fun showFreeShippingIcon() { println("Showing free shipping icon on $item") }
    fun hideFreeShippingIcon() { println("Hiding free shipping icon on $item") }
}

fun getBuyButtonsDom() = listOf(
        Button(CartItem("Lip chap", 2.99)),
        Button(CartItem("Gatorade", 4.85)),
        Button(CartItem( "Lighter", 1.49)),
        Button(CartItem("Gummy Bears", 5.99))
    )

fun setCartTotalDom(total: Double) {
    println("Setting the cart total in the DOM to $total...")
}

fun setTaxDom(tax: Double) {
    println("Setting tax to $tax")
}