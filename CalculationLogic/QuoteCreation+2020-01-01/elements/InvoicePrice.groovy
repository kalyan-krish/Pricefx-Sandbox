/**
 Invoice price per item shown in both USD and EU using Exchange Rate value

 Invoice Price per item = List Price or Override Price * (Competition Strategy) – (Sales Discount)
 */

//Checking null in any value in List
if (null in [out.ListPrice,out.SalesDiscount,out.CompetitiveStrategy]) {
    api.addWarning("Unable to calculate Invoice Price missing in parameters")
    return null
}

else {
    // InvoicePrice
    def InvoicePrice = (out.ListPrice * out.CompetitiveStrategy) - (out.SalesDiscount)

    // Rounded to 2 Decimals using LibraryLogic
    def RoundedInvoicePrice = libs.SharedLib.RoundingUtils.round(InvoicePrice as BigDecimal,2)

    return RoundedInvoicePrice

}

