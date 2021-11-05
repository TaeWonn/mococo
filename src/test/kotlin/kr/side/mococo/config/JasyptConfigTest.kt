package kr.side.mococo.config

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class JasyptConfigTest {

    private val password = "captainYian"
    private val target: String = "root"
    private val jasypt = StandardPBEStringEncryptor().apply {
        setPassword(password)
        setAlgorithm("PBEWithMD5AndDES")
    }

    @Test
    @Disabled
    fun `jasypt 인코딩`() {
        println(jasypt.encrypt(target))
    }

    @Test
    @Disabled
    fun `jasypt 디코딩`() {
        println(jasypt.decrypt(target))
    }
}