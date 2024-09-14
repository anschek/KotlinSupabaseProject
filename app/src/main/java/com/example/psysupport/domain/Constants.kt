package com.example.psysupport.domain

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Constants {
    val supabaseClient = createSupabaseClient(
        supabaseKey = "",
        supabaseUrl = ""
    ){
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}