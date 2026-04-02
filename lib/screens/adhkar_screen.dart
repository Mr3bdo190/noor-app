import 'package:flutter/material.dart';

class AdhkarScreen extends StatefulWidget {
  const AdhkarScreen({super.key});

  @override
  State<AdhkarScreen> createState() => _AdhkarScreenState();
}

class _AdhkarScreenState extends State<AdhkarScreen> {
  final List<Map<String, dynamic>> _adhkar = [
    {'text': 'سُبْحَانَ اللَّهِ وَبِحَمْدِهِ', 'count': 33, 'total': 33},
    {'text': 'أَسْتَغْفِرُ اللَّهَ وَأَتُوبُ إِلَيْهِ', 'count': 100, 'total': 100},
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.fromLTRB(20, 60, 20, 20),
      itemCount: _adhkar.length + 1,
      itemBuilder: (context, index) {
        if (index == 0) return const Text('الأذكار', style: TextStyle(fontSize: 28, color: Color(0xFF38BDF8), fontWeight: FontWeight.bold));
        final zikr = _adhkar[index - 1];
        final isDone = zikr['count'] == 0;
        return Card(
          color: isDone ? Colors.green.withOpacity(0.1) : const Color(0xFF1E293B),
          child: ListTile(
            onTap: isDone ? null : () => setState(() => _adhkar[index-1]['count']--),
            title: Text(zikr['text'], textAlign: TextAlign.center),
            subtitle: LinearProgressIndicator(
              // التصحيح هنا: تحويل لـ double صريح
              value: (1.0 - (zikr['count'] / zikr['total'])).toDouble(),
              color: Color(0xFF38BDF8),
            ),
          ),
        );
      },
    );
  }
}
