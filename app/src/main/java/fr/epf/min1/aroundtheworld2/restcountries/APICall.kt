package fr.epf.min1.aroundtheworld2.restcountries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun APICallScreen() {
    val countryState = remember { mutableStateOf<List<Country>?>(null) }
    val loadingState = remember { mutableStateOf(false) }
    val errorState = remember { mutableStateOf<String?>(null) }
    val countryNameState = remember { mutableStateOf("france") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = countryNameState.value,
            onValueChange = { countryNameState.value = it },
            label = { Text("Enter country name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                errorState.value = null
                loadingState.value = true
                loadCountry(countryNameState.value, countryState, loadingState, errorState)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Load Country Data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            loadingState.value -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            errorState.value != null -> Text(
                text = errorState.value ?: "Unknown error",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            countryState.value != null -> CountryList(countryState.value!!)
        }
    }
}

private fun loadCountry(countryName: String, countryState: MutableState<List<Country>?>, loadingState: MutableState<Boolean>, errorState: MutableState<String?>) {
    val call = RetrofitClient.apiService.getCountry(countryName)
    call.enqueue(object : Callback<List<Country>> {
        override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
            loadingState.value = false
            if (response.isSuccessful) {
                countryState.value = response.body()
            } else {
                errorState.value = "Error: ${response.code()}"
            }
        }

        override fun onFailure(call: Call<List<Country>>, t: Throwable) {
            loadingState.value = false
            errorState.value = "Failed to load data: ${t.message}"
        }
    })
}


private fun loadCountries(countriesState: MutableState<List<Country>?>) {
    val call = RetrofitClient.apiService.getCountries()
    call.enqueue(object : Callback<List<Country>> {
        override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
            if (response.isSuccessful) {
                val countries = response.body()
                countriesState.value = countries
            } else {
                // Handle error
            }
        }

        override fun onFailure(call: Call<List<Country>>, t: Throwable) {
            // Handle failure
        }
    })
}

private fun loadCountry(countryName: String, countryState: MutableState<List<Country>?>) {
    val call = RetrofitClient.apiService.getCountry(countryName)
    call.enqueue(object : Callback<List<Country>> {
        override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
            if (response.isSuccessful) {
                val country = response.body()
                countryState.value = country
            } else {
                // Handle error
            }
        }

        override fun onFailure(call: Call<List<Country>>, t: Throwable) {
            // Handle failure
        }
    })
}

@Composable
fun CountryList(countries: List<Country>) {
    Column {
        for (country in countries) {
            Text(text = "Name: ${country.name}")
            Text(text = "Capital: ${country.capital}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
