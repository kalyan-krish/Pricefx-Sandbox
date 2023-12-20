
// Creating Customer Input in Input Generation Mode
if (api.isInputGenerationExecution()) {

    // CustomerId Input
    api.inputBuilderFactory().createCustomerEntry("customerId").getInput()

    def customerParam = api.getParameter("customerId")

    customerParam.setLabel("Customer ID") // Label
    customerParam.setRequired(true) // Mandatory field


}