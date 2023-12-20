/**
 Search by Product Attribute (using Business Unit)

 Retrieve Competitive positioning % percentage value
 */
// BusinessUnit of the product in context
def BusinessUnit = api.product("BusinessUnit")?:null

//if BusinessUnit is not found
if (BusinessUnit == null) {
    api.addWarning("Business Unit is not found for the product")
    return null
}

// CompetitivePositioning from Competition Strategy Table for the BusinessUnit
def CompetitivePositioning = api.findLookupTableValues("CompetitionStrategy",null,
                                                 Filter.equal("BusinessUnit",BusinessUnit))?.find()?.attribute1

// If value is not found
if (CompetitivePositioning == null) {
    api.addWarning("CompetitivePositioning value is not found for the BusinessUnit")
    return null
}

return CompetitivePositioning


