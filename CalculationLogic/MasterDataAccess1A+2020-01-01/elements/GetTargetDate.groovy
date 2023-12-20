/**
 Fill out Target Date in Groovy Console with any Date or keep it empty to use today.
 Return this date in correct format for the next task using api.targetDate() and formatting.

 Common Date formats: "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss"
 */

//TargetDate with format "yyyy-MM-dd"
def targetTimestamp = api.targetDate()?.format("yyyy-MM-dd")

return targetTimestamp