package com.takagi.makeyourbody.data.raw

import com.takagi.makeyourbody.data.UserInformation

data class UserInfoRaw(
    val name: String? = null,
    val age: String? = null,
    val gender: String? = null,
    val height: String? = null,
    val weight: String? = null,
    val type: String? = null,
    val userid: String? = null,
) {

    fun toUserInformation(): UserInformation {
        return UserInformation(
            name = checkNotNull(this.name),
            age = checkNotNull(this.age),
            gender = checkNotNull(this.gender),
            height = checkNotNull(this.height),
            weight = checkNotNull(this.weight),
            type = checkNotNull(this.type),
            userId = checkNotNull(this.userid)
        )
    }
}
