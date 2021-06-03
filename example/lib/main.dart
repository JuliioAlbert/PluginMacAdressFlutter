import 'package:flutter/material.dart';
import 'package:macadress_gen/macadress_gen.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: 'Material App', home: HomePage());
  }
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  MacadressGen macadressGen = MacadressGen();

  String? mac;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('MAC ADDRESS'),
      ),
      body: Center(
          child: Text(
        mac ?? 'MAC ',
        style: TextStyle(
            fontSize: 30, fontWeight: FontWeight.bold, color: Colors.black54),
      )),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          await getMAc();
          setState(() {});
        },
      ),
    );
  }

  Future getMAc() async {
    mac = await macadressGen.getMac();
  }
}
