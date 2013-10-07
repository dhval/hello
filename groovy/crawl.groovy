// Create a directory to store data 'menu_finish.html'.
println "Current directory is " + System.getProperty("user.dir");
" sh -c \"[ -d 'data' ] && rm -rf ./data/* || mkdir data\"".execute().waitFor();

File filePrinter = new File("printer.txt")
File fileModel = new File("model.txt")

// All Models that we have.
def setModel = new HashSet()
fileModel.eachLine() { line -> 
    setModel.add(line.trim())    
}

// k=Model, v=Printer that we tested.
def testModel = [:]

// Printer that we tested and failed.
def failPrinter = []

// Printer that we tested and passed.
def passPrinter = []

// All printer's EWS URL we did not understand.
def unknownEWS = [] 

// All printer's not responnding.
def noResponse = [] 

// URL suffix format
def _url1 = "/cgi-bin/posttest/printer/config/gen/menu_finish.html"    
def _url2 = "/cgi-bin/posttest/cgi-bin/dynamic/config/gen/menu_finish.html"
def _url3 = "/cgi-bin/postpf/printer/config/gen/menu_finish.html"

// line number in printer.txt
def lCount = 0

filePrinter.eachLine() { line -> 
    if (lCount > 5000 ) return true;
    
    lCount++
    def field = line.tokenize(",")
    def ipAddress =  field[0].replaceAll("\"", "").trim();
    def model =  field[1].replaceAll("\"", "").replaceAll(" ", "_").trim();
    def serial =  field[2].replaceAll("\"", "").replaceAll(" ", "_").trim();
    
     // test printer model we haven't so far.
    if (!testModel.containsKey(model)) {
        
        // Checking that printer's httpd return a HTTP header, it's up. !
        def proc = " sh -c \" curl -s --connect-timeout 2 --max-time 2  --head http://${ipAddress}/ | wc -l \"".execute()
        Thread.start { System.err << proc.err } 
        proc.waitFor()
        def status = Integer.parseInt(proc.text.trim())
        
        println "row# ${lCount} | ${ipAddress} | ${model} HTTP# ${status}"

        if (status > 0) {
        
            // URL style 1
            def pURL1 = "http://" + ipAddress + _url1
            def pURL2 = "http://" + ipAddress + _url2
            def pURL3 = "http://" + ipAddress + _url3
            
            // Download with one of the URL
            def wgetCmd = " wget -T 10 --tries=1 -q -O ./data/${model}/" + ipAddress + "_" + serial + "_finish_menu.htm "
            // Create new directory for this printer model if does not exist.
            " sh -c \"[ -d \"./data/${model}\" ] || mkdir ./data/${model}\" ".execute().waitFor();
            
            def proc1 = " sh -c \" ${wgetCmd} ${pURL1} || ${wgetCmd} ${pURL2} || ${wgetCmd} ${pURL3} || echo 'fail' \"".execute()
            Thread.start { System.err << proc1.err } 
            proc1.waitFor()
            status = "success"
            status = proc1.text
            
            if (status.equals("fail")) {
                println "skip# ${lCount} : unknownEWS - | ${ipAddress} | ${model} | ${status}"
                unknownEWS.add(line.trim())
            } else {
                println "Grabbed# ${lCount} ${model} | ${ipAddress} | ${serial}"
              //  testModel.put(model, ipAddress)
            }
         } else {
            println "Skip# ${lCount} : No Response -  ${model} | ${ipAddress} | ${serial}"
            noResponse.add(line.trim())
         }   
     } else {
         println "Skip# ${lCount} : Already tested -  ${model} | ${ipAddress} | ${serial}"
     }
}

println "\n\n#####\n\n Pass \n\n#####\n\n"

for (model in passPrinter) {
        println model
}

println "\n\n#####\n\n Fail \n\n#####\n\n"

for (model in failPrinter) {
        println model
}

println "\n\n#####\n\n COULD NOT FIND FOLLOWING MODELS \n\n#####\n\n"

for (model in setModel) {
    if (!testModel.containsKey(model)) {
        println model
    }
}

println "\n\n#####\n\n Unknown EWS URL \n\n#####\n\n"

for (model in unknownEWS) {
        println model
}


println "\n\n#####\n\n No Response \n\n#####\n\n"

for (model in noResponse) {
        println model
}