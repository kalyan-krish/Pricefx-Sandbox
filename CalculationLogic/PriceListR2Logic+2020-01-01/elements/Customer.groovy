

// Creating Customer Input in Input Generation Mode
if (api.isInputGenerationExecution()) {

    // CustomerId
    api.inputBuilderFactory().createCustomerEntry("customerId").getInput()

    def customerParam = api.getParameter("customerId")

    customerParam.setLabel("Customer ID")
    customerParam.setRequired(true) // mandatory



}