import java.net.ServerSocket
import java.net.Socket

class main {

    companion object {

        val PORT = 40000

        @JvmStatic
        fun main(args: Array<String>) {
            println("Kotlin main is running here!")

            var sk = ServerSocket(PORT)

            var socket: Socket

            while (true) {
                socket = sk.accept()

                SlavesCommunication(socket).start()
            }


        }


    }

}

