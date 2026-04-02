import 'package:flutter/material.dart';

class QiblaScreen extends StatefulWidget {
  const QiblaScreen({super.key});

  @override
  State<QiblaScreen> createState() => _QiblaScreenState();
}

class _QiblaScreenState extends State<QiblaScreen> with SingleTickerProviderStateMixin {
  late AnimationController _controller;

  @override
  void initState() {
    super.initState();
    // أنيميشن خفيف عشان يدي إحساس إن البوصلة شغالة لحد ما نربطها بالحساسات
    _controller = AnimationController(vsync: this, duration: const Duration(seconds: 2))..repeat(reverse: true);
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        const Text('اتجاه القبلة', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Color(0xFF38BDF8))),
        const SizedBox(height: 10),
        const Text('تأكد من إبعاد الهاتف عن أي مجال مغناطيسي', style: TextStyle(color: Colors.grey)),
        const SizedBox(height: 60),
        
        // رسم البوصلة
        Stack(
          alignment: Alignment.center,
          children: [
            Container(
              width: 250,
              height: 250,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                border: Border.all(color: const Color(0xFF38BDF8), width: 3),
                color: const Color(0xFF1E293B),
              ),
            ),
            AnimatedBuilder(
              animation: _controller,
              builder: (context, child) {
                return Transform.rotate(
                  angle: 0.1 * _controller.value, // حركة خفيفة لليمين واليسار
                  child: const Icon(Icons.navigation, size: 150, color: Colors.redAccent),
                );
              },
            ),
            const Positioned(top: 20, child: Text('N', style: TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold))),
          ],
        ),
      ],
    );
  }
}
