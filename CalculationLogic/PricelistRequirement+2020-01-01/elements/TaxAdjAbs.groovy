/**
 * Calculating Absolute value for the TaxAdj %
 */

if (null in [out.BasePrice,out.TaxAdjPct]){
    api.addWarning("Absolute value of TaxAdj Pct unable to calculate")
    return null
}


def TaxAdjAbs = (out.BasePrice * out.TaxAdjPct) // Absolute value


return TaxAdjAbs

