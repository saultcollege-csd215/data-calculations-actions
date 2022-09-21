package coupondog

enum class CouponRank { GOOD, BEST, BAD }

data class Subscriber(
    val email: String,
    val referrals: Int
)

data class Coupon(
    val code: String,
    val rank: CouponRank
)

data class Email(
    val from: String,
    val to: String,
    val subject: String,
    val body: String
)