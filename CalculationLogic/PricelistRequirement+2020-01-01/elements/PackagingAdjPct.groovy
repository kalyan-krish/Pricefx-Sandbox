/**
 * Based on the product size, return the packaging adjustment value from PackagingAdj company parameter
 */


// Fetching ProductSize for the Product in Context using api.product
def ProductSize = api.product("Size")?:null

// If productSize is null raising warning
if (ProductSize == null) {
    api.addWarning("ProductSize for the product is not available")
    return null
}

else {
    // Fetching PackagingAdjPct from PackagingAdj Parameters Table using vLookup with product size as key
    def PackagingAdjPct = api.vLookup("PackagingAdj", "PackagingAdj", ProductSize as String)?.find()
    // Raising warning if null
    if (PackagingAdjPct == null) {
        api.addWarning("Packaging Adj is not available for the product Size")
        return null
    }
    return PackagingAdjPct
}



