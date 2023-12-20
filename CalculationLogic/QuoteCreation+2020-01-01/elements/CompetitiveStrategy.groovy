/**
 * Calculating Competitive Strategy using below formula
    Competition Strategy = List Price or Override Price * Competitive positioning %
 */

// checking for null in ListPrice or CompetitivePositioning
if (null in [out.ListPrice,out.CompetitionPositioning]) {
    api.addWarning("Unable to calculate Competitive Strategy missing in parameters")
    return null
}

else {

    // Competitive Strategy
    BigDecimal CompetitiveStrategy =  out.ListPrice * out.CompetitionPositioning

    return CompetitiveStrategy
}


