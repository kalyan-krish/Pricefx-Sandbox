/**
 * ListPrice Calculation with below Formula:
 * ListPrice = out.BasePrice + out.PackagingAdjAbs + out.CompetitivePositioningAbs + out.TaxAdjAbs + out.SeasonalRateAbs
 * @return ListPrice
 */

if (null in [out.BasePrice,out.PackagingAdjAbs,out.CompetitivePositioningAbs,out.TaxAdjAbs,out.SeasonalRateAbs])
{
    api.addWarning("Unable to calculate ListPrice: missing in parameters")
    return null
}

// listPrice
def listPrice = (out.BasePrice + out.PackagingAdjAbs + out.CompetitivePositioningAbs + out.TaxAdjAbs + out.SeasonalRateAbs)

return listPrice