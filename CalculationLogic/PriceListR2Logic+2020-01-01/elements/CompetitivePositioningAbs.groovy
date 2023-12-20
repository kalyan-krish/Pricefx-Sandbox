/**
 * Calling CompetitivePositioningAbs Library Logic by passing BasePrice
 * @return CompetitivePositioningAbs value
 */

if (out.BasePrice != null) {

    return libs.AbsLib.CompetitivePositioningAbsUtils.getCompetitivePositioningAbs(out.BasePrice as BigDecimal)
}