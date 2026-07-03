package io.jadu.phoenix.storage

import dev.gitlive.firebase.storage.Data

actual fun storageDataOf(byte: ByteArray): Data = Data(byte)