package com.gendroid.mac.macadress_gen

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.net.NetworkInterface

/** MacadressGenPlugin */
class MacadressGenPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "macadress_gen")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when (call.method) {
    "getMac" -> {
      var mac = getMacAddress(result)
      result.success(mac)
    }
    else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun getMacAddress(@NonNull result: Result): String {
    try {
      val networkInterfaces = NetworkInterface.getNetworkInterfaces()
      for (nif in networkInterfaces) {
        if (!nif.name.equals("wlan0", ignoreCase = true)) continue
        val macBytes = nif.hardwareAddress ?: return ""
        val builder = StringBuilder()
        for (b in macBytes) {
          builder.append(String.format("%02X:", b))
        }

        if (builder.isNotEmpty()) {
          builder.deleteCharAt(builder.length - 1)
        }
        return builder.toString()
      }
    } catch (ex: Exception) {
      result.notImplemented()
    }

    return "02:00:00:00:00:00"

  }


}
