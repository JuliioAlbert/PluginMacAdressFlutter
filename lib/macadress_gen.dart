import 'dart:async';

import 'package:flutter/services.dart';

class MacadressGen {
  static const MethodChannel _channel = const MethodChannel('macadress_gen');
  Future<String> getMac() async {
    try {
      String mac = await _channel.invokeMethod("getMac");
      return mac;
    } catch (e) {
      return 'error';
    }
  }
}
