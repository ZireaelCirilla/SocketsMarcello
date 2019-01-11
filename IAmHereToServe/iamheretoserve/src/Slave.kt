import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import java.util.*

class Slave {
    companion object {

        val PORT = 40000

        @JvmStatic
        fun main(args: Array<String>) {
            println("Kotlin main is running here!")

            val HOST = "localhost"

            var socket = Socket(HOST, PORT)

            iAmReadyMaster(socket)

        }

        private fun iAmReadyMaster(socket: Socket) {

            var os = socket.getOutputStream()
            var osw = OutputStreamWriter(os)
            var bw = BufferedWriter(osw)

            var myis = socket.getInputStream()
            var isr = InputStreamReader(myis)
            var br = BufferedReader(isr)

            var sc = Scanner(System.`in`)
            println("Sent: I am ready Master")
            bw.write("I am ready, Master")
            bw.newLine()
            bw.flush()
            var lineFromServer: String = br.readLine()
            var result: String = "none"

            var n1 = 0.0
            var n2 = 0.0
            var n3 = 0.0
            var o1: String
            var o2: String

            while( lineFromServer != null){

                println("Master asked for: $lineFromServer")

                val content = lineFromServer.split(",")

                println(content[0])

                try {
                    n1 = content[0].toDouble()
                    n2 = content[1].toDouble()
                    n3 = content[2].toDouble()
                } catch (e: Exception){
                    println("One of your numbers is wrong, Master")
                    return
                }

                o1 = content[3]
                o2 = content[4]

                if (n2 == 0.0 && o1.equals("/")){
                    println("Can't divide by 0, Master")
                    return
                } else if (n3 == 0.0 && o2.equals("/")){
                    println("Can't divide by 0, Master")
                    return
                }

                if (o1.equals("*") && o2.equals("*")){
                    result = "${(n1 * n2 * n3)}"
                } else if (o1.equals("/") && o2.equals("/")){
                    result = "${(n1 / n2 / n3)}"
                } else if (o1.equals("*") && o2.equals("/")){
                    result = "${(n1 * n2 / n3)}"
                } else if (o1.equals("/") && o2.equals("*")){
                    result = "${(n1 / n2 * n3)}"
                }

                bw.write(result)
                bw.newLine()
                bw.flush()

                lineFromServer = br.readLine()
            }

        }

    }
}