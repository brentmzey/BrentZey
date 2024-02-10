//import arrow.core.Either
//import arrow.core.getOrElse
//import arrow.core.left
//import arrow.core.raise.either
//import arrow.core.raise.ensureNotNull
//import arrow.core.toOption
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import models.id.TenantId
import kotlin.jvm.JvmInline

object TenantConstants {

    data class TenantDoesNotExist(val key: String)

    @JvmInline
    value class ContextKey(val key: String)

    val BRENT_TENANT_ID = TenantId(uuidFrom("bca3f471-7f5d-4207-828c-1f75c9e95b73"))
    val BRENT_TENANT_CONTEXT_KEY = ContextKey("brent")

    val CONTEXT_KEYS: List<ContextKey> = listOf(
        BRENT_TENANT_CONTEXT_KEY
    )

    fun findContextKeyOpt(inputKey: String): ContextKey? =
        CONTEXT_KEYS.find { it.key == inputKey.lowercase() }

//    fun findContextKey(inputKey: String?): Either<TenantDoesNotExist, ContextKey> = either {
//        ensureNotNull( inputKey?.let { findContextKeyOpt(it) } ) {
//            TenantDoesNotExist(inputKey ?: "NONE")
//        }
//    }
//
//    fun findTenantIdHandle(inputKey: ContextKey): Either<TenantDoesNotExist, TenantId> = Either
//        .catchOrThrow<NoSuchElementException, TenantId> {
//            CONTEXT_KEY_TENANT_ID_MAP.filter { inputKey.equals(it) }.map { it.value }.first()
//        }.mapLeft { _ -> return TenantDoesNotExist(inputKey.key).left() }
//
//    fun findTenantIdHandle(inputKey: ContextKey?): Either<TenantDoesNotExist, TenantId> = either {
//        ensureNotNull(inputKey) { TenantDoesNotExist("NONE") }
//        return findTenantIdHandle(inputKey)
//    }
//
//    fun findTenantIdHandle(inputKey: String?): Either<TenantDoesNotExist, TenantId> = either {
//        val inputCtx = inputKey?.let { ContextKey(it) }
//        ensureNotNull(inputCtx) { TenantDoesNotExist(inputKey ?: "NONE") }
//        return findTenantIdHandle(inputCtx) // smart-cast to non-nullable occurs
//    }
//
//    fun findTenantId(inputKey: String): TenantId = findTenantIdHandle(findContextKeyOpt(inputKey))
//        .getOrElse { throw IllegalArgumentException("No valid tenantId for input key: $inputKey") }

    fun findTenantId(inputContext: ContextKey?): TenantId = CONTEXT_KEY_TENANT_ID_MAP[inputContext] ?:
        throw IllegalArgumentException("No valid tenantId for input context object: $inputContext")

//
//    fun findTenantId(tenantUuid: Uuid): TenantId = TENANT_IDS.find { tenantUuid.equals(it) }
//        .toOption()
//        .getOrElse { throw IllegalArgumentException("No valid tenantId for input UUID: $tenantUuid") }

    val TENANT_IDS: List<TenantId> = listOf(
        BRENT_TENANT_ID
    )

    val CONTEXT_KEY_TENANT_ID_MAP: Map<ContextKey, TenantId> = mapOf(
        Pair(BRENT_TENANT_CONTEXT_KEY, BRENT_TENANT_ID)
    )

    val TENANT_ID_CONTEXT_KEY_MAP = CONTEXT_KEY_TENANT_ID_MAP.entries.associate {
            (key, value) -> Pair(value, key)
    }
}