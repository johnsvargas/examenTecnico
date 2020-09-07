package com.juancarlos.examentecnico

import android.os.StrictMode
import org.json.JSONObject
import java.io.*
import java.net.URL
import java.net.URLConnection
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.nio.charset.Charset


fun downloadFile(url: String, outputFile: File) {
    try {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val u = URL(url)
        val conn: URLConnection = u.openConnection()
        val contentLength: Int = conn.contentLength
        val stream = DataInputStream(u.openStream())
        val buffer = ByteArray(contentLength)
        stream.readFully(buffer)
        stream.close()
        val fos = DataOutputStream(FileOutputStream(outputFile))
        fos.write(buffer)
        fos.flush()
        fos.close()
    } catch (e: FileNotFoundException) {
        return  // swallow a 404
    } catch (e: IOException) {
        return  // swallow a 404
    }
}

fun readFile(jsonFile:File) {
    try {
        if(jsonFile.exists()){
            val stream = FileInputStream(jsonFile)
            var jsonStr: String? = null
            try {
                val fc: FileChannel = stream.channel
                val bb: MappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
                jsonStr = Charset.defaultCharset().decode(bb).toString()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                stream.close()
            }
            /*  String jsonStr = "{\n\"data\": [\n    {\n        \"id\": \"1\",\n        \"title\": \"Farhan Shah\",\n        \"duration\": 10\n    },\n    {\n        \"id\": \"2\",\n        \"title\": \"Noman Shah\",\n        \"duration\": 10\n    },\n    {\n        \"id\": \"3\",\n        \"title\": \"Ahmad Shah\",\n        \"duration\": 10\n    },\n    {\n        \"id\": \"4\",\n        \"title\": \"Mohsin Shah\",\n        \"duration\": 10\n    },\n    {\n        \"id\": \"5\",\n        \"title\": \"Haris Shah\",\n        \"duration\": 10\n    }\n  ]\n\n}\n";
      */
            jsonStr?.let {
                val jsonObj = JSONObject(jsonStr)
                val data = jsonObj.getJSONArray("data")
            }
        }
        // Getting data JSON Array nodes


        // looping through All nodes
        /*for (i in 0 until data.length()) {
            val c = data.getJSONObject(i)
            val id = c.getString("id")
            val title = c.getString("title")
            val duration = c.getString("duration")
            //use >  int id = c.getInt("duration"); if you want get an int


            // tmp hashmap for single node
            val parsedData = HashMap<String, String>()

            // adding each child node to HashMap key => value
            parsedData["id"] = id
            parsedData["title"] = title
            parsedData["duration"] = duration


            // do what do you want on your interface
        }*/
    } catch (e: Exception) {
        e.printStackTrace()
    }
}