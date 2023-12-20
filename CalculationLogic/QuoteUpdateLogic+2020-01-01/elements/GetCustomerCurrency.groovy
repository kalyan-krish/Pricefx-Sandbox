
/**
 * Method to get Currency of Customer from Header Input and Customer Master Data
 * @return Currency
 */


def getCurrency() {

    def headerInputCustomer = input.Customer // Header Input

    //DebugMode
    if (api.isDebugMode()) {
        headerInputCustomer = "CD-00001"
    }

    //Get Currency from Customer Master data for the Customer
    def currency = api.customer("CustomerCurrency",headerInputCustomer)

    // if Currency not found
    if (currency == null) {
        api.addWarning("Currency is not found for Customer ID input")
        return null
    }

    return currency
}