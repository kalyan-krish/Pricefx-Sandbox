
// Declaring Sales Discount Input in InputGeneration Mode
if (api.isInputGenerationExecution()) {

    // Sales Discount %

    api.inputBuilderFactory().createUserEntry("SalesDiscountPct").getInput()

    def salesDiscountParam = api.getParameter("SalesDiscountPct")

    salesDiscountParam.setLabel("Sales Discount %") // Label
    salesDiscountParam.setRequired(false) // optional field

    salesDiscountParam.setConfigParameter("formatType","Percent")
    salesDiscountParam.setConfigParameter("inputType","range")

    //Limit values >=0 and <=1
    salesDiscountParam.setConfigParameter("from",0)
    salesDiscountParam.setConfigParameter("To",1)


}