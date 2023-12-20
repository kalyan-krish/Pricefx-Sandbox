/**
 * Calculating Absolute Value for the PackagingAdj % value
 */

if (null in [out.BasePrice,out.PackagingAdjPct]){
    api.addWarning("Absolute value of PackagingAdj Pct unable to calculate")
    return null
}

def PackagingAdjAbs = (out.BasePrice * out.PackagingAdjPct)

return PackagingAdjAbs


