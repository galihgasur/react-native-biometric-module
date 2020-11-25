package com.reactnativebiometricmodule

import android.content.pm.PackageManager
import androidx.biometric.BiometricManager
import com.facebook.react.bridge.*

class BiometricModuleModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "BiometricModule"
    }

    // Example method
    // See https://facebook.github.io/react-native/docs/native-modules-android
    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {

      promise.resolve(a * b)

    }

  // callback : status: Boolean, error: String
  @ReactMethod
  fun canEvaluatePolicy(callback: Callback) {
    val biometricManager = BiometricManager.from(reactApplicationContext)
    when (biometricManager.canAuthenticate()) {
      BiometricManager.BIOMETRIC_SUCCESS -> callback.invoke(true, null)
      BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
        callback.invoke(false, "The hardware is unavailable. Try again later")
      }
      BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
        callback.invoke(false, "The user does not have any biometrics enrolled")
      }
      BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
        callback.invoke(false, "Device is not compatible for Biometric login")
      }
    }
  }

  // callback : type: String, error: String
  @ReactMethod
  fun biometricType(callback: Callback) {
    val biometricManager = BiometricManager.from(reactApplicationContext)
    when (biometricManager.canAuthenticate()) {
      BiometricManager.BIOMETRIC_SUCCESS -> callback.invoke("Touch ID", null)
      BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
        callback.invoke(null, "The hardware is unavailable. Try again later")
      }
      BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
        callback.invoke("Touch ID", null)
      }
      BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
        callback.invoke(null, "Device is not compatible for Biometric login")
      }
    }
  }

  // callback : type: String, error: String
  @ReactMethod
  fun authenticateUser(callback: Callback) {
    canEvaluatePolicy(Callback {
      val status = it[0] as Boolean
      if (status) {

      }
    })
  }


}
