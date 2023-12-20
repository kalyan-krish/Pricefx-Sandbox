/**
 * Fetching Competitive Positioning % from Competition Strategy Parameter Table based on BusinessUnit of Product
 * @return CompetitivePositioning %
 */

//BusinessUnit of Product in Context
def BusinessUnit = api.product("BusinessUnit")?:null

// If BU is not found
if (BusinessUnit == null) {
    api.addWarning("Business Unit is not found for the product")
    return null
}

// Fetching CompetitivePositioning % for the BusinessUnit of the Product in Context
def CompetitivePositioning = api.findLookupTableValues("CompetitionStrategy",null,
        Filter.equal("BusinessUnit",BusinessUnit))?.find()?.attribute1

// If value % not found
if (CompetitivePositioning == null) {
    api.addWarning("CompetitivePositioning value is not found for the BusinessUnit")
    return null
}

return CompetitivePositioning


