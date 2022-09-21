package coupondog

fun subscriberCouponRank(sub: Subscriber) =
    if ( sub.referrals >= 10 )
        CouponRank.BEST
    else
        CouponRank.GOOD

fun selectCouponsByRank(coupons: List<Coupon>, rank: CouponRank): List<Coupon> {
    val rankedCoupons = mutableListOf<Coupon>()
    for ( coupon in coupons ) {
        if (coupon.rank == rank) {
            rankedCoupons.add(coupon)
        }
    }
    return rankedCoupons
}

fun couponEmailForSubscriber(
    sub: Subscriber,
    goods: List<Coupon>,
    bests: List<Coupon>
): Email {
    return when(subscriberCouponRank(sub)) {
        CouponRank.BEST -> Email(
            from="newsletter@coupondog.co",
            to=sub.email,
            subject="Your best weekly coupons",
            body="Here are the best coupons: ${bests.joinToString(", ")}")
        CouponRank.GOOD -> Email(
            from="newsletter@coupondog.co",
            to=sub.email,
            subject="Your good weekly coupons",
            body="Here are the good coupons: ${goods.joinToString(", ")}")
        else -> throw Exception("There should never be a subscriber with a coupon rank of BAD, but this one somehow does: $sub")
    }
}

fun couponEmailsForSubscribers(
    subs: List<Subscriber>,
    goods: List<Coupon>,
    bests: List<Coupon>
): List<Email> {
    val emails = mutableListOf<Email>()
    for ( sub in subs ) {
        emails.add(couponEmailForSubscriber(sub, goods, bests))
    }
    return emails
}

fun sendNewsletter() {
    val coupons = fetchCouponsFromDB()
    val goodCoupons = selectCouponsByRank(coupons, CouponRank.GOOD)
    val bestCoupons = selectCouponsByRank(coupons, CouponRank.BEST)
    val subscribers = fetchSubscribersFromDB()
    val emails = couponEmailsForSubscribers(subscribers, goodCoupons, bestCoupons)
    sendEmails(emails)
}

fun main() {
    sendNewsletter()
}