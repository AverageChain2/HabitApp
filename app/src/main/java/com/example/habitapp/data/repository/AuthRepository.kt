package com.example.habitapp.data.repository

import com.example.habitapp.data.model.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

interface AuthRepo {
    val currentUser: FirebaseUser?
    suspend fun firebaseRegisterWithEmailAndPassword(
        email: String,
        password: String
    ): Response<Boolean>

    suspend fun sendEmailVerification(): Response<Boolean>
    suspend fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String
    ): Response<Boolean>

    suspend fun sendPasswordResetEmail(email: String): Response<Boolean>
    fun signOut()
}

class AuthRepository(private val auth: FirebaseAuth) : AuthRepo {
    override val currentUser get() = auth.currentUser //Needed for log in VM

    override suspend fun firebaseRegisterWithEmailAndPassword(
        email: String, password: String
    ): Response<Boolean> {
        return try {
            // 1. Create the user
            auth.createUserWithEmailAndPassword(email, password).await()

            // 2. Immediately sign the user out
            // We need to check if there's a current user before signing out,
            // just in case (though after successful creation, there should be).
            if (auth.currentUser != null) {
                auth.signOut() // This makes auth.currentUser null again
            }
            Response.Success(true) // Registration was successful
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun sendEmailVerification(): Response<Boolean> {
        return try {
            // Note: This will likely fail if no user is signed in.
            // You might need to re-think the flow if you send verification
            // email immediately after a registration where you've signed them out.
            // Typically, sendEmailVerification is called when a user IS signed in.
            auth.currentUser?.sendEmailVerification()?.await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ): Response<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String):
            Response<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun signOut() = auth.signOut()
}