/**
 * Method to Get AvgPrice from Existing Approved Quotes with in the Timeframe Range
 * @param productId
 * @param targetDate (Quote Effective Date)
 * @param TimeframeRange (Past Date)
 * @return AvgPrice
 */
def getAvgPriceFromQuotes(def productId,def targetDate,def TimeframeRange) {

    // Filters to set TimeFrame Range
    def filters = [
            Filter.lessThan("targetDate",targetDate),
            Filter.greaterOrEqual("targetDate",TimeframeRange),
            Filter.in("workflowStatus",["APPROVED","NO_APPROVAL_REQUIRED"])
    ]
    def ListPrice = 0.0
    def TotalListPrice = 0.0
    //Fetching all Existing quotes with in TimeFrame Range
    def quotes = api.find('Q',0,api.getMaxFindResultsLimit(),null,*filters)
    api.trace(quotes)
    def count = 0
    for (quote in quotes) {

        //Inbuilt function to get Inputs and Outputs of Quote LineItems using TypedId
        def QuoteLineItems = api.getCalculableLineItemCollection(quote?.typedId)?.lineItems

        for (lineItem in QuoteLineItems) {

            //condition that Quote lineItem sku = productId
            if (lineItem.sku == productId) {

                // If LineItem output elements = ListPrice Fetching the result value of ListPrice
                ListPrice = lineItem?.outputs?.find() { line ->
                    line.resultName == "ListPrice"
                }?.result

                if (ListPrice != null) {
                    TotalListPrice += ListPrice  //summing up the ListPrice
                    count += 1  // Total Quotes with have the Product
                }
            }
        }
    }
    // If Count is Zero that means no quote in TimeFrame Range is having with the Product passed
    if (count == 0) {
        api.addWarning("No Quotes are falling under Given TimeFrame Range")
        return 0
    }

    // Else return the AvgPrice
    return (TotalListPrice/count)?:0.0
}
