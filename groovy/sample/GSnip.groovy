package sample

class GSnip {
    // global declare array
    def static array = [15,5,10,5,10,10,15,5,15,10,5]

       static main(args) {

    // iterate array
    for(key in array) { print " ${key} " }

    // Current directory
    println "Current directory is " + System.getProperty("user.dir");

    // Shell Script
    def proc = " sh -c \" curl -s --connect-timeout 2 --max-time 2  --head http://www1.lexmark.com/en_US/\"".execute()
    Thread.start { System.err << proc.err }
    proc.waitFor()
    println "HTTP# ${proc.text}"


    }

}