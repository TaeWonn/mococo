package kr.side.mococo.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.MappedSuperclass
import javax.persistence.EntityListeners
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
open class BaseEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0,

    @CreatedDate
    @Column(columnDefinition = "DATETIME", nullable = false)
    open var createdDate: LocalDateTime = LocalDateTime.now()
)

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
open class UpdatableEntity (
    @LastModifiedDate
    @Column(columnDefinition = "DATETIME", nullable = false)
    open var updatedAt: LocalDateTime = LocalDateTime.now()
): BaseEntity()

@MappedSuperclass
open class DeletableEntity (
    @Column(columnDefinition = "DATETIME")
    open var deletedAt: LocalDateTime? = null
): UpdatableEntity()
