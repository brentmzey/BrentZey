package models.id

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlin.reflect.KClass

//sealed class InternalId(open val id: Uuid) {
//    abstract fun idTypeName(): String
//}

sealed interface InternalId

inline fun <reified U : InternalId> idType(id: Uuid?): KClass<U> {
    val internalId = id ?: uuid4()
    return when (U::class) {
        UserId::class -> UserId(internalId)
        TenantId::class -> TenantId(internalId)
//        null -> NullPointerException("Supplied object is null")
        else -> IllegalArgumentException(
            "No proper subtype of InternalId" +
                    " supplied to function 'checkIdType'"
        )
    } as KClass<U>
//    return when (id) {
//        is UserId -> UserId(id.id)
//        is TenantId -> TenantId(id.id)
//        else -> IllegalArgumentException(
//            "No proper subtype of InternalId" +
//                    " supplied to function 'checkIdType'"
//        )
//    } as U
}