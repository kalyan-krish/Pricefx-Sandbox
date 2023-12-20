/** Calculation of Sales Discount
  Sales Discount = List Price or Override Price * Sales Discount %
 */
// Checking null in ListPrice or SalesDiscount %
if (null in [out.ListPrice,out.SalesDiscountPct]) {
    api.addWarning("Unable to calculate Sales Discount missing in parameters")
    return null
}

else {
    return (out.ListPrice * out.SalesDiscountPct) // SalesDiscount
}