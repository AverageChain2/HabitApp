package com.example.habitapp.data.repository

import com.example.habitapp.data.model.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

interface AuthRepo {
    val currentUser: FirebaseUser?
    suspend fun firebaseRegisterWithEmailAndPassword(email: String,
                                                   password: String): Response<Boolean>
    suspend fun sendEmailVerification(): Response<Boolean>
    suspend fun firebaseSignInWithEmailAndPassword(email: String,
                                                   password: String): Response<Boolean>
    suspend fun sendPasswordResetEmail(email: String): Response<Boolean>
    fun signOut()
}
class AuthRepository(private val auth: FirebaseAuth): AuthRepo {
    override val currentUser get() = auth.currentUser //Needed for log in VM
    override suspend fun firebaseRegisterWithEmailAndPassword(
        email: String, password: String): Response<Boolean>{
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
    override suspend fun sendEmailVerification(): Response<Boolean>{
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String): Response<Boolean>{
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