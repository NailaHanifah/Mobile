package com.example.helloui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloui.ui.theme.HelloUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // State untuk Toggle Mode Gelap
            var isDark by remember { mutableStateOf(false) }

            // Menggunakan Theme bawaan projectmu
            HelloUITheme(darkTheme = isDark) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        isDark = isDark,
                        onThemeChange = { isDark = it }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    // State untuk Input dan Hasil Sapaan
    var nameInput by remember { mutableStateOf("") }
    var greetingResult by remember { mutableStateOf("Halo!") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        // 4. Switch/Toggle Mode Gelap/Terang
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = if (isDark) "Mode Gelap" else "Mode Terang")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = isDark, onCheckedChange = onThemeChange)
        }

        // 1. TextInput (Nama)
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Masukkan Nama") },
            modifier = Modifier.fillMaxWidth()
        )

        // 2. Tombol "Sapa"
        Button(
            onClick = {
                greetingResult = if (nameInput.isNotBlank()) "Hello $nameInput!" else "Halo!"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sapa")
        }

        // 3. TextView/Text (Hasil)
        Text(
            text = greetingResult,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

// Preview agar kamu bisa lihat tampilan di Android Studio tanpa run emulator
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloUITheme {
        MainScreen(isDark = false, onThemeChange = {})
    }
}