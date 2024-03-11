package me.irinastyazhkina

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main(args: Array<String>) {
    val options = FirebaseOptions.builder()
        .setCredentials(
            GoogleCredentials.fromStream(FileInputStream("src/fcm.json"))
        )
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
            "userId": 1,
            "userName": "Vasiliy",
            "postId": 2,
            "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val newPostMessage = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
            "author": "Netology",
            "content": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
    FirebaseMessaging.getInstance().send(newPostMessage)
}