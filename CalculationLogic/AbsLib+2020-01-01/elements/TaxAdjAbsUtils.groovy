/**
 * Based on the region of the Customer return tax adjustment value from the Tax Adjustment company parameter.
 * @param BasePrice
 * @return TaxAdjAbs
 */

def getTaxAdjAbs(BigDecimal BasePrice) {

    // Fetching Region of Customer from Input using api.find
    def Region = api.find("C", 0, 1, null, ["Region"],
            Filter.equal("customerId", input.customerId))?.find()?.Region

    // If Region is not found
    if (Region == null) {
        api.addWarning("Region is not available for the customer")
        return null
    }

    // Finding table Id of TaxAdj
    def table = api.findLookupTable("TaxAdj") ?: null
    // If table is not found
    if (table == null) {
        api.addWarning("TaxAdj table is not found")
        return null
    }

    //filters with Table Id and Region
    def filters = [
            Filter.equal("lookupTable.id", table.id),
            Filter.equal("name", Region)
    ]
    // TaxAdj % using api.find with above filters
    def taxAdjPct = api.find('LTV', 0, 1, null, ["value"], *filters)?.find()?.value
    // if taxAdj % is null
    if (taxAdjPct == null) {
        api.addWarning("TaxAdj not available for the Region")
        return null

    }

    // Return TaxAdjAbs value
    return (BasePrice * (taxAdjPct as BigDecimal))


}