/** Calculating ListPrice with below Formula
 * List Price = base price + packaging adjustment + Competitive positioning + tax adjustment
 */


if (null in [out.BasePrice,out.PackagingAdjAbs,out.CompetitivePositioningAbs,out.TaxAdjAbs])
{
    api.addWarning("Unable to calculate ListPrice: missing in parameters")
    return null
}

// Based on the Formula summing up all to get listPrice of the Product for the Customer
def listPrice = (out.BasePrice + out.PackagingAdjAbs + out.CompetitivePositioningAbs + out.TaxAdjAbs)

// Rounding ListPrice to 2 Decimals using Library Logic
def roundedListPrice = libs.SharedLib.RoundingUtils.round(listPrice as BigDecimal,2)


return roundedListPrice