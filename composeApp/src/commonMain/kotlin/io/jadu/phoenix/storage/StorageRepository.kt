package io.jadu.phoenix.storage

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.storage.storage

class StorageRepository {

    private val storage get() = Firebase.storage

    suspend fun upload(path: String, byte: ByteArray) : String {
        val ref = storage.reference(path)
        ref.putData(storageDataOf(byte))
        return ref.getDownloadUrl()
    }

    suspend fun downloadUrl(path: String) : String =
        storage.reference(path).getDownloadUrl()

    suspend fun delete(path: String){
        storage.reference(path).delete()
    }

    suspend fun list(prefix: String) : List<String> =
        storage.reference(prefix).listAll().items.map { it.path }

}