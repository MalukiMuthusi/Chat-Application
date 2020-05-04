import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.Socket

object TCPClient {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            // host and port number.
            val socket = Socket("127.0.0.1", 8888)

            // read from the socket.
            val inStream = DataInputStream(socket.getInputStream())

            // Write to the socket.
            val outStream = DataOutputStream(socket.getOutputStream())

            var clientMessage = ""
            var serverMessage = ""

            while (clientMessage != "bye") {
                println("Type message:")
                clientMessage = readLine()!!

                // Write to the socket.
                outStream.writeUTF(clientMessage)
                outStream.flush()

                // read from socket.
                serverMessage = inStream.readUTF()
                println("From Server: $serverMessage")
            }
            outStream.close()
            outStream.close()
            socket.close()
        } catch (e: Exception) {
            println(e)
        }
    }
}