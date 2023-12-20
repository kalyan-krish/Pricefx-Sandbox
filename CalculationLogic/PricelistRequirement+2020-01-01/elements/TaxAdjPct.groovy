/**
 * Based on the region of the Customer return tax adjustment value from the Tax Adjustment company parameter.
 */

// Fetching Region of Customer from Input using api.find
def Region = api.find("C",0,1,null,["Region"],
                           Filter.equal("customerId",input.customerId))?.find()?.Region

// If Region is not found
if (Region == null) {
    api.addWarning("Customer is not Selected or Region is not available for the customer")
    return null
}

// TaxAdj from Company parameters Table TaxAdj for the Region of Customer
def taxAdj = api.vLookup("TaxAdj", Region)?.find()

// If TaxAdj is null
if (taxAdj == null) {
    api.addWarning("TaxAdj not available for the Region")
    return null

}

return taxAdj