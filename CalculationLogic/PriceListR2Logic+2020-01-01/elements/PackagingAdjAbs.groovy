/**
 * Calling Library Logic function to get PackagingAdjAbs value
 * @return PackagingAdjAbs
 */

// if BasePrice is not null
if (out.BasePrice != null) {

    // calling LibraryLogic AbsLib and PackagingAbsUtils function
    BigDecimal PackagingAdjAbs = libs.AbsLib.PackagingAbsUtils.getPackagingAbs(out.BasePrice as BigDecimal)


    return PackagingAdjAbs

}

