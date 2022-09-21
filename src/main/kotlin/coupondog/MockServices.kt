package coupondog

// In a real app these would be calls to a database or service to perform the actions
// For simplicity, we just fake the actions

fun fetchSubscribersFromDB(): List<Subscriber> {
    return listOf(
        Subscriber("john@coldmail.com", 2),
        Subscriber("sam@pmail.co", 16),
        Subscriber("john@coldmail.com", 1),
        Subscriber("john@coldmail.com", 0),
        Subscriber("john@coldmail.com", 25),
        Subscriber("john@coldmail.com", 0),
    )
}

fun fetchCouponsFromDB(): List<Coupon> {
    return listOf(
        Coupon("MAYDISCOUNT", CouponRank.GOOD),
        Coupon("10PERCENT", CouponRank.BAD),
        Coupon("PROMOTION45", CouponRank.BEST),
        Coupon("IHEARTYOU", CouponRank.BAD),
        Coupon("GETADEAL", CouponRank.BEST),
        Coupon("ILIKEDISCOUNTS", CouponRank.GOOD)
    )
}

fun sendEmails(emails: List<Email>) {
    for ( email in emails ) {
        println("Sending email...")
        println(email)
        println()
    }
}