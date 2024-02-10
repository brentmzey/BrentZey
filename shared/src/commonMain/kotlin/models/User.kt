package models

import models.id.TenantId
import models.id.UserId

data class User
(
  val id: UserId,
  val tenantId: TenantId,
  val name: String,
  val email: String?,
  val username: String?
)
