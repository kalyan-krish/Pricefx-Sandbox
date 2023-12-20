
//Declaring Required Inputs in Input Generation Mode

if (api.isInputGenerationExecution()) {

    // Quantity

    api.inputBuilderFactory().createIntegerUserEntry("Quantity").getInput()
    def QuantityParam = api.getParameter("Quantity")
    QuantityParam.setLabel("Required Quantity")
    QuantityParam.setRequired(false)
    //Limit values > 0 (positive)
    QuantityParam.setConfigParameter("from",1)


    // OverRide Price of ListPrice if given any Input value

    api.inputBuilderFactory().createUserEntry("OverRidePrice").getInput()
    def OverRidePriceParam = api.getParameter("OverRidePrice")
    OverRidePriceParam.setRequired(false) // optional entry
    OverRidePriceParam.setConfigParameter("inputType","float") //decimal
    OverRidePriceParam.setConfigParameter("from",0.0) // Limit values > 0


}