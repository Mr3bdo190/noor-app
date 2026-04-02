import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AdhkarScreen extends StatefulWidget {
  const AdhkarScreen({super.key});

  @override
  State<AdhkarScreen> createState() => _AdhkarScreenState();
}

class _AdhkarScreenState extends State<AdhkarScreen> {
  List<Map<String, dynamic>> _adhkar = [
    {'id': 'subhan', 'text': 'سُبْحَانَ اللَّهِ وَبِحَمْدِهِ', 'count': 33, 'total': 33},
    {'id': 'استغفار', 'text': 'أَسْتَغْفِرُ اللَّهَ وَأَتُوبُ إِلَيْهِ', 'count': 100, 'total': 100},
  ];

  @override
  void initState() {
    super.initState();
    _loadProgress();
  }

  // تحميل التقدم من ذاكرة الهاتف
  _loadProgress() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      for (var zikr in _adhkar) {
        zikr['count'] = prefs.getInt(zikr['id']) ?? zikr['total'];
      }
    });
  }

  // حفظ التقدم
  _saveProgress(String id, int count) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setInt(id, count);
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.fromLTRB(20, 60, 20, 20),
      itemCount: _adhkar.length + 1,
      itemBuilder: (context, index) {
        if (index == 0) return const Text('الأذكار اليومية', style: TextStyle(fontSize: 28, color: Color(0xFF38BDF8), fontWeight: FontWeight.bold));
        final zikr = _adhkar[index - 1];
        final isDone = zikr['count'] == 0;

        return Card(
          color: isDone ? Colors.green.withOpacity(0.1) : const Color(0xFF1E293B),
          margin: const EdgeInsets.only(bottom: 12),
          child: ListTile(
            onTap: isDone ? null : () {
              setState(() {
                zikr['count']--;
                _saveProgress(zikr['id'], zikr['count']);
              });
            },
            title: Text(zikr['text'], textAlign: TextAlign.center, style: const TextStyle(fontSize: 18)),
            trailing: Text('${zikr['count']}', style: TextStyle(color: isDone ? Colors.green : const Color(0xFF38BDF8), fontWeight: FontWeight.bold, fontSize: 20)),
          ),
        );
      },
    );
  }
}
