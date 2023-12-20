import org.w3c.dom.ranges.Range

//Fetching ProductId from product Master Table
def productId = api.product("ProductId")?:null

if (productId == null) {
    api.addWarning("Product is not selected")
    return null
}

//Getting DataMart
def dmCtx = api.getDatamartContext()

def dm = dmCtx.getDatamart("Transaction")?:null // Transaction DataMart

if (dm == null) {
    api.addWarning("Transaction dataMart is not available")
    return null
}

// finding Date ago 365 days to TargetDate if option selected is Last365daysAgo
def targetDate = api.targetDate() as Date
def calender = api.calendar()
calender.time = targetDate
calender.add(Calendar.DAY_OF_YEAR,-365)
def date365DaysAgo = calender.time.format("yyyy-MM-dd")
def targetDateFormat = api.targetDate()?.format("yyyy-MM-dd")

// finding previous year of TargetDate if option selected in LastYear
def currentYear = api.targetDate()?.format('yyyy') as BigInteger
def previousYear = currentYear - 1

def filters = []

// Block if selected DateRange is Last365days
if (out.DateRange == "Last365days") {
    filters = [
            Filter.lessOrEqual("InvoiceDate",targetDateFormat), // less than equal to target date
            Filter.greaterOrEqual("InvoiceDate",date365DaysAgo), // greater than equal to date365daysAgo
            Filter.equal("ProductId",productId) // equal to productId
    ]
}

// Block if selected DateRange is Last Year
else if (out.DateRange == "LastYear") {
    filters = [
            Filter.equal("InvoiceDateYear",previousYear), // equal to previous year
            Filter.equal("ProductId",productId) // equal to productId
    ]
}

def dataQuery = dmCtx.newQuery(dm,true) // New DataQuery with rollup to get sum of quantity sold

//dataQuery.select("InvoiceDate")

dataQuery.select("Quantity","Quantity") //select Quantity

dataQuery.where(*filters) // adding filters to query

//dataQuery.orderBy("InvoiceDate")

def result = dmCtx.executeQuery(dataQuery) //Executing Query

BigDecimal TotalQuantity = result?.getData()?.find()?.Quantity // Total Quantity

return TotalQuantity?:0