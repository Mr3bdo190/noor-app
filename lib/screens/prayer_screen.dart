import 'package:flutter/material.dart';

class PrayerScreen extends StatelessWidget {
  const PrayerScreen({super.key});

  final List<Map<String, dynamic>> _prayers = const [
    {'name': 'الفجر', 'time': '04:30 ص', 'isNext': false},
    {'name': 'الشروق', 'time': '05:55 ص', 'isNext': false},
    {'name': 'الظهر', 'time': '12:05 م', 'isNext': true},
    {'name': 'العصر', 'time': '03:35 م', 'isNext': false},
    {'name': 'المغرب', 'time': '06:15 م', 'isNext': false},
    {'name': 'العشاء', 'time': '07:40 م', 'isNext': false},
  ];

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.only(top: 60, left: 20, right: 20, bottom: 20),
      children: [
        const Text('مواقيت الصلاة', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Color(0xFF38BDF8))),
        const SizedBox(height: 5),
        const Row(
          children: [
            Icon(Icons.location_on, color: Colors.grey, size: 18),
            SizedBox(width: 5),
            Text('القاهرة، مصر', style: TextStyle(color: Colors.grey, fontSize: 16)),
          ],
        ),
        const SizedBox(height: 30),
        
        // كارت الصلاة القادمة
        Container(
          padding: const EdgeInsets.all(30),
          decoration: BoxDecoration(
            gradient: const LinearGradient(colors: [Color(0xFF38BDF8), Color(0xFF0284C7)]),
            borderRadius: BorderRadius.circular(20),
          ),
          child: const Column(
            children: [
              Text('الصلاة القادمة', style: TextStyle(color: Colors.white70, fontSize: 16)),
              SizedBox(height: 10),
              Text('الظـهـر', style: TextStyle(color: Colors.white, fontSize: 36, fontWeight: FontWeight.bold)),
              SizedBox(height: 10),
              Text('متبقي: 01:45:20', style: TextStyle(color: Colors.white, fontSize: 18)),
            ],
          ),
        ),
        
        const SizedBox(height: 30),
        
        // قائمة الصلوات
        ..._prayers.map((prayer) {
          final isNext = prayer['isNext'];
          return Card(
            color: isNext ? const Color(0xFF38BDF8).withOpacity(0.1) : const Color(0xFF1E293B),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15),
              side: BorderSide(color: isNext ? const Color(0xFF38BDF8) : Colors.transparent),
            ),
            margin: const EdgeInsets.only(bottom: 12),
            child: ListTile(
              contentPadding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
              leading: Icon(isNext ? Icons.notifications_active : Icons.notifications_none, color: isNext ? const Color(0xFF38BDF8) : Colors.grey),
              title: Text(prayer['name'], style: TextStyle(color: isNext ? const Color(0xFF38BDF8) : Colors.white, fontSize: 20, fontWeight: FontWeight.bold)),
              trailing: Text(prayer['time'], style: TextStyle(color: isNext ? const Color(0xFF38BDF8) : Colors.white, fontSize: 18, fontWeight: FontWeight.bold)),
            ),
          );
        }).toList(),
      ],
    );
  }
}
