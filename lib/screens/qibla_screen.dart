import 'dart:math';
import 'package:flutter/material.dart';
import 'package:flutter_qiblah/flutter_qiblah.dart';

class QiblaScreen extends StatelessWidget {
  const QiblaScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return StreamBuilder(
      stream: FlutterQiblah.qiblahStream,
      builder: (context, AsyncSnapshot<QiblahDirection> snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) return const Center(child: CircularProgressIndicator());
        final qiblahDirection = snapshot.data!;
        return Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('اتجاه القبلة', style: TextStyle(fontSize: 24, color: Color(0xFF38BDF8))),
              const SizedBox(height: 40),
              Transform.rotate(
                angle: (qiblahDirection.qiblah * (pi / 180) * -1),
                child: Icon(Icons.navigation, size: 200, color: Colors.redAccent),
              ),
            ],
          ),
        );
      },
    );
  }
}
