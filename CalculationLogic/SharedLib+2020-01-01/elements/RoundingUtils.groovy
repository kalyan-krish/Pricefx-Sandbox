import java.math.RoundingMode


// Library Function to round the number to (noDecimals) and return
BigDecimal round(BigDecimal number, int noDecimals) {
    if(number == null) {
        return null
    }
    return number.setScale(noDecimals, RoundingMode.HALF_UP)
}