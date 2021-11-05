package kr.side.mococo.config

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.salt.RandomSaltGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig (
    @Value("\${jasypt.encryptor.password}")
    private val encryptKey: String,
) {

    @Bean
    fun jasyptStringEncryptor(): StringEncryptor {
        return PooledPBEStringEncryptor().apply {
            setConfig(SimpleStringPBEConfig().apply {
                password = encryptKey
                algorithm = "PBEWithMD5AndDES"
                keyObtentionIterations = 1000
                poolSize = 1
                saltGenerator = RandomSaltGenerator()
                stringOutputType = "base64"
            })
        }
    }
}