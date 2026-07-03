package io.jadu.phoenix.storage

import dev.gitlive.firebase.storage.Data
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create

@OptIn(ExperimentalForeignApi::class)
actual fun storageDataOf(byte: ByteArray): Data =
    Data(
        if(byte.isEmpty()) {
            NSData()
        } else {
            byte.usePinned { pinned ->
                NSData.create(bytes = pinned.addressOf(0), length = byte.size.toULong())
            }
        }
    )