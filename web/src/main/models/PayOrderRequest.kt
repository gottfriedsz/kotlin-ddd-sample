package models

import java.util.*

data class PayOrderRequest(
        var cardName: String = "",
        var cardNumber: String = "",
        var expirationDate: Date? = null,
        var verificationCode: String = "")