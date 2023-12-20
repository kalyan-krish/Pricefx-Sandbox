/**
 * Method to Get Average List Price of Product with in TimeFrame Range
 * @param productId
 * @param targetDate
 * @param TimeframeRange
 * @return AvgListPrice
 */

def getAvgListPrice(def productId,def targetDate,def TimeframeRange) {

    // DataMart Context
    def dmCtx = api.getDatamartContext()

    // DataMart
    def dm = dmCtx.getDatamart("Transaction")

    // Filters to select the TimeFrame Range and Product
    def filters = [
            Filter.equal("ProductId", productId), // productId
            Filter.lessOrEqual("InvoiceDate",targetDate), // Current Quote Effective Date
            Filter.greaterOrEqual("InvoiceDate",TimeframeRange), // Past Date
            Filter.notEqual("InvoiceDate",null) // Date is not null
    ]

    def dataQuery = dmCtx.newQuery(dm, false) // Starting NewQuery without Rollup
    dataQuery.select("ListPrice/Quantity", "AvgListPrice")
    dataQuery.select("InvoiceDate","InvoiceDate")
    dataQuery.where(*filters)
    dataQuery.orderBy("InvoiceDate DESC")
    def result = dmCtx.executeQuery(dataQuery)?:null // Execute Query

    def Transactions = 0
    def TotalAvgListPrice = 0.0
    // Closure to iterate through result of Query and Sum up the TotalAvgListPrice and Total Transactions
    result?.getData()?.forEach { row ->
            Transactions += 1
            TotalAvgListPrice += row.AvgListPrice

    }
    // If Transactions == 0 (No Data with in TimeframeRange for the Product)
    if (Transactions == 0) {
        api.addWarning("No Data for Given Timeframe range in the DataMart")
        return 0
    }
    // Else returning the AvgListPrice
    else {
        return (TotalAvgListPrice / Transactions) ?: 0.0
    }
}