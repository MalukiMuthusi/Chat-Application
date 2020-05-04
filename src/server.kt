import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.ServerSocket

object TCPServer {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            // create Server socket bound to port 8888.
            val server = ServerSocket(8888)

            // Listen for connection and accept. Returns a socket object.
            val serverClient = server.accept()

            // bind the server to an ip address.
//            server.bind(192.168.19.2)

            val inStream = DataInputStream(serverClient.getInputStream())
            val outStream = DataOutputStream(serverClient.getOutputStream())

            var clientMessage = ""
            var serverMessage: String? = ""

            while (clientMessage != "bye") {
                clientMessage = inStream.readUTF()
                println("From Client: $clientMessage")
                println("Type your message:")
                serverMessage = readLine()!!
                outStream.writeUTF(serverMessage.toString())
                outStream.flush()
            }

            inStream.close()
            outStream.close()

            // close connection with this client.
            serverClient.close()

            // close this socket.
            server.close()

        } catch (e: Exception) {
            println(e)
        }
    }
}