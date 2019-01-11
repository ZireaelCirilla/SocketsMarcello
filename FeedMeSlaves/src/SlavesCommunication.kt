import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import java.util.*

class SlavesCommunication(private val socket: Socket) : Thread() {

    @Override
    override fun run() {
        feedMe(socket)
    }

    private fun feedMe(socket: Socket) {

        var myis = socket.getInputStream()
        var isr = InputStreamReader(myis)
        var br = BufferedReader(isr)
        var line: String

        var os = socket.getOutputStream()
        var osw = OutputStreamWriter(os)
        var bw = BufferedWriter(osw)
        var sendLine: String

        var operator1Chosen: String
        var operator2Chosen: String

        //if (br.readLine() == "1") {
            println("Hello")
            do {
                line = br.readLine()
                println("Slave Said: $line")

                //var sc = Scanner(System.`in`)

                operator1Chosen = if (Math.random() > 0.49){
                    "*"
                } else {
                    "/"
                }

                operator2Chosen = if (Math.random() > 0.49){
                    "*"
                } else {
                    "/"
                }

                sendLine = "${Math.random() * 100},${Math.random() * 100},${Math.random() * 100},$operator1Chosen,$operator2Chosen"
                bw.write(sendLine)
                bw.newLine()
                bw.flush()

                println("Asked a Slave: $sendLine")

                //sendLine = sc.nextLine()

            } while (line != null)

            if (br != null) {
                br.close()
            }

        //}


    }

}