/**
 * Calculate a High and Low deviation acceptable for the line item. Example: if the calculated average price is $20 and allowable deviation is 10%, the High & Low deviations would be $22 and $18 respectively.

 Review Acceptable Criteria section.

 Consider increasing allowable deviation during testing to get to a point that a SKU is not always placed into workflow.
 */

//api.trace("quote",quote)

def PriceDeviationPercent = quote?.inputs?.find() { it ->
    it.name == "PriceDeviation"
}?.value

//api.trace(PriceDeviationPercent)


def lineItems = quote?.lineItems?.findAll() {
    !it.folder
}

def CFOApprovalRequired = false
def PricingManagerApprovalRequired = false
def ProductManagerApprovalRequired = false
def reasonCFO =[]
def reasonPrice = []
def reasonProd= []

//api.trace("LineItems",lineItems)

for (lineItem in lineItems) {

    def AvgPrice = lineItem?.outputs?.find() { row ->

        row.resultName == "AverageProductPrice"

    }?.result

    def lineItemListPrice = lineItem?.outputs?.find() { row ->
        row.resultName == "ListPrice"
    }?.result

    if (AvgPrice == 0) {
        api.addWarning("AvgPrice is zero : No quotes & No data in DataMart fall under with Given TimeFrame Range")
        return null
    }

    BigDecimal skuDeviation = AvgPrice * (PriceDeviationPercent)

    BigDecimal highDeviation = AvgPrice + skuDeviation // Max price on high end that is allowable
    BigDecimal lowDeviation = AvgPrice - skuDeviation // Lowest price on low end that is allowable

    // Assume lineItemPrice is the actual List Price collected from the quote for the current SKU


    BigDecimal lineDeviationPercent = 1 - (AvgPrice/lineItemListPrice).abs()

    if (lineItemListPrice >= lowDeviation && lineItemListPrice <= highDeviation) {
        api.trace("LineItemListPrice is with in Limits No Deviation is found")
    }
    else {
        api.trace("LineDeviationPercent", lineDeviationPercent)
        api.trace("AvgPrice", AvgPrice)
        api.trace("SkuDeviation", skuDeviation)
        api.trace("highDeviation", highDeviation)
        api.trace("LowDeviation", lowDeviation)
        api.trace("lineItemListPrice", lineItemListPrice)
        reason = "Deviation Error - SKU ${lineItem.sku} : ${lineItemListPrice} is not between ${lowDeviation} and ${highDeviation}" +
                  "  and line Deviation Percent is ${lineDeviationPercent}"

        //api.logInfo(reason)
        if (lineDeviationPercent >= 0.20) {
            reasonCFO.add(reason)
            CFOApprovalRequired = true
        }
        else if (lineDeviationPercent >= 0.05 && lineDeviationPercent <= 0.20) {
            reasonPrice.add(reason)
            PricingManagerApprovalRequired = true
        }
        else if (lineDeviationPercent <= 0.05) {
            reasonProd.add(reason)
            ProductManagerApprovalRequired = true
        }
    }
}

if (CFOApprovalRequired == true) {
    workflow.addApprovalStep("CFO")
            .withUserGroupApprovers("CFO")
            .withReasons("Need CFO Approval + ${reasonCFO}")
            .withMinApprovalsNeeded(2)
}

else if (PricingManagerApprovalRequired == true) {
    workflow.addApprovalStep("Price Manager")
            .withUserGroupApprovers("PriceManager")
            .withReasons("Need Price Manager Approval + ${reasonPrice}")
            .withMinApprovalsNeeded(2)
}

else if (ProductManagerApprovalRequired == true) {
    workflow.addApprovalStep("Product Manager")
            .withUserGroupApprovers("ProductManager")
            .withReasons("Need Product Manager Approval + ${reasonProd}")
            .withMinApprovalsNeeded(2)
}








