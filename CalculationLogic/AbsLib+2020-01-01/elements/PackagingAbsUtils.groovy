/**
 * Library Function to get PackagingAdjAbs value with parameter of BasePrice
 * @param BasePrice
 * @return PackagingAdjAbs
 */

def getPackagingAbs(BigDecimal BasePrice) {

    // ProductSize from product in Context
    def ProductSize = api.product("Size") ?: null

    // If null
    if (ProductSize == null) {
        api.addWarning("ProductSize for the product is not available")
        return null
    }
    else {
        // Fetching PackagingAdjPct from PackagingAdj Parameters Table using vLookup with product size as key
        def PackagingAdjPct = api.vLookup("PackagingAdj", "PackagingAdj", ProductSize as String)?.find()

        if (PackagingAdjPct == null) {
            api.addWarning("Packaging Adj is not available for the product Size")
            return null
        }

        // AbsoluteValue
        def PackagingAdjAbs =  (BasePrice * (PackagingAdjPct as BigDecimal))

        return PackagingAdjAbs
    }


}