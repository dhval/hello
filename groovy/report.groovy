import groovy.io.FileType
import groovy.xml.*

class Setting {
    String id, name
    Map option
}
    
class Printer {
    String model, address, serial
    Setting[] settings
}

println System.getProperty('user.home');


/**
    Recurse all the finish_menu.html downloaded from EWS.
    We need only one ipAddress per model.
**/

def printers = [:]
def rootDir = new File("extract/data")
rootDir.eachDir { model ->
def modelName = model.toString().tokenize("\\")[2]
    def files = new File("extract/data/" + modelName)
    files.eachFileRecurse (FileType.FILES) { file ->
        if (printers.containsKey(modelName)) return;
        if (file.size() != 0) printers.put(modelName, file)
    }
}
println "Total unique printer models# ${printers.size()}"


/** 
    Parse EWS's finishing menu from HTML.
**/      

def list = []
printers.each() { key, value -> 
    @Grab(group='org.ccil.cowan.tagsoup',
          module='tagsoup', version='1.2' )
    def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()
    def slurper = new XmlSlurper(tagsoupParser)
    def htmlParser = slurper.parse(value)
    def settings = []
    htmlParser.body.form.table.tr.findAll{ it }.each {
//        println "${it}"
        if ( it.td[1].select != "") {
            def settingId, settingName, option = [:]
            settingName = it.td[0].text().trim()
            settingId = it.td[1].select.@name.text().trim()     
            it.td[1].select.option.findAll{ it }.each {
                option.put(it.@value, it.text()) 
            }
            def setting = new Setting(id:settingId, name:settingName, option:option)
            settings.add(setting)
        }
    }
    def token1 = value.toString().tokenize("\\")
    def token2 = token1[3].tokenize("_")
//    println " ${token1[2]} ${token2[0]} ${token2[1]} " 
    def printer = new Printer(model:token1[2], address:token2[0], serial:token2[1], settings:settings)
    list.add(printer)
}


/**
    Create report.xml from above data.
**/    

def builder = new StreamingMarkupBuilder()
builder.encoding = 'UTF-8'
def printXML = builder.bind {
    mkp.xmlDeclaration()
//    namespaces << [meta:'http://printer/finishmenu']
    finishing (count: list.size()) {
        list.each() { printer ->
            model ( ip:printer.address, model:printer.model, serial:printer.serial ) {
                printer.settings.each() { settingV ->
                    setting (name:settingV.name, id:settingV.id)
                    settingV.option.each { key, value ->
                        option (id:key, value)
                    }
                }
            }        
        }
    }    
}
PrintWriter pw = new PrintWriter("report.xml")  
pw.write(XmlUtil.serialize(printXML))  
pw.close()
