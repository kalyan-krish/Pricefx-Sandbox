/**
 *  Declaring Header Inputs for UpdateQuote and Workflow
 *  SalesDiscount %
 *  PriceDeviation %
 *  TimeFrame
 */

if (quoteProcessor.isPrePhase()) {

    // Sales Discount %
    def SalesDiscount = api.inputBuilderFactory().createUserEntry("SalesDiscountPct")
                                                                    .setLabel("Sales Discount %")
                                                                    .setRequired(false)
                                                                    .buildMap()

    // Price Deviation %

    def PriceDeviation = api.inputBuilderFactory().createUserEntry("PriceDeviation")
                                                                    .setRequired(false)
                                                                    .setLabel("Price Deviation %")
                                                                    .buildMap()


    // TimeFrame (No Of Months)

    def TimeFrame = api.inputBuilderFactory().createIntegerUserEntry("TimeFrame")
                                                                    .setRequired(true)
                                                                    .setLabel("TimeFrame(No of Months)")
                                                                    .buildMap()

    //adding inputs declared to Quote header
    quoteProcessor.addOrUpdateInput(SalesDiscount)
    quoteProcessor.addOrUpdateInput(PriceDeviation)
    quoteProcessor.addOrUpdateInput(TimeFrame)


}