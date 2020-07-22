package com.example.no_cloudsave

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.State
import androidx.compose.state
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.example.no_cloudsave.Managers.ApiService
import com.example.no_cloudsave.Managers.ApiClient
import com.example.no_cloudsave.ui.NocloudsaveTheme
import retrofit2.Call
import retrofit2.Response
import androidx.compose.*



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

           App()
        }
    }
}

@Composable
fun App() {
    lateinit var apiService: ApiService

    var recipes: MutableState<MutableList<Recipe>> = mutableStateOf(mutableListOf<Recipe>())

    apiService = ApiClient.getClient().create(ApiService::class.java)
    val getReq = apiService.getRecipes()
    getReq.enqueue(object : retrofit2.Callback<List<Recipe>> {

        override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
            if (response.isSuccessful) {
                recipes.value = (response.body() as MutableList<Recipe>?)!!
                print(response.body())
            }
        }
        override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {

            print(call)

        }
    })
    NocloudsaveTheme {
        MaterialTheme {
            VerticalScroller() {
                Column() {
                    for (recipe in recipes.value ){
                        RecipeCard(recipe = recipe, height = 200.0)
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun DefaultPreview() {

}

