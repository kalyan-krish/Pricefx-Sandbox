/**
 * Based on the Business Unit of the product,
 * return the Competitive positioning value from CompetitionStrategy company parameter
 * @param BasePrice
 * @return CompetitivePositioningAbs
 */

def getCompetitivePositioningAbs(BigDecimal BasePrice) {

    // Fetching BusinessUnit from product Master data for the product in context
    def BusinessUnit = api.product("BusinessUnit") ?: null

    // If BusinessUnit is null
    if (BusinessUnit == null) {
        api.addWarning("Business Unit is not available for the product")
        return null
    } else {
        // Fetching CompetitivePositioning % from Competition Strategy Parameter Table for the BusinessUnit of Product
        def CompetitivePositioningPct = api.vLookup("CompetitionStrategy", "CompetitivePositioning", BusinessUnit as String)?.find()
        if (CompetitivePositioningPct == null) {
            api.addWarning("Competitive Positioning is not available for the Business Unit")
            return null

        }

        // Retuning AbsoluteValue
        return (BasePrice * (CompetitivePositioningPct as BigDecimal))
    }

}