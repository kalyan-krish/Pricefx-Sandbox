/**
 * Calling TaxAdjAbs Library Logic Function by passing BasePrice
 * @return TaxAdjAbs
 */

if (out.BasePrice != null) {

    return libs.AbsLib.TaxAdjAbsUtils.getTaxAdjAbs(out.BasePrice as BigDecimal)
}