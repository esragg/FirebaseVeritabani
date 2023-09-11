package com.pisiitech.firebaseveritabani

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pisiitech.firebaseveritabani.ui.theme.FirebaseVeritabaniTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseVeritabaniTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
    //ekle()
    //tumKisiler()
    sil()
}

fun ekle() {
    val db = FirebaseDatabase.getInstance()
    val refKisiler = db.getReference("kisiler")

    val yeniKisi = Kisiler("Talat",36)
    refKisiler.push().setValue(yeniKisi)
}

fun tumKisiler() {
    val db = FirebaseDatabase.getInstance()
    val refKisiler = db.getReference("kisiler")

    refKisiler.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            for (d in snapshot.children) {
                val kisi = d.getValue(Kisiler::class.java)

                if(kisi != null) {
                    Log.e("Kisi Bilgi","***************")
                    Log.e("Kisi key",d.key!!)
                    Log.e("Kisi ad", kisi.kisi_ad!!)
                    Log.e("Kisi yas", kisi.kisi_yas.toString())
                }
            }
        }

        override fun onCancelled(error: DatabaseError) { }
    })
}

fun sil() {
    val db = FirebaseDatabase.getInstance()
    val refKisiler = db.getReference("kisiler")

    refKisiler.child("-Ne2g0Ho4X9HbKf0WqCx").removeValue()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseVeritabaniTheme {
        Sayfa()
    }
}