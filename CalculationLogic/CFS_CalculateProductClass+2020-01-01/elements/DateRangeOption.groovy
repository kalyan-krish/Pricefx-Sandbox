
// Declare DateRange Option Entry input
if (api.isInputGenerationExecution()) {

    api.inputBuilderFactory().createOptionEntry("DateRange")
                             .setLabel("Select DateRange")
                             .setOptions(["Last365days","LastYear"]) // options
                             .setLabels(["Last365days":"Last 365 Days","LastYear": "Last Year"])
                             .setRequired(true) // Mandatory to select
                             .getInput()
}