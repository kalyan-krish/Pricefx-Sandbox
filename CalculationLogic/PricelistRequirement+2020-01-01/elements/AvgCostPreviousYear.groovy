/**
 * Based on the customer calculate the average cost of the product sold to the customer in
 * previous year (from the target date) from the Transactions DataMart.
 */

// Fetching TargetDate and format to Year and Casting to BigInteger
def TargetYear = api.targetDate()?.format("yyyy") as BigInteger

// ProductId for the Product in Context from Products Master data
def ProductId = api.product("ProductId")?:null

// If productId is null
if (ProductId == null) {
    api.addWarning("Product id is not selected in context")
    return null
}

//DataMart Context
def dmtCx = api.getDatamartContext()
def dm = dmtCx.getDatamart("Transaction")?:null // Get DataMart

// If DataMart is not found
if (dm == null) {
    api.addWarning("Transaction DataMart is not available")
    return null
}

else {
    def dataQuery = dmtCx.newQuery(dm, true) // Creating New Query with Rollup (aggregation)
    dataQuery.select("SUM(InvoicePrice)", "TotalInvoicePrice") // TotalInvoicePrice
    dataQuery.select("SUM(Quantity)","TotalQuantity") // Total Quantity

    // Filters
    def filters = [
            Filter.equal("ProductId", ProductId), // productId
            Filter.equal("CustomerId", input.customerId), // CustomerId
            Filter.equal("InvoiceDateYear", TargetYear - 1) // InvoiceDateYear = previousYear from TargetYear
    ]

    dataQuery.where(*filters) // where with above filters

    def result = dmtCx.executeQuery(dataQuery) // Executing Query

    def TotalInvoicePrice = result?.getData()?.find()?.TotalInvoicePrice
    def TotalQuantity = result?.getData()?.find()?.TotalQuantity

    if (TotalQuantity == 0 || TotalQuantity == null) {
        api.addWarning("Unable to calculate AverageCost TotalQuantity is null or Zero (due to Divide by Zero Exception)")
        return null
    }

    def AvgCost = (TotalInvoicePrice / TotalQuantity) //AvgCost sold to Customer

    // Rounding AvgCost to 2 Decimals using Library Logic
    def AvgCostRounded = libs.SharedLib.RoundingUtils.round(AvgCost as BigDecimal,2)

    return AvgCostRounded
}


