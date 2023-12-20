/**
 * Calculating InvoicePrice based on Below Formula
 * InvoicePrice = (ListPrice * CompetitiveStrategy) - SalesDiscount
 * @ InvoicePrice
 */


// If any of value in List is null Raising Warnings
if (null in [out.ListPrice,out.SalesDiscount,out.CompetitiveStrategy]) {
    api.addWarning("Unable to calculate Invoice Price missing in parameters")
    return null
}

else {
    // InvoicePrice
    def InvoicePrice = (out.ListPrice * out.CompetitiveStrategy) - (out.SalesDiscount)

    // Rounding InvoicePrice = 3 decimals using Library Logic
    def RoundedInvoicePrice = libs.SharedLib.RoundingUtils.round(InvoicePrice as BigDecimal,3)

    return RoundedInvoicePrice

}

