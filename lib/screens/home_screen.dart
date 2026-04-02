import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(20),
      children: [
        const SizedBox(height: 40),
        const Text('أهلاً بك في نـور 🌙', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Colors.white)),
        const Text('رصيدك الإيماني: 120 نقطة', style: TextStyle(color: Color(0xFF38BDF8), fontSize: 16)),
        const SizedBox(height: 30),
        
        // كارت التدبر
        Container(
          padding: const EdgeInsets.all(20),
          decoration: BoxDecoration(
            color: const Color(0xFF1E293B),
            borderRadius: BorderRadius.circular(16),
            border: Border.all(color: const Color(0xFF38BDF8).withOpacity(0.3)),
          ),
          child: const Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Icon(Icons.auto_awesome, color: Colors.amber),
                  SizedBox(width: 8),
                  Text('تدبر اليوم', style: TextStyle(color: Colors.white, fontSize: 18, fontWeight: FontWeight.bold)),
                ],
              ),
              SizedBox(height: 12),
              Text('﴿وَهُوَ مَعَكُمْ أَيْنَ مَا كُنتُمْ﴾\nالله معك في كل لحظة، فلا تقلق ولا تحزن.',
                  style: TextStyle(color: Colors.white70, fontSize: 16, height: 1.5)),
            ],
          ),
        ),
        
        const SizedBox(height: 30),
        const Text('تحديات اليوم', style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold, color: Colors.white)),
        const SizedBox(height: 15),
        
        // مهام اليوم
        ListTile(
          tileColor: const Color(0xFF1E293B),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          leading: const Icon(Icons.star, color: Colors.amber),
          title: const Text('صلاة الضحى', style: TextStyle(color: Colors.white)),
          trailing: const Text('+50 نقطة', style: TextStyle(color: Colors.green, fontWeight: FontWeight.bold)),
        ),
        const SizedBox(height: 10),
        ListTile(
          tileColor: const Color(0xFF1E293B),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          leading: const Icon(Icons.star, color: Colors.amber),
          title: const Text('أذكار الصباح', style: TextStyle(color: Colors.white)),
          trailing: const Text('+30 نقطة', style: TextStyle(color: Colors.green, fontWeight: FontWeight.bold)),
        ),
      ],
    );
  }
}
