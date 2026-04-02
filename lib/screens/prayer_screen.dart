import 'package:flutter/material.dart';
import '../services/prayer_service.dart';
import 'package:adhan/adhan.dart';
import 'package:intl/intl.dart';

class PrayerScreen extends StatelessWidget {
  const PrayerScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<PrayerTimes?>(
      future: PrayerService.getTimes(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) return const Center(child: CircularProgressIndicator());
        if (!snapshot.hasData || snapshot.data == null) return const Center(child: Text("برجاء تفعيل الموقع (GPS)"));

        final times = snapshot.data!;
        final prayerList = [
          {'name': 'الفجر', 'time': times.fajr},
          {'name': 'الشروق', 'time': times.sunrise},
          {'name': 'الظهر', 'time': times.dhuhr},
          {'name': 'العصر', 'time': times.asr},
          {'name': 'المغرب', 'time': times.maghrib},
          {'name': 'العشاء', 'time': times.isha},
        ];

        return ListView(
          padding: const EdgeInsets.fromLTRB(20, 60, 20, 20),
          children: [
            const Text('مواقيت الصلاة اليوم', style: TextStyle(fontSize: 28, color: Color(0xFF38BDF8), fontWeight: FontWeight.bold)),
            const SizedBox(height: 20),
            ...prayerList.map((p) => Card(
              color: const Color(0xFF1E293B),
              child: ListTile(
                title: Text(p['name'] as String, style: const TextStyle(color: Colors.white, fontSize: 18)),
                trailing: Text(DateFormat.jm().format(p['time'] as DateTime), style: const TextStyle(color: Color(0xFF38BDF8), fontSize: 18, fontWeight: FontWeight.bold)),
              ),
            )).toList(),
          ],
        );
      },
    );
  }
}
