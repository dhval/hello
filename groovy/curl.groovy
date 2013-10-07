// Create a directory to store data.

def sout = new StringBuffer(), serr = new StringBuffer()
def proc1 = " sh -c \"[ -d 'data' ] && rm -rf ./data/* || mkdir data\"".execute()
proc1.consumeProcessOutput(sout, serr)
proc1.waitForOrKill(1000)
println "out> $sout err> $serr"
println "[ -d 'data' ]] && rm -rf ./data/* || mkdir data"


println System.getProperty("user.dir");

// Start from today.
def today = new Date()
// Determine date value for the next month.
// The TimeCategory makes it possible to use the 1.month syntax.
def nextMonth
use (org.codehaus.groovy.runtime.TimeCategory) {
  nextMonth = today + 1.month
}
// Loop through every day from now to the next month.
for (day in today..nextMonth) {
  println "Starting import for ${day.format('dd MMMM yyyy')}."
  // Define cURL process with correct arguments.
  def proc = "curl -o data/${day.format('yyyy-MM-dd')}.log google.com".execute()
  // cURL uses error output stream for progress output.
  Thread.start { System.err << proc.err } 
  // Wait until cURL process finished and continue with the loop.
  proc.waitFor()
}