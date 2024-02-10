package models.id

import com.benasher44.uuid.Uuid
import kotlin.jvm.JvmInline

@JvmInline
value class TenantId(val id: Uuid) : InternalId

//data class TenantId(override val id: Uuid) : InternalId(id) {
//    fun idType(): TenantId = models.id.idType<TenantId>(this)
//    override fun idTypeName(): String = idType()::class.simpleName ?: "TenantId"
//}
