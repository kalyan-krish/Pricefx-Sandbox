/**
 * Calculating Absolute value for Competitive Positioning %
 */

if (null in [out.BasePrice,out.CompetitivePositioningPct]){
    api.addWarning("Absolute value of CompetitivePositioning Pct unable to calculate")
    return null
}


def CompetitivePositioningAbs = (out.BasePrice * out.CompetitivePositioningPct) // Absolute value

return CompetitivePositioningAbs

