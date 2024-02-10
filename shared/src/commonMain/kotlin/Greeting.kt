import models.User

class Greeting(user: User) {
    private val platform = getPlatform()
    private val currentUser = user

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun currentUserDetail(): String {
        val (userId, tenantId, name, email, username) = currentUser
        return "Current User: $name with ID: ${userId.id} and tenantID: ${tenantId.id}"
    }
}