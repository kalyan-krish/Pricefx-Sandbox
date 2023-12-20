/**
 * We need to pre-calculate a total revenue(invoice price) money (in USD) for each combination of
 Country
 Business Unit
 Month
 The pre-aggregated results should be stored in a dataMart PreAggregatedReport each night.

 Creating DataLoad Logic to create PreAggregated Report from Transactions DataMart Data
 */


def Target = api.getDatamartRowSet("target") // Set Target DataMart

def dmcTx = api.getDatamartContext() //Defining DataMart context

def dm = dmcTx.getDatamart("Transaction")?:null // Get DataMart

if (dm == null){
    api.addWarning("DataMart Transaction is not available")
    return null
}

// Fetching Valid ExchangeRate to convert from EUR to USD
def target = api.targetDate()?.format("yyyy-MM-dd")
def filters = [
        Filter.equal("key1","EUR"),
        Filter.equal("key2","USD"),
        Filter.greaterOrEqual("attribute1",target)
]
def ExchangeRate = api.findLookupTableValues("ExchangeRate",null,*filters)?.find()?.attribute2
if (ExchangeRate == null) {
    api.addWarning("Exchange Rate is not found")
}
//api.trace(ExchangeRate)

// Data Query with rollup and group by Country,BusinessUnit,Month
def dataQuery = dmcTx.newQuery(dm,true).select("Country","Country")
        .select("BusinessUnit","BusinessUnit")
        .select("InvoiceDateMonth","Month")

dataQuery.select("InvoicePrice","Revenue") // InvoicePrice as Revenue
def result = dmcTx.executeQuery(dataQuery) // Execute Query
def newRow // defining newRow outside closure
def ParId = 0 // unique Id key

//Iterating each row and loading into Target using Closure
result?.getData()?.forEach { row ->

    if (null in [row.Country,row.BusinessUnit,row.Month]) {
        api.addWarning("Cannot add this row missing in Country or BusinessUnit or Month")
        return null
    }
    // New Row
    newRow = [
            ParId : ParId,
            Country : row.Country,
            BusinessUnit: row.BusinessUnit,
            Month : row.Month,
            Revenue : (row.Revenue * ExchangeRate),
            Currency : "USD",
    ]

    //Adding new Row into Target DataMart
    Target?.addRow(newRow)
    //api.trace(newRow)
    ParId += 1


}








