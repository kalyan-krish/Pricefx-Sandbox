/**
 * Calculating Sales Discount from SalesDiscount % and ListPrice
 * @return SalesDiscount
 */

// If any of value ListPrice = null or SalesDiscount % is 0 Raising Warnings
if (out.ListPrice == null || out.SalesDiscountPct == 0) {
    api.addWarning("Unable to calculate Sales Discount missing in parameters")
    return null
}

else {
    return (out.ListPrice * (out.SalesDiscountPct)) // Sales Discount
}