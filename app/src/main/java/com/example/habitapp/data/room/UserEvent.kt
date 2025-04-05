package com.example.habitapp.data.room

import com.example.habitapp.data.room.database.user.User

sealed interface UserEvent {

    object SaveUser: UserEvent
    data class SetFirstName(val firstName: String): UserEvent
    data class SetSurname(val surname: String): UserEvent
    data class SetTelNo(val telNo: String): UserEvent
    object ShowDialog: UserEvent
    object HideDialog: UserEvent
    data class SortUser(val sortType: SortType): UserEvent
    data class DeleteUser(val user: User): UserEvent

}