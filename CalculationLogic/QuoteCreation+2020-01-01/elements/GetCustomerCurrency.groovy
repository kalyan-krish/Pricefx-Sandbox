
// Method to get Currency of the Customer (Quote Header Input)
def getCurrency() {

    def headerInputCustomer = input.Customer // from Quote Header

    // DebugMode to Test with AnyCustomer
    if (api.isDebugMode()) {
        headerInputCustomer = "CD-00003"
    }

    // Filter
    def filters = [
            Filter.equal("customerId",headerInputCustomer)
    ]

    // Fetching Currency of Customer using api.find
    def currency = api.find('C',0,1,null,["CustomerCurrency"],*filters)?.find()?.CustomerCurrency
    // If Currency not found
    if (currency == null) {
        api.addWarning("Currency is not found for Customer ID input")
        return null
    }

    return currency
}