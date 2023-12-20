/**
 Return Target Date using api.targetDate()

 With format dd.MM.yyyy
 */
// TargetDate in format of dd.MM.yyyy
def targetDate = api.targetDate()?.format("dd.MM.yyyy")

return targetDate