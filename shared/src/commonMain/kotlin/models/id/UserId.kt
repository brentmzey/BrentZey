package models.id

import com.benasher44.uuid.Uuid
import kotlin.jvm.JvmInline

@JvmInline
value class UserId(val id: Uuid) : InternalId

//data class UserId(override val id: Uuid) : InternalId(id) {
//    fun idType(): UserId = models.id.idType<UserId>(this)
//    override fun idTypeName(): String = idType()::class.simpleName ?: "UserId"
//}
