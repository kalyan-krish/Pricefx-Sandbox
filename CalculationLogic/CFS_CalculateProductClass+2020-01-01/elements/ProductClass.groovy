
/*
 Product Class Based on TotalQuantity from Product Classification Parameter Table Data
 Below are the Ranges for Product Classification

Value [value]	Low [lowerBound]	High [upperBound]
A                  30000                99999999

B                  15000                   29999

C                    0                     14999
 */

//Filters
def filters = [
        Filter.greaterOrEqual("upperBound",out.GetTotalQuantity),
        Filter.lessOrEqual("lowerBound",out.GetTotalQuantity)
]

// ProductClass from ProductClassification Table
def productClass = api.findLookupTableValues("ProductClassification",null,
                                     *filters)?.find()?.value

if (productClass == null) {
    api.addWarning("Product Classification with given Quantity is not available or " +
            "ProductClassification Table is not Valid for the TargetDate set")
    return null
}


return productClass