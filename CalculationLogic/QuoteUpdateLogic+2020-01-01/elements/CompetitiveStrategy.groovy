/**
 *  Calculating Competitive Strategy using below Formula:
 *  CompetitivePositioning = ListPrice * Competitive Positioning
 *  @return CompetitiveStrategy
 */

// If any of value ListPrice or CompetitivePositioning is null Raising warnings
if (null in [out.ListPrice,out.CompetitivePositioning]) {
    api.addWarning("Unable to calculate Competitive Strategy missing in parameters")
    return null
}

else {

    // Competitive Strategy
    BigDecimal CompetitiveStrategy =  out.ListPrice * out.CompetitivePositioning

    return CompetitiveStrategy
}


