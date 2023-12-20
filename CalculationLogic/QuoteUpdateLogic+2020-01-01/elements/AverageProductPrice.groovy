/**
 * AverageProductPrice of Product in Context is calculated using below Formula:
 *  AverageProductPrice = (AvgListPriceFromQuotes + AvgListPriceFromDataMart)/2
 *
 *  @return AverageProductPrice
 */

// TimeFrame HeaderInput (No of Months of Historical Data Required)
def TimeFrame = out.TimeFrame
// Quote EffectiveDate or TargetDate
def targetDate = api.targetDate() as Date

def TimeframeRange
//Checking if TimeFrame is 0 months
if (TimeFrame == 0) {
    TimeframeRange = targetDate
}

else {
// To Get the Past TimeFrame count of Months Date from Current Quote EffectiveDate
    def calender = api.calendar()
    calender.time = targetDate
    calender.add(Calendar.MONTH, -TimeFrame)
    TimeframeRange = calender.time.format('yyyy-MM-dd') // PastDate
}

def productId = api.product("ProductId") // productId from Context
def QuoteEffectiveDate = api.targetDate()?.format('yyyy-MM-dd') // TargetDate

//Calling AvgListPrice From DataMart passing productId,TargetDate,PastDate
def AvgListPriceFromDataMart = GetAverageListPrice.getAvgListPrice(productId,QuoteEffectiveDate,TimeframeRange)

//Calling AvgPrice from Quotes passing ProductId,TargetDate,PastDate
def AvgPriceFromQuotes = GetAvgPricefromQuotes.getAvgPriceFromQuotes(productId,QuoteEffectiveDate,TimeframeRange)

//FinalAverageProductPrice using the above Formula
def finalAverageProductPrice = (AvgListPriceFromDataMart + AvgPriceFromQuotes) / 2

//api.trace(AvgPriceFromQuotes)
//api.trace(AvgListPriceFromDataMart)

//RoundedAverageProductPrice to 2 Decimals using Library Logic
def RoundedAverageProductPrice = libs.SharedLib.RoundingUtils.round(finalAverageProductPrice,2)

return RoundedAverageProductPrice








