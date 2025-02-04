package com.example.firstappp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

@SuppressLint("ServiceCast")
@Composable
fun EncoderDecoderApp() {

    var inputEncode by remember { mutableStateOf("")}
    var encodedResult by remember { mutableStateOf("")}
    var inputDecode by remember { mutableStateOf("")}
    var decodedResult by remember { mutableStateOf("")}

    val context = LocalContext.current
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Encoder/Decoder App",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 60.dp, bottom = 16.dp)
        )

        OutlinedTextField(
            value = inputEncode,
            onValueChange = { inputEncode = it },
            label = {Text("Enter a string to encode")},
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            onClick = {
                encodedResult =  encode(inputEncode)
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "ENCODE", fontSize = 14.sp)
        }
        // Encoded result with custom copy button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // Ensures alignment with button height
        ) {
            // Encoded text aligned to the leftmost side
            SelectionContainer(
                modifier = Modifier.weight(1f) // Takes available space, aligning text to the left
            ) {
                Text(
                    text = "Encoded: $encodedResult",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start // Align text to the start
                )
            }

            // Copy button aligned to the rightmost side
            IconButton(
                onClick = {
                    val clip = ClipData.newPlainText("Encoded Result", encodedResult)
                    clipboard.setPrimaryClip(clip)
                },
                modifier = Modifier.align(Alignment.CenterVertically) // Aligns with text vertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_copy), // Replace with your image name
                    contentDescription = "Copy",
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        OutlinedTextField(
            value = inputDecode,
            onValueChange = { inputDecode = it },
            label = { Text("Enter a string to decode") },
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            onClick = {
                decodedResult = decode(inputDecode)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "DECODE", fontSize = 14.sp)
        }
        // Decoded result with custom copy button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // Ensures alignment with button height
        ) {
            SelectionContainer(
                modifier = Modifier.weight(1f) // Takes available space, aligning text to the left
            ) {
                Text(
                    text = "Decoded: $decodedResult",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start // Align text to the start
                )
            }


            IconButton(
                onClick = {
                    val clip = ClipData.newPlainText("Decoded Result", decodedResult)
                    clipboard.setPrimaryClip(clip)
                },
                modifier = Modifier.align(Alignment.CenterVertically) // Aligns with text vertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_copy), // Replace with your image name
                    contentDescription = "Copy",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

fun decode(input: String):String{
    return input.map{
            char-> (char.code-2).toChar()
    }.joinToString("")
}

fun encode(input: String):String{
    return input.map{
            char-> (char.code+2).toChar()
    }.joinToString("")
}